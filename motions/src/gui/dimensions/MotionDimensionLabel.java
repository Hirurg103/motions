package gui.dimensions;

import java.awt.Font;

import javax.swing.JLabel;

public class MotionDimensionLabel extends JLabel {
	/**
	 *  Label on motion dimension slider
	 */
	private static final long serialVersionUID = 1L;

	public MotionDimensionLabel(String text) {
		super(text);
		setFont(new Font("Geneva", Font.PLAIN, 7));
	}
}
