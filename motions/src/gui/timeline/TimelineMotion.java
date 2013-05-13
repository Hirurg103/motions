package gui.timeline;

import figures.HumanSkeleton;
import gui.dimensions.MotionDimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimelineMotion {
	private ArrayList<TimelineMotionDimension> timelineMotionDimensions;
	private float time;

	public TimelineMotion(List<Map<String, Object>> queryTimelineDimensions) {
		setTimelineMotionDimensions(new ArrayList<TimelineMotionDimension>());
		setTime(TimelineDimensionSettingPanel.getTime());
		for(Map<String, Object> queryTimelineDimension : queryTimelineDimensions) {
			MotionDimension motionDimension = HumanSkeleton.motionDimensions.get(queryTimelineDimension.get("DIMENSION_ID"));
			if(motionDimension == null) continue;
			TimelineMotionDimension timelineMotionDimension = new TimelineMotionDimension(motionDimension, new Float(queryTimelineDimension.get("FROM_F").toString()), new Float(queryTimelineDimension.get("TO_F").toString()), new Float(queryTimelineDimension.get("INITIAL_F").toString()));
			timelineMotionDimension.setIsSynchronized((int)queryTimelineDimension.get("IS_SYNCHRONIZED") == 1);
			timelineMotionDimension.setBounds(TimelineDimensionSettingPanel.getCursorPosition() - TimelineMotionDimension.NORMAL_WIDTH/2, 0, TimelineMotionDimension.NORMAL_WIDTH, TimelineMotionDimension.NORMAL_HEIGHT);
			timelineMotionDimension.setTimelineMotion(this);
			timelineMotionDimension.setMotionId(queryTimelineDimension.get("MOTION_ID"));
			timelineMotionDimension.setId(queryTimelineDimension.get("ID"));

			timelineMotionDimensions.add(timelineMotionDimension);
		}		
	}

	public ArrayList<TimelineMotionDimension> getTimelineMotionDimensions() { return timelineMotionDimensions; }

	public void setTimelineMotionDimensions(ArrayList<TimelineMotionDimension> timelineMotionDimensions) { this.timelineMotionDimensions = timelineMotionDimensions; }

	public float getTime() { return time; }

	public void setTime(float time) { this.time = time; }
}
