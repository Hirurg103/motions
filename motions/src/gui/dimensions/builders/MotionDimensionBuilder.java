package gui.dimensions.builders;

import gui.dimensions.MotionDimension;

public interface MotionDimensionBuilder {
	public MotionDimension build(String name, float from, float to, float initial);
}
