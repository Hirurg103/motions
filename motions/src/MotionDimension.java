import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MotionDimension<T extends Number> extends JSlider implements ChangeListener {
	/** This class represents arbitrary dimension model.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<DimensionListener> dimensionListeners;
	private T from;
	private T to;
	private static final int SENSITIVITY = 360;
	
	public MotionDimension(String name, T from, T to, T initial) {
		super(0, SENSITIVITY, (int)(SENSITIVITY*(initial.floatValue() - from.floatValue())/(to.floatValue() - from.floatValue())));
		setName(name);
		this.dimensionListeners = new ArrayList<DimensionListener>();
		this.addChangeListener(this);
		setFrom(from);
		setTo(to);
	}
	
	public void setName(String name) { this.name = name; }
	
	public String getName() { return name; }
	
	public float getNumericValue() {
		return from.floatValue() + (to.floatValue() - from.floatValue())*getValue()/SENSITIVITY;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) { notifyDimensionListeners(); }
	
	protected void notifyDimensionListeners() {
		for(DimensionListener dimensionListener: dimensionListeners) {
			dimensionListener.dimensionChanged(this);
		}
	}
	
	public void addDimensionListener(DimensionListener dimensionListener) {
		dimensionListeners.add(dimensionListener);
	}
	
	public void setFrom(T from) { this.from = from; }
	
	public T getFrom(T from) { return from; }
	
	public void setTo(T to) { this.to = to; }
	
	public T getTo(T from) { return to; }
}