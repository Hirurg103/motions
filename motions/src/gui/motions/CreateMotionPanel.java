package gui.motions;

import gui.MainPanel;
import gui.RightPane;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CreateMotionPanel extends JPanel {
	/**
	 * 	This class represents custom motion creation.
	 */
	private static final long serialVersionUID = 6559571653667296936L;
	public static SkeletonPartsSettingPanel skeletonPartsSettingPanel = new SkeletonPartsSettingPanel();

	public CreateMotionPanel() { 
		super();
		JScrollPane skeletonPartsSettingScrollPane = new JScrollPane();
		skeletonPartsSettingScrollPane.setViewportView(skeletonPartsSettingPanel);
		skeletonPartsSettingScrollPane.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, MainPanel.MAIN_PANEL_HEIGHT - 39 - 35));
		add(skeletonPartsSettingScrollPane);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createHorizontalStrut(12));
		buttonsPanel.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, 25));
		buttonsPanel.add(new JButton("Save"));
		add(buttonsPanel);
	}
}
