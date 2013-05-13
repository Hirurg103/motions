package gui.timeline;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

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
	private HashMap<Object, TimelineDimensionSettingPanel> timelineDimensionSettingPanels;
	private Component bottomMargin = Box.createVerticalStrut(12);

	public TimelineSkeletonPartSettingPanel(SkeletonPart skeletonPart) {
		super();
		timelineDimensionSettingPanels = new HashMap<Object, TimelineDimensionSettingPanel>();
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
		super();
		timelineDimensionSettingPanels = new HashMap<Object, TimelineDimensionSettingPanel>();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		skeletonPartLabel = new JLabel(skeletonPart);
		skeletonPartLabel.setFont(new Font("Geneva", Font.BOLD, 10));
		SKELETON_PART_LABEL_WIDTH = skeletonPartLabel.getPreferredSize().width;
		SKELETON_PART_LABEL_HEIGHT = skeletonPartLabel.getPreferredSize().height;
		add(skeletonPartLabel);
		add(Box.createVerticalStrut(5));
		add(bottomMargin);
	}

	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		skeletonPartLabel.setBounds(getVisibleRect().x, 0, SKELETON_PART_LABEL_WIDTH, SKELETON_PART_LABEL_HEIGHT);
		// TODO: draw double line separator or no?
		/*g.drawLine(0, SKELETON_PART_LABEL_HEIGHT, TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth, SKELETON_PART_LABEL_HEIGHT);
		g.drawLine(0, SKELETON_PART_LABEL_HEIGHT + 2, TimelineDimensionSettingPanel.timelineDimensionSettingPanelWidth, SKELETON_PART_LABEL_HEIGHT + 2);*/
	}

	public void addTimelineMotionDimension(TimelineMotionDimension timelineMotionDimension) {
		if(timelineDimensionSettingPanels.get(timelineMotionDimension.getId()) == null) {
			TimelineDimensionSettingPanel timelineDimensionSettingPanel = new TimelineDimensionSettingPanel(timelineMotionDimension);
			timelineDimensionSettingPanels.put(timelineMotionDimension.getId(), timelineDimensionSettingPanel);
			remove(bottomMargin);
			add(timelineDimensionSettingPanel);
			add(bottomMargin);
		}
		timelineDimensionSettingPanels.get(timelineMotionDimension.getId()).add(timelineMotionDimension);
	}
}
