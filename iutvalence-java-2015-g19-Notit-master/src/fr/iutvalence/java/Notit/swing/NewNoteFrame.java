package fr.iutvalence.java.Notit.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.iutvalence.java.Notit.Date;
/**
 * This class open new frame when the user add new note.
 * @author g19
 *
 */
public class NewNoteFrame extends JFrame implements ActionListener{

	private MainFrame theFrame;
	private Button addNoteButton;
	private JLabel titleLabel;
	private JLabel contentsLabel;
	private JTextField titleText;
	private JTextArea contentsText;
	private JSplitPane titleSplitPane;
	private JSplitPane contentsSplitPane;
	private Date date;
	private DayPanel panel;
	
	/**
	 * 1st constructor, if add general note in the HomePagePanel.
	 * @param frame
	 */
	public NewNoteFrame(MainFrame frame){
		displayOfFrame(frame);
	}
	
	/**
	 * 2nd constructor, if add day note in the HomePagePanel (for current day).
	 * @param date
	 * @param frame
	 */
	public NewNoteFrame(Date date, MainFrame frame){
		this.date=date;
		displayOfFrame(frame);
	}
	
	/**
	 * 3rd constructor, if add day note in the panel of the day (DayPanel).  
	 * @param date
	 * @param panel
	 * @param frame
	 */
	public NewNoteFrame(Date date, DayPanel panel, MainFrame frame){
		this.panel = panel;
		this.date=date;
		displayOfFrame(frame);
	}
	
	/**
	 * This is the components of this Frame.
	 * @param frame
	 */
	private void displayOfFrame(MainFrame frame){
		this.setTitle("Not'It : new note");
		ImageIcon img = new ImageIcon("img/calendar_icon.png");
		this.setIconImage(img.getImage());
		this.setSize(500, 380); 
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.theFrame = frame;
		
		/**
		 * JButton
		 */
		this.addNoteButton = new Button(null, new Dimension(500, 40), "Add");
		this.addNoteButton.setBackground(new Color(231, 76, 60));
		this.addNoteButton.setFont(new Font("Lao UI", 1, 20));
		this.addNoteButton.addActionListener(this);
		
		/**
		 * JLabel
		 */
		this.titleLabel = new JLabel("Title :");
		this.titleLabel.setFont(new Font("Lao UI", 1, 18));
		this.contentsLabel = new JLabel("Contents :");
		this.contentsLabel.setFont(new Font("Lao UI", 1, 18));
		
		this.titleLabel.setPreferredSize(new Dimension(500, 40));
		this.contentsLabel.setPreferredSize(new Dimension(500, 40));
		
		/**
		 * JTextField
		 */
		this.titleText = new JTextField("Title", 30);
		this.titleText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231, 76, 60)));
		this.contentsText = new JTextArea("Content");
		this.contentsText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231, 76, 60)));
		
		this.titleText.setPreferredSize(new Dimension(500, 20));
		this.contentsText.setPreferredSize(new Dimension(500, 240));

		/**
		 * JSplitPane
		 */
		this.titleSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.titleLabel, this.titleText);
		this.contentsSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.contentsLabel, this.contentsText);
		this.titleSplitPane.setBackground(Color.WHITE);
		this.contentsSplitPane.setBackground(Color.WHITE);
		this.titleSplitPane.setBorder(null);
		this.contentsSplitPane.setBorder(null);
		this.titleSplitPane.setDividerSize(0);
		this.contentsSplitPane.setDividerSize(0);
				
		/**
		 * Layout and Visibility.
		 */
		this.add(titleSplitPane, BorderLayout.PAGE_START);
		this.add(contentsSplitPane, BorderLayout.CENTER);
		this.add(addNoteButton, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}

	/**
	 * ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.addNoteButton)
			if(this.date==null){ // if no date = GeneralNote 
				try {
					this.theFrame.getApplication().createGNotes(this.titleText.getText(), this.contentsText.getText());
					this.dispose();
					this.theFrame.getHomePage().displayGeneralNote();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else{
				if(this.panel==null) // if no panel = dayNote of the HomePagePanel
				{
					try {
						this.theFrame.getApplication().createDayNotes(this.titleText.getText(), this.contentsText.getText(), this.date);
						this.dispose();
						this.theFrame.getHomePage().displayDayNote();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else{
					try { // dayNote of the DayPanel
						this.theFrame.getApplication().createDayNotes(this.titleText.getText(), this.contentsText.getText(), this.date);
						this.dispose();
						this.theFrame.getHomePage().displayDayNote();
						this.panel.displayDayNotePanel();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
	}
}
