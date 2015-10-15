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

import fr.iutvalence.java.Notit.Date;
import fr.iutvalence.java.Notit.DayNote;

/**
 * This is the panel for each day.
 * @author g19
 *
 */
public class DayPanel extends JPanel implements ActionListener{

	private MainFrame theFrame;
	private JButton toMonthButton;
	private JButton addDayNoteButton;
	private JLabel noteLabel;
	private JPanel notePanel;
	private JSplitPane noteSplit;
	private MonthPanel theMonthPanel;
	private Date theDate;
	private JSplitPane allDay;
	
	/**
	 * Constructor of the day panel.
	 * @param theDate
	 * @param theMonthPanel
	 * @param frame
	 * @throws IOException
	 */
	public DayPanel(Date theDate, MonthPanel theMonthPanel, MainFrame frame) throws IOException{

		Dimension buttonDimension = new Dimension(50,40);
		Dimension labelDimension = new Dimension(974,40);
		  
		this.theMonthPanel = theMonthPanel;
		this.theDate = theDate;
		this.theFrame = frame;
		this.setSize(1024, 768); 
		this.setBackground(Color.WHITE);

		/**
		 * the Buttons.
		 */
		this.toMonthButton = new JButton("to Month");
		
		this.toMonthButton = new Button(new ImageIcon("img/arrow-left.png"),new Dimension(1024, 68),"  GO TO MONTH");
		this.toMonthButton.setFont(new Font("Lao UI", 1, 32));
		this.toMonthButton.setBackground(new Color(231, 76, 60));
		
		this.addDayNoteButton = new Button(new ImageIcon("img/plus.png"), new Dimension(buttonDimension));
		this.addDayNoteButton.setBackground(Color.WHITE);
		
		this.toMonthButton.addActionListener(this);
		this.addDayNoteButton.addActionListener(this);


		/**
		 *  the labels
		 */
		this.noteLabel = new JLabel("Note of "+ theDate.getEntireDate(), JLabel.CENTER);
		this.noteLabel.setForeground(new Color(192, 57, 43));
		this.noteLabel.setBackground(Color.WHITE);
		this.noteLabel.setFont(new Font("LAO UI", 1, 28));
		this.noteLabel.setPreferredSize(labelDimension);

		/**
		 * the panels
		 */
		this.notePanel = new JPanel();

		this.notePanel.setBackground(Color.WHITE);
		this.notePanel.setPreferredSize(new Dimension(1024,650));


		/**
		 * SplitPane
		 */
		this.noteSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.noteLabel, this.addDayNoteButton);

		this.noteSplit.setDividerSize(0);
		this.noteSplit.setBorder(null);
		this.noteSplit.setBackground(Color.WHITE);

		this.allDay = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.noteSplit, this.notePanel);
		this.allDay.setDividerSize(0);
		this.allDay.setBorder(null);

		/**
		 * Layout and Visibility.
		 */
		this.add(toMonthButton, BorderLayout.PAGE_START);
		this.add(allDay, BorderLayout.CENTER);
		
		this.displayDayNotePanel();
		this.setVisible(true);
	}
	
	/**
	 * Display the note (DayNote / NotePanel) of the day in the 'notePanel'.
	 * Refresh the Panel if we add new DayNote.
	 * @throws IOException
	 */
	public void displayDayNotePanel() throws IOException{
		this.setVisible(false);
		this.notePanel.removeAll();
		this.theDate.updateListOfNote();
		for(DayNote dayNote : this.theDate.getListOfNote()){
			this.notePanel.add(new NotePanel(dayNote, this, this.theFrame));
		}
		this.notePanel.revalidate();
		this.setVisible(true);
	}

	/**
	 * ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if(e.getSource()== this.toMonthButton){
			this.theFrame.setContentPane(this.theMonthPanel);
			this.theFrame.revalidate();
		}
		if(e.getSource()==this.addDayNoteButton){
			new NewNoteFrame(this.theDate, this, this.theFrame);
		}
		
	}
}
