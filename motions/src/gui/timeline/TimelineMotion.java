package gui.timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoundedRangeModel;

import db.DatabaseUtils;

public class TimelineMotion {
	private ArrayList<TimelineMotionDimension> timelineMotionDimensions;
	private BoundedRangeModel  commonModel = null;

	public TimelineMotion(List<Map<String, Object>> queryTimelineDimensions) {
		setTimelineMotionDimensions(new ArrayList<TimelineMotionDimension>());
		for(Map<String, Object> queryMotionDimension : queryTimelineDimensions) {
			TimelineMotionDimension timelineMotionDimension = new TimelineMotionDimension(queryMotionDimension);
			timelineMotionDimension.setTimelineMotion(this);
			timelineMotionDimensions.add(timelineMotionDimension);
			if(timelineMotionDimension.getIsSynchronized()) {
				if(commonModel == null) {  
					setCommonModel(timelineMotionDimension.getModel()); 
				} else {
					timelineMotionDimension.setModel(getCommonModel());
				}
			}
		}		
	}

	public TimelineMotion(Object motionId) {
		this(queryMotionDimensions(motionId));
	}

	@SuppressWarnings("serial")
	public static List<Map<String, Object>> queryMotionDimensions(final Object motionId) {
		return DatabaseUtils.query("select * from motions inner join motion_dimensions on motions.id = motion_dimensions.motion_id where motions.id = ?", new ArrayList<Object>() {{ add(motionId); }});
	}

	public void setNormalView() {
		for(TimelineMotionDimension timelineMotionDimension : getTimelineMotionDimensions()) {
			timelineMotionDimension.setNormalView();
		}
	}

	public void setSliderView() {
		for(TimelineMotionDimension timelineMotionDimension : getTimelineMotionDimensions()) {
			timelineMotionDimension.setSliderView();
		}
	}

	public ArrayList<TimelineMotionDimension> getTimelineMotionDimensions() { return timelineMotionDimensions; }

	public void setTimelineMotionDimensions(ArrayList<TimelineMotionDimension> timelineMotionDimensions) { this.timelineMotionDimensions = timelineMotionDimensions; }

	public BoundedRangeModel getCommonModel() { return commonModel; }

	public void setCommonModel(BoundedRangeModel commonModel) { this.commonModel = commonModel; }

	public void setDragging(boolean isDragging) {
		for(TimelineMotionDimension timelineMotionDimension : getTimelineMotionDimensions()) {
			timelineMotionDimension.setDragging(isDragging);
		}
	}
}
