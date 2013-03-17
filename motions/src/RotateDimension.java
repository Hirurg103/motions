import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import types.Range;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class RotateDimension extends JSlider implements ChangeListener {
	/** This class represents rotate dimension. 
	 *  Attribute range sets bounds of rotation capacity.
	 *  axisAngle is a vector in the space which defines the axis of rotation.
	 *  angleChanged sets to true after angle changed and sets to false after angle read.
	 *  axisChanged sets to true after axis changed and sets to false after axis read.
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Range<Integer> range;
	private AxisAngle4f axisAngle;
	private ArrayList<UpdateListener> updateListeners;
	
	public RotateDimension(String name, Vector3f axis, Range<Integer> range) {
		super(range.from(), range.to());
		this.name = name;
		axis.normalize(); this.axisAngle = new AxisAngle4f(axis, 0);
		this.range = range;
		this.addChangeListener(this);
		this.updateListeners = new ArrayList<UpdateListener>();
	}
	
	public RotateDimension(String name, Vector3f axis) { 
		this(name, axis, new Range<Integer>(0, 0));
	}
	
	public RotateDimension(String name) { 
		this(name, new Vector3f(0.0f, 0.0f, 1.0f), new Range<Integer>(0, 0));
	}
	
	public void setAngle(int angle) {
		if(range.contains(angle)) {
			this.axisAngle.setAngle(angle);
		} else if(range.onLeftSizeOf(angle)) {
			this.axisAngle.setAngle(range.to());
		} else {
			this.axisAngle.setAngle(range.from());
		}
		notifyUpdateListeners();
	}
	
	public int getAngle() { return (int)this.axisAngle.getAngle(); }
	
	public String getName() { return this.name; }
	
	public Range<Integer> getRange() { return this.range; }
	
	public Vector3f getAxis() { return new Vector3f(axisAngle.x, axisAngle.y, axisAngle.z); }
	
	public void setAxis(Vector3f axis) { axis.normalize(); this.axisAngle.set(axis, this.axisAngle.getAngle()); notifyUpdateListeners(); }
	
	public void setAxis(float x, float y, float z) { setAxis(new Vector3f(x, y, z)); }

	@Override
	public void stateChanged(ChangeEvent e) {
		 JSlider slider = (JSlider)e.getSource();
		 setAngle((int)slider.getValue());
	}
	
	public Matrix4f getRotationMatrix() {
		Matrix4f rotationMatrix = new Matrix4f();
		rotationMatrix.set(1);
		rotationMatrix.set(this.axisAngle);
		return rotationMatrix;
	}
	
	private void notifyUpdateListeners() {
		for(UpdateListener updateListener: updateListeners) {
			updateListener.update();
		}
	}
	
	public void addUpdateListener(UpdateListener updateListener) {
		updateListeners.add(updateListener);
	}
}
