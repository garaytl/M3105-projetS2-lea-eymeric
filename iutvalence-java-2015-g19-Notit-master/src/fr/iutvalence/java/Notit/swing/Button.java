package fr.iutvalence.java.Notit.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * New Button class extends JButton.
 * This class create Button with Icon, dimension and optional text.
 * @author g19
 *
 */
public class Button extends JButton
{
	
	/**
	 * 1st constructor of Button.
	 * @param img
	 * @param size
	 */
	public Button(ImageIcon img, Dimension size){
		super();
		this.setIcon(img);
		this.setPreferredSize(size);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}
	
	/**
	 * 2nd constructor of Button.
	 * @param img
	 * @param size
	 * @param text
	 */
	public Button(ImageIcon img, Dimension size, String text){
		super(text);
		this.setIcon(img);
		this.setPreferredSize(size);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setForeground(Color.WHITE);
	}
}
