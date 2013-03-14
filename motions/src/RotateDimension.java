import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import types.Range;
import javax.vecmath.Vector3f;

public class RotateDimension extends JSlider implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int angle;
	private Range<Integer> range;
	private Vector3f axis;
	
	public RotateDimension(String name, Vector3f axis, Range<Integer> range) {
		super(range.from(), range.to());
		this.name = name;
		this.axis = axis;
		this.range = range;
		this.angle = 0;
		this.addChangeListener(this);
	}
	
	public RotateDimension(String name, Vector3f axis) { 
		this(name, axis, new Range<Integer>(0, 0));
	}
	
	public RotateDimension(String name) { 
		this(name, new Vector3f(0.0f, 0.0f, 1.0f), new Range<Integer>(0, 0));
	}
	
	public void setCurrentAngle(int angle) {
		Integer value = new Integer(angle);
		if(range.contains(value)) {
			this.angle = angle;
		} else if(range.onLeftSizeOf(value)) {
			this.angle = range.to();
		} else {
			this.angle = range.from();
		}
	}
	
	public int getAngle() { return this.angle; }
	
	public String getName() { return this.name; }
	
	public Range<Integer> getRange() { return this.range; }
	
	public Vector3f getAxis() { return this.axis; }
	
	public void setAxis(Vector3f axis) { this.axis = axis; }
	
	public void setAxis(float x, float y, float z) { this.axis = new Vector3f(x, y, z); }

	@Override
	public void stateChanged(ChangeEvent e) {
		 JSlider slider = (JSlider)e.getSource();
		 this.angle = (int)slider.getValue();
	}
}
