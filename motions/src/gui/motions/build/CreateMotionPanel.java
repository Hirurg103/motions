package gui.motions.build;

import gui.MainPanel;
import gui.RightPane;
import gui.dimensions.MotionDimension;
import gui.motions.store.StoreMotionsPanel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreateMotionPanel extends JPanel {
	/**
	 * 	This class represents custom motion creation.
	 */
	private static final long serialVersionUID = 6559571653667296936L;
	public static SkeletonPartsSettingPanel skeletonPartsSettingPanel = new SkeletonPartsSettingPanel();

	public CreateMotionPanel() { 
		super();
		JScrollPane skeletonPartsSettingScrollPane = new JScrollPane();
		skeletonPartsSettingScrollPane.setViewportView(skeletonPartsSettingPanel);
		skeletonPartsSettingScrollPane.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, MainPanel.MAIN_PANEL_HEIGHT - 39 - 35));
		add(skeletonPartsSettingScrollPane);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createHorizontalStrut(12));
		buttonsPanel.setPreferredSize(new Dimension(RightPane.RIGHT_PANE_WIDTH - 2, 25));
		JButton buttonSave = new JButton("Save");
		buttonSave.addMouseListener(new SaveButtonMouseListener());
		buttonsPanel.add(buttonSave);
		add(buttonsPanel);
	}
	
	public class SaveButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			ArrayList<MotionDimension> motionDimensions = new ArrayList<MotionDimension>();
			for(Component component : skeletonPartsSettingPanel.getComponents()) {
				if(component instanceof SkeletonPartSettingPanel) {
					for(Component innerComponent : ((SkeletonPartSettingPanel) component).getComponents()) {
						if(innerComponent instanceof MotionDimensionSettingPanel) {
							MotionDimensionSettingPanel motionDimensionSettingPanel = (MotionDimensionSettingPanel)innerComponent;
							motionDimensions.add(motionDimensionSettingPanel.getMotionDimension());
						}
					}
				}
			}
			StoreMotionsPanel.setMotionDimensions(motionDimensions);
			MainPanel.getRightPanel().setSelectedIndex(1);
			StoreMotionsPanel.getStoreMotionsControlPanel().setVisible(true); 
			StoreMotionsPanel.getTextFieldNewMotionName().setText("New motion");
			StoreMotionsPanel.getTextFieldNewMotionName().grabFocus();
		}
	}
}
