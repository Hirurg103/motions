package gui.dimensions;

public class RotateDimension extends MotionDimension<Integer> {
	/**
	 *  Represents the angle of rotation of the skeleton part in degrees
	 */
	private static final long serialVersionUID = 1L;

	public RotateDimension(String name, int from, int to, int initial) {
		super(name, from, to, initial);
		this.possibleTickLengths = new float[] {1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60, 90, 120, 150, 180, 210, 240, 270, 300, 360, 540, 720, 2^32};
		setCountTicks(4);
		setUnitSign("\u00B0");
		updateLabelTable();
		setPaintLabels(true);
	}
	
	public int getAngle() {
		return (int)super.getConvertedValue();
	}
	
	@Override
	public RotateDimension clone() {
		return new RotateDimension(name, from, to, initial);
	}
}
