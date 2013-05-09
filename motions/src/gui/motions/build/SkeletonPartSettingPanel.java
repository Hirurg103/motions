package gui.motions.build;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import figures.SkeletonPart;
import gui.dimensions.MotionDimension;

public class SkeletonPartSettingPanel extends JPanel {
	/** 
	 *  This class represents skeleton part on motion panel
	 */
	private static final long serialVersionUID = 6288333375323004623L;
	private SkeletonPartSettingPanel that;
	private SkeletonPart skeletonPart;

	public SkeletonPartSettingPanel(SkeletonPart skeletonPart) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		that = this;
		this.skeletonPart = skeletonPart;
		
		// show name of the skeleton part
		JPanel namePanel = new JPanel();
		JLabel name = new JLabel(skeletonPart.getName());
		namePanel.add(name);
		JLabel labelCancelPart = new JLabel(new ImageIcon("../images/close_part_icon.png"), SwingConstants.CENTER);
		labelCancelPart.addMouseListener(new CloseSkeletonPartMouseAdapter());
		namePanel.add(labelCancelPart);
		add(namePanel);
		
		for(MotionDimension motionDimension: skeletonPart.getMotionDimensions()) {
			add(new MotionDimensionSettingPanel(motionDimension));
			add(Box.createVerticalStrut(5));  						// distance between dimensions
		}
		add(Box.createVerticalStrut(17));                 			// create border between parts
	}
	
	public SkeletonPart getSkeletonPart() { return skeletonPart; }
	
	class CloseSkeletonPartMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			SkeletonPartsSettingPanel parent = (SkeletonPartsSettingPanel)that.getParent();
			parent.remove(that);
			for(MotionDimension motionDimension: skeletonPart.getMotionDimensions()) {
				motionDimension.reset();
			}
		}
	}
}
