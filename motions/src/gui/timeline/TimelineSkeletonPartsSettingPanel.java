package gui.timeline;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TimelineSkeletonPartsSettingPanel extends JPanel {

	/**
	 * 	Holds time line settings for all skeleton parts.
	 */
	private static final long serialVersionUID = -4311210569005609359L;
	HashMap<Object, TimelineSkeletonPartSettingPanel> timelineSkeletonPartSettingPanels;
	private Component verticalStrut;

	public TimelineSkeletonPartsSettingPanel() {
		super();
		timelineSkeletonPartSettingPanels = new HashMap<Object, TimelineSkeletonPartSettingPanel>();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int verticalStrutHeight = TimelinePanel.TIMELINE_PANEL_HEIGHT - getPreferredSize().height;
		if(verticalStrutHeight < 0) verticalStrutHeight = 0;
		this.verticalStrut = Box.createVerticalStrut(verticalStrutHeight);
		add(verticalStrut);
	}

	public void addTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		if(timelineSkeletonPartSettingPanels.get(timelineMotionDimension.getSkeletonPartId()) == null) {
			TimelineSkeletonPartSettingPanel timelineSkeletonPartSettingPanel = new TimelineSkeletonPartSettingPanel("Skeleton Part");
			timelineSkeletonPartSettingPanels.put(timelineMotionDimension.getSkeletonPartId(), timelineSkeletonPartSettingPanel);
			add(timelineSkeletonPartSettingPanel);
			revalidateHeight();
		}
		timelineSkeletonPartSettingPanels.get(timelineMotionDimension.getSkeletonPartId()).addTimelineMotionDimension(timelineMotionDimension);
	}

	public void revalidateHeight() {
		remove(verticalStrut);
		// reset vertical strut to prevent scratch interface by vertical
		int verticalStrutHeight = getParent().getParent().getPreferredSize().height - getPreferredSize().height;
		if(verticalStrutHeight  > 0) add(verticalStrut = Box.createVerticalStrut(verticalStrutHeight));
	}

}
