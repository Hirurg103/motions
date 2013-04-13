package gui;

import java.awt.Dimension;

import gui.motions.CreateMotionPanel;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RightPane extends JTabbedPane implements ChangeListener {
	/**
	 * 	Right panel shows create motion, motions and timelines tabs 
	 */
	private static final long serialVersionUID = 6562729020471545125L;
	private CreateMotionPanel createMotionPanel;
	private JPanel storeMotionsPanel;
	private JPanel storeTimelinesPanel;
	public static int RIGHT_PANE_WIDTH = 620;

	public RightPane() {
		super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		setPreferredSize(new Dimension(RIGHT_PANE_WIDTH, MainPanel.MAIN_PANEL_HEIGHT));
		createMotionPanel = new CreateMotionPanel();
		createMotionPanel.setName("Create motion");
		addTab("Create motion", new ImageIcon("../images/create_motion_icon.jpeg"), createMotionPanel, "<Alt> + <N>");
		setMnemonicAt(0, 78);
		JPanel storeMotionsPanel = new JPanel();
		storeMotionsPanel.setName("Motions store");
		addTab("Motions store", null, storeMotionsPanel, "<Alt> + <M>");
		setMnemonicAt(1, 77);
		JPanel storeTimelinesPanel = new JPanel();
		storeTimelinesPanel.setName("Timelines store");
		addTab("Timelines", null, storeTimelinesPanel, "<Alt> + <T>");
		setMnemonicAt(2, 84);
		addChangeListener(this);
	}
	
	public void stateChanged(ChangeEvent e) {
		MainPanel mainPanel = GUIUtils.getApplicationFrame(this).getMainPanel();
		mainPanel.getHumanCanvas().setMouseAdapter(getSelectedComponent().getName());
	}

	public CreateMotionPanel getCreateMotionPanel() { return createMotionPanel; }

	public JPanel getStoreMotionsPanel() { return storeMotionsPanel; }

	public void setStoreMotionsPanel(JPanel storeMotionsPanel) { this.storeMotionsPanel = storeMotionsPanel; }

	public JPanel getStoreTimelinesPanel() { return storeTimelinesPanel; }

	public void setStoreTimelinesPanel(JPanel storeTimelinesPanel) { this.storeTimelinesPanel = storeTimelinesPanel; }
}
