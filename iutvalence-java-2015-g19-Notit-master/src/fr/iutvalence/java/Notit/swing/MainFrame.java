package fr.iutvalence.java.Notit.swing;


import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.iutvalence.java.Notit.Application;
/**
 * This is the main frame of he application.
 * @author g19
 *
 */
public class MainFrame extends JFrame{

	private HomePagePanel homePage;
	private CalendarPanel calendarPanel;
	private Application application;

	/**
	 * the constructor of the frame.
	 * @throws IOException
	 */
	public MainFrame() throws IOException{

		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		this.application = new Application();
		ImageIcon img = new ImageIcon("img/calendar_icon.png");
		this.setIconImage(img.getImage());
		this.setTitle("Not'It");
		this.setSize(1037, 820);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.calendarPanel = new CalendarPanel(this);
		this.homePage = new HomePagePanel(this, this.application);
		this.getContentPane().add(this.homePage);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}
	
	/**
	 * return the calendarPanel, used by other Panel for switch to the calendarPanel.
	 * @return
	 */
	public CalendarPanel getCalendarPanel() {
		return calendarPanel;
	}

	/**
	 * return the homePagePanel, used by other Panel for switch to the homePagePanel.
	 * @return
	 */
	public HomePagePanel getHomePage() {
		return homePage;
	}
	
	/**
	 * return the application.
	 * @return
	 */
	public Application getApplication() {
		return application;
	}


}
