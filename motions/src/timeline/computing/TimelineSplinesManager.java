package timeline.computing;

import figures.HumanSkeleton;
import gui.dimensions.MotionDimension;
import gui.timeline.TimelineDimensionSettingPanel;
import gui.timeline.TimelineMotionDimension;

import java.util.HashMap;

import spline.BaseSpline;
import spline.linear.LinearSpline;

public class TimelineSplinesManager extends HashMap<Object, BaseSpline> {
	/**
	 * 	Manages time line splines  
	 */
	private static final long serialVersionUID = -5469836369667844890L;
	public static TimelineSplinesManager timelineSplinesManager = new TimelineSplinesManager();
	
	public TimelineSplinesManager() {
		super();
	}
	
	public void addTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		if(get(timelineMotionDimension.getMotionDimensionId()) == null) {
			put(timelineMotionDimension.getMotionDimensionId(), new LinearSpline(timelineMotionDimension));
		} else {
			get(timelineMotionDimension.getMotionDimensionId()).getPoints().put(timelineMotionDimension.getTime(), timelineMotionDimension.getConvertedValue());
		}
		resetMotionDimensionFor(timelineMotionDimension);
	}
	
	public static void add(TimelineMotionDimension timelineMotionDimension) {
		timelineSplinesManager.addTimelineMotionDimension(timelineMotionDimension);
	}
	
	public void removeTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		if(get(timelineMotionDimension.getMotionDimensionId()) == null) return; 
		get(timelineMotionDimension.getMotionDimensionId()).getPoints().remove(timelineMotionDimension.getTime());
		resetMotionDimensionFor(timelineMotionDimension);
	}
	
	public static void remove(TimelineMotionDimension timelineMotionDimension) {
		timelineSplinesManager.removeTimelineMotionDimension(timelineMotionDimension);
	}
	
	public void resetTimeForTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension, float oldTime) {
		if(get(timelineMotionDimension.getMotionDimensionId()) == null) {
			put(timelineMotionDimension.getMotionDimensionId(), new LinearSpline(timelineMotionDimension));
		} else {
			get(timelineMotionDimension.getMotionDimensionId()).getPoints().remove(oldTime);
			addTimelineMotionDimension(timelineMotionDimension);
		}
		resetMotionDimensionFor(timelineMotionDimension);
	}
	
	public static void resetTimeFor(TimelineMotionDimension timelineMotionDimension, float oldTime) {
		timelineSplinesManager.resetTimeForTimelineMotionDimension(timelineMotionDimension, oldTime);
	}
	
	public void resetValueForTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		if(get(timelineMotionDimension.getMotionDimensionId()) == null) {
			put(timelineMotionDimension.getMotionDimensionId(), new LinearSpline(timelineMotionDimension));
		} else {
			removeTimelineMotionDimension(timelineMotionDimension);
			addTimelineMotionDimension(timelineMotionDimension);
		}
		resetMotionDimensionFor(timelineMotionDimension);
	}

	public static void resetValueFor(TimelineMotionDimension timelineMotionDimension) {
		timelineSplinesManager.resetValueForTimelineMotionDimension(timelineMotionDimension);
	}

	public static void resetMotionDimensionFor(Object motionDimensionId) {
		MotionDimension motionDimension = HumanSkeleton.motionDimensions.get(motionDimensionId);
		if(motionDimension == null) return;
		BaseSpline spline = timelineSplinesManager.get(motionDimensionId);
		if(spline == null) return;
		if(spline.getPoints().size() == 0) {
			motionDimension.reset();
			timelineSplinesManager.remove(motionDimensionId);
			return;
		}
		motionDimension.setValue(motionDimension.getRevertedValue(spline.calculate(TimelineDimensionSettingPanel.getTime())));
	}
	
	public static void resetMotionDimensionFor(TimelineMotionDimension timelineMotionDimension) {
		resetMotionDimensionFor(timelineMotionDimension.getMotionDimensionId());
	}

	public static void resetMotionDimensions() {
		for(Object motionDimensionId : timelineSplinesManager.keySet()) {
			resetMotionDimensionFor(motionDimensionId);
		}
	}
}
