package gui;

import gui.actions.CreateMotionAction;
import gui.actions.CreateTimelineAction;

import javax.swing.Box;
import javax.swing.JToolBar;

public class ApplicationToolbar extends JToolBar {
	/**
	 * 	Application tool bar to switch program mode	
	 */
	private static final long serialVersionUID = -5404092669483233843L;
	
	public ApplicationToolbar() {
		// create instrument panel
		super("Tools", JToolBar.HORIZONTAL);
		add(new CreateMotionAction());
		add(Box.createHorizontalStrut(2));
		add(new CreateTimelineAction());
	}
}
