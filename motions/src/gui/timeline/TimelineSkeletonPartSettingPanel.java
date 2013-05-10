package gui.timeline;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import figures.SkeletonPart;


public class TimelineSkeletonPartSettingPanel extends JPanel {

	/**
	 * 	Allows settings for skeleton part on time line.
	 */
	private static final long serialVersionUID = 2906823868375425213L;
	
	private JLabel skeletonPartLabel;
	private final int SKELETON_PART_LABEL_WIDTH;
	private final int SKELETON_PART_LABEL_HEIGHT;
	
	public TimelineSkeletonPartSettingPanel(SkeletonPart skeletonPart) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		skeletonPartLabel = new JLabel(skeletonPart.getName());
		skeletonPartLabel.setFont(new Font("Geneva", Font.BOLD, 10));
		SKELETON_PART_LABEL_WIDTH = skeletonPartLabel.getPreferredSize().width;
		SKELETON_PART_LABEL_HEIGHT = skeletonPartLabel.getPreferredSize().height;
		add(skeletonPartLabel);
		add(Box.createVerticalStrut(5));
	}
	
	// TODO: delete this constructor for demo
	public TimelineSkeletonPartSettingPanel(String skeletonPart) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		skeletonPartLabel = new JLabel(skeletonPart);
		skeletonPartLabel.setFont(new Font("Geneva", Font.BOLD, 10));
		SKELETON_PART_LABEL_WIDTH = skeletonPartLabel.getPreferredSize().width;
		SKELETON_PART_LABEL_HEIGHT = skeletonPartLabel.getPreferredSize().height;
		add(skeletonPartLabel);
		add(Box.createVerticalStrut(5));
	}
	
	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		skeletonPartLabel.setBounds(getVisibleRect().x, 0, SKELETON_PART_LABEL_WIDTH, SKELETON_PART_LABEL_HEIGHT);
		g.drawLine(0, SKELETON_PART_LABEL_HEIGHT, TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth, SKELETON_PART_LABEL_HEIGHT);
		g.drawLine(0, SKELETON_PART_LABEL_HEIGHT + 2, TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth, SKELETON_PART_LABEL_HEIGHT + 2);
	}
}
