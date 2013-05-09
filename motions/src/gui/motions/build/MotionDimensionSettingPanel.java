package gui.motions.build;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.dimensions.MotionDimension;

public class MotionDimensionSettingPanel extends JPanel {
	
	/**
	 * 	This class represents motion dimension setting controls on skeleton part setting panel.
	 */
	private static final long serialVersionUID = 7551262022885146277L;
	private MotionDimension motionDimension;
	private JCheckBox isSynchronized;
	private MotionDimension from;
	private MotionDimension to;
	public static int SLIDER_WIDTH = 100;
	private static BoundedRangeModel commonModel = null;
	private static int countSynchronized = 0;
	
	public MotionDimensionSettingPanel(MotionDimension motionDimension) {
		super();
		this.motionDimension = motionDimension;
		String[] dimensionCharacteristics = motionDimension.getName().split(":");
		String dimensionName = dimensionCharacteristics[0];
		String[] dimensionDirections = dimensionCharacteristics[1].split("-");
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalStrut(12));
		JPanel dimensionCharacteristicsPanel = new JPanel();
		dimensionCharacteristicsPanel.setLayout(new BoxLayout(dimensionCharacteristicsPanel, BoxLayout.X_AXIS));
		dimensionCharacteristicsPanel.add(new JLabel(dimensionName + ":"));
		dimensionCharacteristicsPanel.add(Box.createHorizontalStrut(5));
		dimensionCharacteristicsPanel.add(Box.createHorizontalGlue());
		dimensionCharacteristicsPanel.add(new JLabel(dimensionDirections[0] + '/' + dimensionDirections[1]));
		add(dimensionCharacteristicsPanel);
		add(Box.createHorizontalGlue());
		add(Box.createHorizontalStrut(5));

		isSynchronized = new JCheckBox();
		isSynchronized.addMouseListener(new SynchronizeMouseAdapter());
		
		add(isSynchronized);
		add(Box.createHorizontalStrut(5));
		
		this.motionDimension = motionDimension;
		add(motionDimension);
		motionDimension.setPreferredSize(new Dimension(SLIDER_WIDTH, motionDimension.getPreferredSize().height));
		motionDimension.setMaximumSize(new Dimension(SLIDER_WIDTH, motionDimension.getPreferredSize().height));
		motionDimension.setMinimumSize(new Dimension(SLIDER_WIDTH, motionDimension.getPreferredSize().height));
		add(Box.createHorizontalStrut(5));
		
		add(from = motionDimension.clone());
		from.setPreferredSize(new Dimension(SLIDER_WIDTH, from.getPreferredSize().height));
		from.setMaximumSize(new Dimension(SLIDER_WIDTH, from.getPreferredSize().height));
		from.setMinimumSize(new Dimension(SLIDER_WIDTH, from.getPreferredSize().height));
		from.setEnabled(false);
		from.addChangeListener(new FromChangeListener());
		from.setValue(from.getMinimum());
		add(Box.createHorizontalStrut(5));
		
		add(to = motionDimension.clone());
		to.setPreferredSize(new Dimension(SLIDER_WIDTH, to.getPreferredSize().height));
		to.setMaximumSize(new Dimension(SLIDER_WIDTH, to.getPreferredSize().height));
		to.setMinimumSize(new Dimension(SLIDER_WIDTH, to.getPreferredSize().height));
		to.setEnabled(false);
		to.addChangeListener(new ToChangeListener());
		to.setValue(to.getMaximum());
		
		add(Box.createHorizontalStrut(12));
	}
	
	class SynchronizeMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JCheckBox isSynchronized = (JCheckBox)(e.getSource());  
			if(isSynchronized.isSelected()) {
				if(MotionDimensionSettingPanel.commonModel == null) MotionDimensionSettingPanel.commonModel = new DefaultBoundedRangeModel(motionDimension.getValue(), motionDimension.getExtent(), motionDimension.getMinimum(), motionDimension.getMaximum());
				motionDimension.setModel(MotionDimensionSettingPanel.commonModel);
				motionDimension.setIsSyncronized(true);
				from.setEnabled(true);
				from.getChangeListeners()[0].stateChanged(new ChangeEvent(from));
				to.setEnabled(true);
				to.getChangeListeners()[0].stateChanged(new ChangeEvent(to));
				countSynchronized++;
			} else {
				motionDimension.reset();
				from.setEnabled(false);
				to.setEnabled(false);
				if(--countSynchronized == 0) MotionDimensionSettingPanel.commonModel = null;
			}
		}
	}
	
	class FromChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			MotionDimension from = (MotionDimension)e.getSource();
			motionDimension.setFrom(from.getConvertedValue());
		}
	}
	
	class ToChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			MotionDimension to = (MotionDimension)e.getSource();
			motionDimension.setTo(to.getConvertedValue());
		}
	}
	
	public MotionDimension getMotionDimension() { return motionDimension; }
}
