package fr.iutvalence.java.Notit.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import fr.iutvalence.java.Notit.Application;
import fr.iutvalence.java.Notit.DayNote;
import fr.iutvalence.java.Notit.GeneralNote;
/**
 * This is the panel of each note of the application.
 * @author g19
 *
 */
public class NotePanel extends JPanel implements ActionListener{

	private JLabel noteName;
	private MultiLineLabel descriptionNote;
	private Button deleteNote;
	private JButton editNote;
	private JSplitPane buttonSplit;
	private JSplitPane labelSplit;
	private GeneralNote generalNote;
	private DayNote dayNote;
	private Application application;
	private MainFrame theFrame;
	private DayPanel panel;
	private JSplitPane optionSplit;
	private JSplitPane noteSplit;

	/**
	 * 1st constructor, if the note is a GeneralNote.
	 * @param generalNote
	 * @param theFrame
	 */
	public NotePanel(GeneralNote generalNote, MainFrame theFrame){
		this.generalNote=generalNote;
		display(generalNote.getTitle(), generalNote.getContent(), theFrame);
	}

	/**
	 * 2nd constructor, if the note is a DayNote, on the HomePageFrame.
	 * @param dayNote
	 * @param theFrame
	 */
	public NotePanel(DayNote dayNote, MainFrame theFrame){
		this.dayNote = dayNote;
		display(dayNote.getTitle(), dayNote.getContent(), theFrame);
	}

	/**
	 * the 3rd constructor, if the note is a dayNote, one the DayPanel 'panel'.
	 * @param dayNote
	 * @param panel
	 * @param theFrame
	 */
	public NotePanel(DayNote dayNote, DayPanel panel, MainFrame theFrame){
		this.panel = panel;
		this.dayNote = dayNote;
		display(dayNote.getTitle(), dayNote.getContent(), theFrame);
	}

	/**
	 * This is the components of this Panel.
	 * @param name
	 * @param content
	 * @param theFrame
	 */
	public void display(String name, String content, MainFrame theFrame){

		this.setBackground(Color.WHITE);

		this.theFrame = theFrame;
		this.application = theFrame.getApplication();
		Dimension dimForButton = new Dimension(30, 30);
		Dimension dimForTitle = new Dimension(210,30);
		Dimension dimForContent = new Dimension(210,100);
		Color color = new Color(231,76,60);

		this.setPreferredSize(new Dimension(240, 140));

		/**
		 * JLabel
		 */
		this.noteName = new JLabel(name);
		this.noteName.setPreferredSize(dimForTitle);
		this.noteName.setFont(new Font("LAO UI", 1, 15));
		this.noteName.setBorder(new EmptyBorder(0,10,0,0));

		this.descriptionNote = new MultiLineLabel(content);
		this.descriptionNote.setBackground(Color.WHITE);
		this.descriptionNote.setPreferredSize(dimForContent);
		this.descriptionNote.setBorder(new EmptyBorder(0,10,0,0));

		/**
		 * JButton
		 */
		this.deleteNote = new Button(new ImageIcon("img/close.png"), dimForButton);
		this.deleteNote.setBackground(color);

		this.editNote = new Button(new ImageIcon("img/pencil.png"), dimForButton);
		this.editNote.setBackground(color);

		this.deleteNote.addActionListener(this);
		this.editNote.addActionListener(this);
		/**
		 * JSplitPane
		 */
		this.buttonSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, deleteNote, editNote);
		this.labelSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, noteName, descriptionNote);

		this.buttonSplit.setBorder(null);
		this.labelSplit.setBorder(null);
		this.labelSplit.setBackground(Color.WHITE);

		this.buttonSplit.setDividerSize(0);
		this.labelSplit.setDividerSize(0);

		JPanel designPanel = new JPanel();
		designPanel.setBackground(new Color(231, 76, 60));

		this.optionSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.buttonSplit, designPanel);
		this.optionSplit.setDividerSize(0);
		this.optionSplit.setBorder(null);


		this.noteSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.optionSplit, this.labelSplit);
		this.noteSplit.setDividerSize(0);
		this.noteSplit.setBorder(null);
		this.add(this.noteSplit);
	}

	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==this.deleteNote){     

			int jop = JOptionPane.showConfirmDialog(
					this.theFrame,
					"Would you delete this Not'it?",
					"Delete",
					JOptionPane.YES_NO_OPTION);

			if(jop==JOptionPane.YES_OPTION){
				if( this.generalNote == null){ //if the Note is a DayNote 
					try {
						this.application.deleteDayNotes(this.dayNote); //delete DayNote
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}	

					if(this.panel==null)//if the note is on the HomePagePanel
					{
						try {
							this.theFrame.getHomePage().displayDayNote();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{ // if the note is on the DayPanel
						try {
							this.panel.displayDayNotePanel(); 
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
				else{	// if the note is a GeneralNote
					try {
						this.application.deleteGNotes(this.generalNote);
						this.theFrame.getHomePage().displayGeneralNote();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}


		}

		if(e.getSource()==this.editNote){
			if( this.generalNote == null){
				if(this.panel==null)
				{
					new EditNoteFrame(this.dayNote, this.theFrame);
				}
				else{
					new EditNoteFrame(this.dayNote, this.panel, this.theFrame);
				}
			}
			else{
				new EditNoteFrame(this.generalNote, this.theFrame);
			}
		}

	}

}
