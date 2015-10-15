package fr.iutvalence.java.Notit.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.iutvalence.java.Notit.Application;
import fr.iutvalence.java.Notit.Date;
import fr.iutvalence.java.Notit.DayNote;
import fr.iutvalence.java.Notit.GeneralNote;
/**
 * This is the 1st panel of the main frame.
 * Here we can see the notes of the actual day and the general notes.
 * @author g19
 *
 */
public class HomePagePanel extends JPanel implements ActionListener{

	private MainFrame theFrame;
	private Button toCalendarButton;
	private JButton addGeneralNoteButton;
	private JButton addDayNoteButton;
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JPanel generalNotePanel;
	private JPanel dayNotePanel;
	private JSplitPane generalNoteSplit;
	private JSplitPane dayNoteSplit;
	private JSplitPane allGeneral;
	private JSplitPane allDay;
	private Application application;
	/**
	 * Constructor of the panel.
	 * @param frame
	 * @param application
	 * @throws IOException
	 */
	public HomePagePanel(MainFrame frame, Application application) throws IOException{

		/**
		 * Different dimension.
		 */
		Dimension buttonDimension = new Dimension(50,30);
		Dimension labelDimension = new Dimension(974,30);
		Dimension panelDimension = new Dimension(1024,300);

		
		this.application = application;
		this.theFrame = frame;
		this.setSize(1024, 768);
		this.setBackground(Color.WHITE);

		/**
		 * the Buttons.
		 */
		this.toCalendarButton = new Button(new ImageIcon("img/calendar.png"),new Dimension(1024,68),"  GO TO CALENDAR");
		this.toCalendarButton.setFont(new Font("Lao UI", 1, 32));
		this.toCalendarButton.setBackground(new Color(231, 76, 60));

		ImageIcon imgPlus = new ImageIcon("img/plus.png");
		this.addGeneralNoteButton = new Button(imgPlus, new Dimension(buttonDimension));
		this.addDayNoteButton = new Button(imgPlus, new Dimension(buttonDimension));

		this.addGeneralNoteButton.setBackground(Color.WHITE);
		this.addDayNoteButton.setBackground(Color.WHITE);

		this.toCalendarButton.addActionListener(this);
		this.addGeneralNoteButton.addActionListener(this);
		this.addDayNoteButton.addActionListener(this);

		/**
		 *  the labels
		 */
		this.firstLabel = new JLabel("General Note");
		this.firstLabel.setForeground(new Color(192, 57, 43));
		this.firstLabel.setBackground(Color.WHITE);
		this.firstLabel.setFont(new Font("Lao UI", 1, 28));


		Date date = new Date();
		String currentDay = date.getEntireDate();
		this.secondLabel = new JLabel("Note of "+ currentDay);
		this.secondLabel.setForeground(new Color(192, 57, 43));
		this.secondLabel.setBackground(Color.WHITE);
		this.secondLabel.setFont(new Font("Lao UI", 1, 28));

		this.firstLabel.setPreferredSize(labelDimension);
		this.secondLabel.setPreferredSize(labelDimension);

		/**
		 * the panels
		 */
		this.generalNotePanel = new JPanel();
		this.dayNotePanel = new JPanel();

		this.generalNotePanel.setBackground(Color.WHITE);
		this.dayNotePanel.setBackground(Color.WHITE);
		this.generalNotePanel.setPreferredSize(panelDimension);
		this.dayNotePanel.setPreferredSize(panelDimension);


		/**
		 * SplitPane
		 */
		this.generalNoteSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, firstLabel, addGeneralNoteButton);
		this.dayNoteSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, secondLabel, addDayNoteButton);
		this.allGeneral = new JSplitPane(JSplitPane.VERTICAL_SPLIT, generalNoteSplit, generalNotePanel);
		this.allDay = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dayNoteSplit, dayNotePanel);

		this.generalNoteSplit.setBackground(Color.WHITE);
		this.dayNoteSplit.setBackground(Color.WHITE);
		this.allGeneral.setBackground(Color.WHITE);
		this.allDay.setBackground(Color.WHITE);

		this.generalNoteSplit.setBorder(null);
		this.dayNoteSplit.setBorder(null);
		this.allGeneral.setBorder(null);
		this.allDay.setBorder(null);

		this.generalNoteSplit.setDividerSize(0);
		this.dayNoteSplit.setDividerSize(0);
		this.allGeneral.setDividerSize(0);
		this.allDay.setDividerSize(0); 

		/**
		 * Layout and Visibility.
		 */
		this.add(toCalendarButton, BorderLayout.PAGE_START);
		this.add(allGeneral, BorderLayout.CENTER);
		this.add(allDay, BorderLayout.PAGE_END);

		this.displayGeneralNote();
		this.displayDayNote();

	}

	/**
	 * Display the notes (GeneralNote / NotePanel) of the 'generalNotePanel'.
	 * Refresh the Panel if we add new GeneralNote.
	 * @throws IOException
	 */
	public void displayGeneralNote() throws IOException{
		this.setVisible(false);
		this.generalNotePanel.removeAll();
		for(GeneralNote generalNote : this.application.getGeneralNote()){
			this.generalNotePanel.add(new NotePanel(generalNote, this.theFrame));
		}
		this.generalNotePanel.revalidate();
		this.setVisible(true);
	}


	/**
	 * Display the notes (DayNote / NotePanel) of the 'generalNotePanel'.
	 * Refresh the Panel if we add new DayNote.
	 * @throws IOException
	 */
	public void displayDayNote() throws IOException{
		this.setVisible(false);
		this.dayNotePanel.removeAll();
		for(DayNote dayNote : this.application.getDayNote()){
			this.dayNotePanel.add(new NotePanel(dayNote, this.theFrame));
		}
		this.dayNotePanel.revalidate();
		this.setVisible(true);
	}

	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()== this.toCalendarButton){
			this.theFrame.setContentPane(this.theFrame.getCalendarPanel());
			this.theFrame.revalidate();
		}

		if(e.getSource()==this.addGeneralNoteButton){
			new NewNoteFrame(this.theFrame);
		}

		if(e.getSource()==this.addDayNoteButton){
			try {
				new NewNoteFrame(new Date(), this.theFrame);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
