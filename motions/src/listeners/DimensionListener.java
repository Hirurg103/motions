package listeners;

import main.MotionDimension;


public interface DimensionListener {
	public void dimensionChanged(MotionDimension<? extends Number> dimension);
}
