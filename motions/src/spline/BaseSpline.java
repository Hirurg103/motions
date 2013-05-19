package spline;

import gui.timeline.TimelineMotionDimension;
import timeline.computing.TimelineDimensionPoints;

public abstract class BaseSpline {
	protected TimelineDimensionPoints points;
	
	public BaseSpline() {
		setPoints(new TimelineDimensionPoints());
	}
	
	public BaseSpline(TimelineMotionDimension timelineMotionDimension) {
		this();
		getPoints().put(timelineMotionDimension.getTime(), timelineMotionDimension.getConvertedValue());
	}
	
	public abstract float calculate(float t);
	
	public TimelineDimensionPoints getPoints() { return points; }

	public void setPoints(TimelineDimensionPoints points) { this.points = points; }
}
