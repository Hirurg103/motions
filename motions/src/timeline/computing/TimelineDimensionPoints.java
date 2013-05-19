package timeline.computing;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TimelineDimensionPoints extends TreeMap<Float, ArrayList<Float>> implements Map<Float, ArrayList<Float>> {

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
			 Float lowerTime = super.lowerKey(time);
			 if(lowerTime != null) {
				 time = lowerTime.floatValue();
			 } else {
				 Float higherTime = super.higherKey(time);
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

	@Override
	public Float higherKey(Float key) {
		Float higherKey;
		if((higherKey = super.higherKey(key)) != null) return higherKey;
		return super.lastKey();
	}

	@Override
	public Float lowerKey(Float key) {
		Float lowerKey;
		if((lowerKey = super.lowerKey(key)) != null) return lowerKey;
		return super.firstKey();
	}

	@Override
	public Entry<Float, ArrayList<Float>> higherEntry(Float key) {
		Entry<Float, ArrayList<Float>> higherEntry;
		if((higherEntry = super.higherEntry(key)) != null) return higherEntry;
		return super.lastEntry();
	}

	@Override
	public Entry<Float, ArrayList<Float>> lowerEntry(Float key) {
		Entry<Float, ArrayList<Float>> lowerEntry;
		if((lowerEntry = super.lowerEntry(key)) != null) return lowerEntry;
		return super.firstEntry();
	}

	public Float higherValue(float key) {
		Entry<Float, ArrayList<Float>> higherEntry = higherEntry(key);
		if(higherEntry == null) return null;
		return higherEntry.getValue().get(0);
	}

	public Float lowerValue(float key) {
		Entry<Float, ArrayList<Float>> lowerEntry = lowerEntry(key);
		if(lowerEntry == null) return null;
		return lowerEntry.getValue().get(0);
	}
}
