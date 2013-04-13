package gui.dimensions;

public class StretchDimension extends MotionDimension<Float> {
	/**
	 *  Represents the rate of stretch of the skeleton part relative to part length
	 */
	private static final long serialVersionUID = 1L;
	
	public StretchDimension(String name, float from, float to, float initial) {
		super(name, from, to, initial);
		this.possibleTickLengths = new float[] {0.01f, 0.02f, 0.03f, 0.04f, 0.05f, 0.1f, 0.2f, 0.5f, 1.0f, 2^32};
		setCountTicks(1);
		setMultiplier(100);
		setUnitSign("%");
		updateLabelTable();
		setPaintLabels(true);
	}
	
	public float getStretch() {
		return super.getConvertedValue();
	}
	
	@Override
	public StretchDimension clone() {
		return new StretchDimension(name, from, to, initial);
	}
}
