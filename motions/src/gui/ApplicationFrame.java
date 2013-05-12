package gui;

import gui.timeline.TimelinePanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import db.DatabaseUtils;
import db.FirebirdConnection;

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
		JPanel timelinePanel = new TimelinePanel();
		JScrollPane timelineScrollPane = new JScrollPane(timelinePanel);
		getContentPane().add(timelineScrollPane, "South");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// Animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if(mainPanel.getFPSAnimator().isStarted()) mainPanel.getFPSAnimator().stop();
						System.out.println("Closing database connection...");
						DatabaseUtils.close(FirebirdConnection.getInstance());
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
