package fr.iutvalence.java.Notit.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
/**
 * Other JButton class, MyButton.
 * He is used for create the button of the MonthPanel, the ID allow to know the number of the day. 
 * @author g19
 *
 */
public class MyButton extends JButton{
	
	private int id;
	/**
	 * First constructor, for the disables buttons. 
	 */
	public MyButton(){
		super();
		this.setBorder(null);
		this.setFocusPainted(false);
		this.setBackground(Color.WHITE);
	}
	
	/**
	 * Second constructor, for the button of the day of month.
	 * @param nom
	 * @param theID
	 */
	public MyButton(String nom, int theID){
		super(nom);
		this.id = theID;
		this.setBackground(Color.WHITE);
		this.setFont(new Font("Lao UI", 1, 15));
		this.setFocusPainted(false);
		this.setBorder(null);
		
	}
	
	/**
	 * return the Id of the button, used in the ActionListener of the MonthPanel class, 
	 * for find the number of the button.
	 * @return
	 */
	public int getID(){
		return this.id;
	}

}
