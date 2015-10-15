package fr.iutvalence.java.Notit.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.iutvalence.java.Notit.Date;
/**
 * This is the panel of the Calendar page of the application.
 * @author g19
 *
 */
public class CalendarPanel extends JPanel implements ActionListener{

	private MainFrame theFrame;
	private Button toHomePagebutton;
	private JButton[] tableButton;
	private JLabel monthOfYear;
	private JPanel panelOfMonthButton;
	
	/**
	 * Constructor of the panel.
	 * @param frame
	 * @throws IOException
	 */
	public CalendarPanel(MainFrame frame) throws IOException{

		this.theFrame=frame;
		this.setBackground(Color.WHITE);
		this.setSize(1024, 768); 

		/**
		 * the JButton.
		 */
		this.toHomePagebutton = new Button(new ImageIcon("img/home.png"), new Dimension(1024,68), "  GO TO HOMEPAGE");
		this.toHomePagebutton.setFont(new Font("LAO UI", 1, 32));
		this.toHomePagebutton.setBackground(new Color(231, 76, 60));
	  	this.toHomePagebutton.addActionListener(this);
		/**
		 * the JLabel.
		 */
		this.monthOfYear = new JLabel("Year "+ new Date().get(Date.YEAR), JLabel.CENTER); 
		this.monthOfYear.setPreferredSize(new Dimension(1024,50));
		this.monthOfYear.setFont(new Font("Lao UI", 1, 28));
		this.monthOfYear.setForeground(new Color(192, 57, 43));
		
		/**
		 * the Panel of button.
		 */
		this.panelOfMonthButton = new JPanel();
		this.panelOfMonthButton.setPreferredSize(new Dimension(1024,650));
		this.panelOfMonthButton.setLayout(new GridLayout(4,3));
		/**
		 * Button of month, create 12 buttons for the month of the year and put it in the table.
		 */
		DateFormatSymbols monthInYear = new DateFormatSymbols(Locale.ENGLISH); // monthinYear = English date format
		String[] theMonth = monthInYear.getMonths(); // theMonth = different moth in the year in English
		this.tableButton = new JButton[12];
		for(int month=0; month<12; month++){
			this.tableButton[month] = new JButton(theMonth[month]);
			this.tableButton[month].setBackground(Color.WHITE);
			this.tableButton[month].setForeground(new Color(231, 76, 60));
			this.tableButton[month].setFont(new Font("Lao UI", 1, 24));
			this.tableButton[month].setBorder(null);
			this.tableButton[month].setFocusPainted(false);
			this.tableButton[month].addActionListener(this);
			this.panelOfMonthButton.add(tableButton[month]);
		}

		/**
		 * Layout and Visibility.
		 */
		this.add(toHomePagebutton, BorderLayout.PAGE_START);
		this.add(monthOfYear, BorderLayout.CENTER);
		this.add(panelOfMonthButton, BorderLayout.PAGE_END);

		this.setVisible(true);
	}

	/**
	 * ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()== this.toHomePagebutton){
			this.theFrame.setContentPane(this.theFrame.getHomePage());
			this.theFrame.revalidate();
		}
		for (int x=0; x<12; x++)
			if (e.getSource() == this.tableButton[x])
			{
				try {
					
					this.theFrame.setContentPane(new MonthPanel(this.theFrame.getApplication().getCalendar(x), this.theFrame));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.theFrame.revalidate();
			}
		
		
		
	}
}
