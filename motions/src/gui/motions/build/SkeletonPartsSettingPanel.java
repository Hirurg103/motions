package gui.motions.build;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import figures.SkeletonPart;
import gui.MainPanel;

public class SkeletonPartsSettingPanel extends JPanel {
	/** 
	 * 	Store all added by user parts for complex motion
	 */
	private static final long serialVersionUID = 953826532434207365L;
	private ArrayList<SkeletonPart> skeletonParts;
	private Component verticalStrut;

	public SkeletonPartsSettingPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		skeletonParts = new ArrayList<SkeletonPart>();
		add(Box.createVerticalStrut(12));					// top padding
		
		add(createHintsPanel());
		add(Box.createVerticalStrut(17));
		int verticalStrutHeight = MainPanel.MAIN_PANEL_HEIGHT - getPreferredSize().height;
		if(verticalStrutHeight < 0) verticalStrutHeight = 0;
		this.verticalStrut = Box.createVerticalStrut(verticalStrutHeight);
		add(verticalStrut);
	}
	
	public void addPart(SkeletonPart skeletonPart) {
		if(skeletonParts.contains(skeletonPart)) return; 	// custom motion cannot contain same part twice
		skeletonParts.add(skeletonPart);
		add(new SkeletonPartSettingPanel(skeletonPart));
		revalidateHeight();
	}
	
	public void revalidateHeight() {
		remove(verticalStrut);
		// reset vertical strut to prevent scratch interface by vertical
		int verticalStrutHeight = getParent().getParent().getPreferredSize().height - getPreferredSize().height;
		if(verticalStrutHeight  > 0) add(verticalStrut = Box.createVerticalStrut(verticalStrutHeight));
	}
	
	public void remove(SkeletonPartSettingPanel skeletonPartSetting) {
		super.remove(skeletonPartSetting);
		skeletonParts.remove(skeletonPartSetting.getSkeletonPart());
		revalidateHeight();
		revalidate();
		repaint();
	}
	
	private JPanel createHintsPanel() {
		JPanel hintsPanel = new JPanel();
		hintsPanel.setLayout(new BoxLayout(hintsPanel, BoxLayout.X_AXIS));
		hintsPanel.add(Box.createHorizontalStrut(12));
		
		JPanel dimensionsHintsPanel = new JPanel();
		dimensionsHintsPanel = new JPanel();
		dimensionsHintsPanel.setLayout(new BoxLayout(dimensionsHintsPanel, BoxLayout.X_AXIS));
		dimensionsHintsPanel.add(new JLabel("Dimension"));
		dimensionsHintsPanel.add(Box.createVerticalStrut(5));
		dimensionsHintsPanel.add(Box.createHorizontalGlue());
		dimensionsHintsPanel.add(new JLabel("Directions"));
		hintsPanel.add(dimensionsHintsPanel);
		hintsPanel.add(Box.createHorizontalGlue());
		hintsPanel.add(Box.createHorizontalStrut(5));
		
		hintsPanel.add(new JLabel("Wired"));
		hintsPanel.add(Box.createHorizontalStrut(5));
		
		JLabel current = new JLabel("Value", SwingConstants.CENTER);
		current.setPreferredSize(new Dimension(MotionDimensionSettingPanel.SLIDER_WIDTH, current.getPreferredSize().height));
		//current.setHorizontalTextPosition(SwingConstants.CENTER);
		hintsPanel.add(current);
		hintsPanel.add(Box.createHorizontalStrut(5));
		
		JLabel from = new JLabel("From", SwingConstants.CENTER);
		from.setPreferredSize(new Dimension(MotionDimensionSettingPanel.SLIDER_WIDTH, from.getPreferredSize().height));
		hintsPanel.add(from);
		hintsPanel.add(Box.createHorizontalStrut(5));
		
		JLabel to = new JLabel("To", SwingConstants.CENTER);
		to.setPreferredSize(new Dimension(MotionDimensionSettingPanel.SLIDER_WIDTH, to.getPreferredSize().height));
		hintsPanel.add(to);
		hintsPanel.add(Box.createHorizontalStrut(12));
		return hintsPanel;
	}
}
