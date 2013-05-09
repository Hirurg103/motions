package gui.dimensions.builders;

import gui.dimensions.MotionDimension;

public class RotateDimensionBuilder implements MotionDimensionBuilder {

	@Override
	public MotionDimension build(String name, float from, float to, float initial) {
		MotionDimension rotateDimension = new MotionDimension(name, from, to, initial);
		rotateDimension.setPossibleTickLengths(new float[] {1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60, 90, 120, 150, 180, 210, 240, 270, 300, 360, 540, 720, 2^32});
		rotateDimension.setUnitSign("\u00B0");
		rotateDimension.updateLabelTable();
		rotateDimension.setPaintLabels(true);
		return rotateDimension;
	}

}
