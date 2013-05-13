package timeline.computing;

import java.util.ArrayList;
import java.util.TreeMap;

public class TimelineDimensionPoints extends TreeMap<Float, ArrayList<Float>> {

	/**
	 * 	Holds time line points for given dimension. 
	 */
	private static final long serialVersionUID = 6260137644079046841L;
	
	public TimelineDimensionPoints() {
		super();
	}

	public void put(float time, float value) {
		ArrayList<Float> timePoints;
		if(super.get(time) != null) {
			timePoints = (ArrayList<Float>)super.get(time);
			timePoints.add(0, value);
		} else {
			timePoints = new ArrayList<Float>();
			timePoints.add(value);
			super.put(time, timePoints);
		}
	}
	
	public float get(float time) throws IndexOutOfBoundsException {
		if(super.get(time) == null) {
			 Float lowerTime = lowerKey(time);
			 if(lowerTime != null) {
				 time = lowerTime.floatValue();
			 } else {
				 Float higherTime = higherKey(time);
				 if(higherTime != null) {
					 time = higherTime.floatValue();
				 } else {
					 throw new IndexOutOfBoundsException("Cannot find time point");
				 }
			 }
		}
		
		ArrayList<Float> timePoints = (ArrayList<Float>)super.get(time);
		return timePoints.get(0);
	}
	
	@Override
	public ArrayList<Float> remove(Object key) {
		if(super.get(key) == null) return null;
		ArrayList<Float> timePoints = (ArrayList<Float>)super.get(key);
		timePoints.remove(0);
		if(timePoints.size() == 0) super.remove(key);
		return timePoints;
	}
}
