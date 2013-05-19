package gui.timeline;

import java.awt.Rectangle;

import javax.swing.JScrollPane;

public class TimelineSkeletonPartsSettingScrollPane extends JScrollPane {

	/**
	 * 	Scroll pane to display skeleton parts setting panel
	 */
	private static final long serialVersionUID = -4133745910505408563L;

	public TimelineSkeletonPartsSettingScrollPane() { 
		super();
	}

	public void resetHorisontalScrollBarPosition(int mousePosition) {
		Rectangle visibleArea = TimelinePanel.timelineSkeletonPartsSettingPanel.getVisibleRect();
		if(mousePosition < visibleArea.x) {
			getHorizontalScrollBar().setValue(Math.max(0, mousePosition));
		} else {
			int newSliderPosition = mousePosition - visibleArea.width + TimelineDimensionSettingPanel.minLeftCursorPossition; 
			if(visibleArea.x < newSliderPosition) {
				getHorizontalScrollBar().setValue(Math.min(TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth, newSliderPosition));
				TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth = mousePosition + TimelineMotionDimension.horizontalSliderOffset();
				TimelinePanel.timelineSkeletonPartsSettingPanel.revalidate();
			}
		}
	}
}
