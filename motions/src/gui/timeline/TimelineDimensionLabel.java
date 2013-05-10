package gui.timeline;

import java.awt.Font;

import javax.swing.JLabel;

public class TimelineDimensionLabel extends JLabel {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 9082367535569461835L;
	
	public TimelineDimensionLabel(String text) {
		super(text);
		setFont(new Font("Geneva", Font.PLAIN, 8));
	}
}
