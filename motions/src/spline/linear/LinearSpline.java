package spline.linear;

import gui.timeline.TimelineMotionDimension;
import spline.BaseSpline;

public class LinearSpline extends BaseSpline {
	public LinearSpline(TimelineMotionDimension timelineMotionDimension) { super(timelineMotionDimension); }

	@Override
	public float calculate(float time) {
		float leftTimePoint = points.lowerKey(time);
		float rightTimePoint = points.higherKey(time);
		float leftValue = points.get(leftTimePoint);
		float rightValue = points.get(rightTimePoint);
		
		if(points.containsKey(time) || leftTimePoint == rightTimePoint) { return points.get(time); }
		return (leftValue*(rightTimePoint - time) + rightValue*(time - leftTimePoint))/(rightTimePoint - leftTimePoint);
	}
}
