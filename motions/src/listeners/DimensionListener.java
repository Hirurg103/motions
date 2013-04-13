package listeners;

import gui.dimensions.MotionDimension;


public interface DimensionListener {
	public void dimensionChanged(MotionDimension<? extends Number> dimension);
}
