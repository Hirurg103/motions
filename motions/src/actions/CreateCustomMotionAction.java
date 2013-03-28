package actions;

import java.awt.event.ActionEvent;

public class CreateCustomMotionAction extends CustomAction {
	
	public CreateCustomMotionAction() {
		super("Create custom motion");
		addActionListener(this);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);	
		System.out.println("Create custom motion");
	}
}