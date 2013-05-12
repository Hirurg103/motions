package gui.timeline;

import gui.HumanCanvas;
import gui.RightPane;

import java.awt.Dimension;

import javax.swing.JPanel;

public class TimelinePanel extends JPanel {
	/**
	 * 	Panel to create time lines
	 */
	private static final long serialVersionUID = -1198440512138924197L;
	public static final int TIMELINE_PANEL_HEIGHT = 224;
	public static TimelineSkeletonPartsSettingPanel timelineSkeletonPartsSettingPanel = new TimelineSkeletonPartsSettingPanel();
	public static TimelineSkeletonPartsSettingScrollPane timelineSkeletonPartsSettingScrollPane = new TimelineSkeletonPartsSettingScrollPane();

	static {
		timelineSkeletonPartsSettingScrollPane.setViewportView(timelineSkeletonPartsSettingPanel);
		timelineSkeletonPartsSettingScrollPane.setPreferredSize(new Dimension(HumanCanvas.HUMAN_CANVAS_WIDTH + RightPane.RIGHT_PANE_WIDTH, TIMELINE_PANEL_HEIGHT));
	}

	public TimelinePanel() {
		super();
		add(timelineSkeletonPartsSettingScrollPane);
	}

	public static void addTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		timelineSkeletonPartsSettingPanel.addTimelineMotionDimension(timelineMotionDimension);
	}
}
