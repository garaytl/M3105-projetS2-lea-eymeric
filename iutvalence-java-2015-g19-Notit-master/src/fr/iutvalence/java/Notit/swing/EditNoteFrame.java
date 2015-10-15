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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.iutvalence.java.Notit.DayNote;
import fr.iutvalence.java.Notit.GeneralNote;
/**
 * This new Frame allow to edit day and general Note.
 * @author g19
 *
 */
public class EditNoteFrame extends JFrame implements ActionListener{

	private MainFrame theFrame;
	private JButton editNoteButton;
	private JLabel titleLabel;
	private JLabel contentsLabel;
	private JTextField titleText;
	private JTextArea contentsText;
	private JSplitPane titleSplitPane;
	private JSplitPane contentsSplitPane;
	private DayPanel panel;
	private DayNote dayNote;
	private GeneralNote generalNote;
	
	/**
	 * 1st constructor, if the day note is edit on the HomePagePanel.
	 * @param dayNote
	 * @param frame
	 */
	public EditNoteFrame(DayNote dayNote, MainFrame frame){
		
		this.dayNote = dayNote;
		/**
		 * JTextField
		 */
		this.titleText = new JTextField(this.dayNote.getTitle(), 30);
		this.contentsText = new JTextArea(this.dayNote.getContent());
		
		this.displayOfFrame(frame);

	}
	
	/**
	 * 2nd constructor, for the general note on the HomePagePanel.
	 * @param generalNote
	 * @param frame
	 */
	public EditNoteFrame(GeneralNote generalNote, MainFrame frame){
		this.generalNote = generalNote;
		/**
		 * JTextField
		 */
		this.titleText = new JTextField(this.generalNote.getTitle(), 30);
		this.contentsText = new JTextArea(this.generalNote.getContent());
		
		this.displayOfFrame(frame);
		
		
	}
	
	/**
	 * 3rd constructor, for the day note on the panel of the specific day.
	 * @param dayNote
	 * @param panel
	 * @param frame
	 */
	public EditNoteFrame(DayNote dayNote, DayPanel panel, MainFrame frame){
		
		this.dayNote = dayNote;
		this.panel = panel;
		/**
		 * JTextField
		 */
		this.titleText = new JTextField(this.dayNote.getTitle(), 30);
		this.contentsText = new JTextArea(this.dayNote.getContent());
		
		this.displayOfFrame(frame);

	}
	
	/**
	 * This is the components of this Frame.
	 * @param frame
	 */
	private void displayOfFrame(MainFrame frame){
		this.setTitle("Not'It : edit note");
		ImageIcon img = new ImageIcon("img/calendar_icon.png");
		this.setIconImage(img.getImage());
		this.setSize(500, 380); 
		this.setResizable(false); 
		this.setLocationRelativeTo(null);
		this.theFrame = frame;
		
		/**
		 * JButton
		 */
		this.editNoteButton = new Button(null, new Dimension(500, 40), "Edit");
		this.editNoteButton.setBackground(new Color(231, 76, 60));
		this.editNoteButton.setFont(new Font("Lao UI", 1, 20));
		this.editNoteButton.addActionListener(this);
		
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
		this.titleText.setPreferredSize(new Dimension(500, 20));
		this.titleText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231, 76, 60)));
		this.contentsText.setPreferredSize(new Dimension(500, 270));
		this.contentsText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231, 76, 60)));
		
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
		this.add(editNoteButton, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}

	/**
	 * ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.editNoteButton)
			if(this.generalNote!=null){
				try { // if we are on the HomePagePanel for GeneralNote
					this.theFrame.getApplication().editGNotes(this.generalNote, this.titleText.getText(), this.contentsText.getText());
					this.dispose();
					this.theFrame.getHomePage().displayGeneralNote();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else{
				if(this.panel==null)
				{
					try { // if we are on the HomePagePanel for DayNote
						this.theFrame.getApplication().editDayNotes(this.dayNote, titleText.getText(), contentsText.getText());
						this.dispose();
						this.theFrame.getHomePage().displayDayNote();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else{
					try { // if we are on the DayPanel 
						this.theFrame.getApplication().editDayNotes(this.dayNote, titleText.getText(), contentsText.getText());
						this.dispose();
						this.panel.displayDayNotePanel();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
	}

}
