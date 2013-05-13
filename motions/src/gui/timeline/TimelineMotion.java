package gui.timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.DatabaseUtils;

public class TimelineMotion {
	private ArrayList<TimelineMotionDimension> timelineMotionDimensions;

	public TimelineMotion(List<Map<String, Object>> queryTimelineDimensions) {
		setTimelineMotionDimensions(new ArrayList<TimelineMotionDimension>());
		for(Map<String, Object> queryMotionDimension : queryTimelineDimensions) {
			TimelineMotionDimension timelineMotionDimension = new TimelineMotionDimension(queryMotionDimension);
			timelineMotionDimension.setTimelineMotion(this);
			timelineMotionDimensions.add(timelineMotionDimension);
		}		
	}
	
	public TimelineMotion(Object motionId) {
		this(queryMotionDimensions(motionId));
	}
	
	@SuppressWarnings("serial")
	public static List<Map<String, Object>> queryMotionDimensions(final Object motionId) {
		return DatabaseUtils.query("select * from motions inner join motion_dimensions on motions.id = motion_dimensions.motion_id where motions.id = ?", new ArrayList<Object>() {{ add(motionId); }});
	}

	public ArrayList<TimelineMotionDimension> getTimelineMotionDimensions() { return timelineMotionDimensions; }

	public void setTimelineMotionDimensions(ArrayList<TimelineMotionDimension> timelineMotionDimensions) { this.timelineMotionDimensions = timelineMotionDimensions; }
}
