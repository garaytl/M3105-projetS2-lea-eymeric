package fr.iutvalence.java.Notit.swing;

import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Class, extends JPanel, create multi line label and add this on his panel.
 * @author g19
 *
 */
public class MultiLineLabel extends JPanel {

	/**
	 * Constructor of this panel.
	 * @param text
	 */
	public MultiLineLabel(String text){
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		StringTokenizer st = new StringTokenizer(text, "\n");
		while(st.hasMoreTokens()) { 
            this.add(new JLabel(st.nextToken())); 
      } 
	}
}
