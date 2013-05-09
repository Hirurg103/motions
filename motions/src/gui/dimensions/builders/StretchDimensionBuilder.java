package gui.dimensions.builders;

import gui.dimensions.MotionDimension;

public class StretchDimensionBuilder implements MotionDimensionBuilder {

	@Override
	public MotionDimension build(String name, float from, float to, float initial) {
		MotionDimension stretchDimension = new MotionDimension(name, from, to, initial);
		stretchDimension.setPossibleTickLengths(new float[] {0.01f, 0.02f, 0.03f, 0.04f, 0.05f, 0.1f, 0.2f, 0.5f, 1.0f, 2^32});
		stretchDimension.setMultiplier(100);
		stretchDimension.setUnitSign("%");
		stretchDimension.updateLabelTable();
		stretchDimension.setPaintLabels(true);
		return stretchDimension;
	}

}
