package gui.dimensions;

import gui.motions.build.MotionDimensionSettingPanel;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listeners.DimensionListener;


public class MotionDimension<T extends Number> extends JSlider implements ChangeListener, Cloneable {
	/** 
	 * 	This class represents arbitrary dimension model.
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	private ArrayList<DimensionListener> dimensionListeners;
	protected T initFrom;
	protected T from;
	protected T initTo;
	protected T to;
	protected T initial;
	protected T current;
	protected BoundedRangeModel nativeModel;
	protected int sensitivity = 360;
	protected float[] possibleTickLengths = {1};
	protected int countTicks = 3;
	protected String unitSign = "";
	protected float multiplier = 1.0f;
	protected Hashtable<Integer, JLabel> labelTable;
	protected float minBoundaryOffset = 0.75f;
	protected float minTicksSpaceRate = 0.05f;
	protected Object id;
	protected Object motionId;
	protected boolean isSynchronized = false;
	
	public MotionDimension(String name, T from, T to, T initial) {
		super();
		setMinimum(0);
		setMaximum(sensitivity);
		setValue((int)(sensitivity*(initial.floatValue() - from.floatValue())/(to.floatValue() - from.floatValue())));
		setName(name);
		this.dimensionListeners = new ArrayList<DimensionListener>();
		this.addChangeListener(this);
		this.initFrom = from;
		setFrom(from);
		this.initTo = to;
		this.nativeModel = getModel();
		setTo(to);
		setInitial(initial);
		labelTable = new Hashtable<Integer, JLabel>();
	}
	
	public void setName(String name) { this.name = name; }
	
	public String getName() { return name; }
	
	public float getConvertedValue() {
		return from.floatValue() + (to.floatValue() - from.floatValue())*getValue()/sensitivity;
	}
	
	public int getRevertedValue(float value) {
		if(from.floatValue() < to.floatValue()) { 
			if(value < from.floatValue()) return 0;
			if(value > to.floatValue()) return sensitivity;
		} else {
			if(value > from.floatValue()) return 0;
			if(value < to.floatValue()) return sensitivity;
		}
		return (int)((value - from.floatValue())/(to.floatValue() - from.floatValue())*sensitivity);
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
	
	public T getFrom() { return from; }
	
	public void setTo(T to) { this.to = to; }
	
	public T getTo() { return to; }

	public T getInitial() { return initial; }

	public void setInitial(T initial) { this.initial = initial; }
	
	public int getCountTicks() { return countTicks; }
	
	public void setCountTicks(int countTicks) { this.countTicks = countTicks; }
	
	public String getUnitSign() { return unitSign; }
	
	public void setUnitSign(String unitSign) { this.unitSign = unitSign; }
	
	public float getMultiplier() { return multiplier; }
	
	public void setMultiplier(float multiplier) { this.multiplier = multiplier; }
	
	public int getSensitivity() { return sensitivity; }
	
	public float[] getPossibleTickLengths() { return possibleTickLengths; }
	
	@Override
	public MotionDimension<T> clone() {
		return new MotionDimension<T>(name, from, to, initial);
	}
	
	public MotionDimensionLabel createTick(Number value) {
		return new MotionDimensionLabel(Math.round(Math.abs(value.floatValue())*multiplier) + unitSign);
	}
	
	public void putTick(Number value) {
		labelTable.put(getRevertedValue(value.floatValue()), createTick(value));
	}
	
	public void createLabelTable() {
		labelTable = new Hashtable<Integer, JLabel>();
		float fromF = from.floatValue(), toF = to.floatValue();
		float min = Math.min(fromF, toF); float max = Math.max(fromF, toF);
		int tickIndex = 0;
		for(; tickIndex < possibleTickLengths.length; tickIndex++) {
			float tickLength = possibleTickLengths[tickIndex];
			float minOffset = min > 0 ? tickLength - min % tickLength: (-min) % tickLength;
			float current = min + minOffset;
			int commonTicksLengthOnPanel = 0;
			while(current <= max) { commonTicksLengthOnPanel += createTick(current).getPreferredSize().width + minTicksSpaceRate*MotionDimensionSettingPanel.SLIDER_WIDTH; current += tickLength; }
			if(commonTicksLengthOnPanel < MotionDimensionSettingPanel.SLIDER_WIDTH) {
				current = min + minOffset;
				while(current <= max) { putTick(current); current += tickLength; }
				break;
			}
		}
		if(labelTable.size() <= 1) {
			putTick(min);
			putTick(max);
		}
	}
	
	public void updateLabelTable() {
		createLabelTable();
		super.setLabelTable(labelTable);
	}
	
	public void reset() {
		setFrom(initFrom);
		setTo(initTo);
		if(getModel().getMinimum() == nativeModel.getMinimum() && getModel().getMaximum() == nativeModel.getMaximum()) nativeModel.setValue(getValue());
		setModel(nativeModel);
		setIsSyncronized(false);
		updateLabelTable();
		revalidate();
		repaint();
	}
	
	public Object getId() { return id; }
	
	public void setId(Object id) { this.id = id; }
	
	public Object getMotionId() { return motionId; }
	
	public void setMotionId(Object motionId) { this.motionId = motionId; }
	
	public boolean getIsSynchronized() { return isSynchronized; }
	
	public void setIsSyncronized(boolean isSynchronized) { this.isSynchronized = isSynchronized; } 
}
