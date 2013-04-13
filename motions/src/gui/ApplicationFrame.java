package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ApplicationFrame extends JFrame {
	// Define constants for the top-level container
	private static String TITLE = "Human motion";  // window's title 
	private final MainPanel mainPanel;
	private final ApplicationToolbar applicationToolbar;
		
	/**
	 * 	Main application frame
	 */
	private static final long serialVersionUID = 7429012575959712724L;

	public ApplicationFrame() {
		super();
		mainPanel = new MainPanel();
		getContentPane().add(mainPanel);
		applicationToolbar = new ApplicationToolbar();
		getContentPane().add(applicationToolbar, "North");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// Animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if(mainPanel.getFPSAnimator().isStarted()) mainPanel.getFPSAnimator().stop();
						System.exit(0);
					}
				}.start();
			}
		});
		
		setTitle(TITLE);
		pack();
    	setVisible(true);
    	
    	// start the animation loop
    	mainPanel.getFPSAnimator().start();
	}
	
	public MainPanel getMainPanel() { return mainPanel; }
	
	public ApplicationToolbar getApplicationToolbar() { return applicationToolbar; }

}
