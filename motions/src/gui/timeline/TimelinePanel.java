package gui.timeline;

import gui.HumanCanvas;
import gui.RightPane;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TimelinePanel extends JPanel {
	/**
	 * 	Panel to create time lines
	 */
	private static final long serialVersionUID = -1198440512138924197L;
	public static final int TIMELINE_PANEL_HEIGHT = 230;

	public TimelinePanel() {
		super();
		TimelineSkeletonPartsSettingPanel timelineSkeletonPartsSettingPanel = new TimelineSkeletonPartsSettingPanel();
		JScrollPane timelineSkeletonPartsSettingScrollPane = new JScrollPane();
		timelineSkeletonPartsSettingScrollPane.setViewportView(timelineSkeletonPartsSettingPanel);
		timelineSkeletonPartsSettingPanel.setPreferredSize(new Dimension(HumanCanvas.HUMAN_CANVAS_WIDTH + RightPane.RIGHT_PANE_WIDTH, TIMELINE_PANEL_HEIGHT));
		add(timelineSkeletonPartsSettingScrollPane);
	}
}
