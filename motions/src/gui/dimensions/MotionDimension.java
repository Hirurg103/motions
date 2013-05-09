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


public class MotionDimension extends JSlider implements ChangeListener, Cloneable {
	/** 
	 * 	This class represents arbitrary dimension model.
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	private ArrayList<DimensionListener> dimensionListeners;
	protected float initFrom;
	protected float from;
	protected float initTo;
	protected float to;
	protected float initial;
	protected float current;
	protected BoundedRangeModel nativeModel;
	protected int sensitivity = 360;
	protected float[] possibleTickLengths = {1};
	protected String unitSign = "";
	protected float multiplier = 1.0f;
	protected Hashtable<Integer, JLabel> labelTable;
	protected float minBoundaryOffset = 0.75f;
	protected float minTicksSpaceRate = 0.05f;
	protected Object id;
	protected Object motionId;
	protected boolean isSynchronized = false;
	
	public MotionDimension(String name, float from, float to, float initial) {
		super();
		setMinimum(0);
		setMaximum(sensitivity);
		setValue((int)(sensitivity*(initial - from)/(to - from)));
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
		setPaintLabels(true);
	}
	
	public void setName(String name) { this.name = name; }
	
	public String getName() { return name; }
	
	public float getConvertedValue() {
		return from + (to - from)*getValue()/sensitivity;
	}
	
	public int getRevertedValue(float value) {
		if(from < to) { 
			if(value < from) return 0;
			if(value > to) return sensitivity;
		} else {
			if(value > from) return 0;
			if(value < to) return sensitivity;
		}
		return (int)((value - from)/(to - from)*sensitivity);
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
		
	@Override
	public MotionDimension clone() {
		MotionDimension clon = new MotionDimension(name, from, to, initial);
		clon.setPossibleTickLengths(getPossibleTickLengths());
		clon.setUnitSign(getUnitSign());
		clon.setMultiplier(getMultiplier());
		clon.setId(getId());
		clon.setMotionId(getMotionId());
		if(getIsSynchronized()) {
			clon.setIsSyncronized(true);
			clon.setModel(getModel());
		}
		return clon;
	}
	
	public MotionDimensionLabel createTick(Number value) {
		return new MotionDimensionLabel(Math.round(Math.abs(value.floatValue())*multiplier) + unitSign);
	}
	
	public void putTick(Number value) {
		labelTable.put(getRevertedValue(value.floatValue()), createTick(value));
	}
	
	public void createLabelTable() {
		labelTable = new Hashtable<Integer, JLabel>();
		float fromF = from, toF = to;
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
		revalidate();
		repaint();
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
		
	public void setFrom(float from) { this.from = from; updateLabelTable(); }
	
	public float getFrom() { return from; }
	
	public void setTo(float to) { this.to = to; updateLabelTable(); }
	
	public float getTo() { return to; }

	public float getInitial() { return initial; }

	public void setInitial(float initial) { this.initial = initial; }
	
	public String getUnitSign() { return unitSign; }
	
	public void setUnitSign(String unitSign) { this.unitSign = unitSign; updateLabelTable(); }
	
	public float getMultiplier() { return multiplier; }
	
	public void setMultiplier(float multiplier) { this.multiplier = multiplier; updateLabelTable(); }
	
	public int getSensitivity() { return sensitivity; }
	
	public float[] getPossibleTickLengths() { return possibleTickLengths; }
	
	public void setPossibleTickLengths(float[] possibleTickLengths) { this.possibleTickLengths = possibleTickLengths; updateLabelTable(); }

	public Object getId() { return id; }
	
	public void setId(Object id) { this.id = id; }
	
	public Object getMotionId() { return motionId; }
	
	public void setMotionId(Object motionId) { this.motionId = motionId; }
	
	public boolean getIsSynchronized() { return isSynchronized; }
	
	public void setIsSyncronized(boolean isSynchronized) { this.isSynchronized = isSynchronized; } 
}
