import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.HumanCanvas;

import actions.*;

import com.jogamp.opengl.util.FPSAnimator;


 
@SuppressWarnings("serial")
public class Main {
	// Define constants for the top-level container
	private static String TITLE = "Human motion";  // window's title
	private static JFrame instrumentPanel = null;  // Panel contains buttons to change program mode 

	/** The entry main() method to setup the top-level container and canvas */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Panel to display human controls such as sliders
				JPanel controlPanel = new JPanel();
				
				// Create the OpenGL rendering canvas
				final HumanCanvas canvas = new HumanCanvas();
				canvas.setControlPanel(controlPanel);
				controlPanel.setPreferredSize(new Dimension(200, 480));
				
				final FPSAnimator animator = canvas.getAnimator();
             
				final JFrame frame = new JFrame();  // Swing's JFrame or AWT's Frame
				JPanel panel = new JPanel();
				panel.add(canvas);
				panel.add(controlPanel);
				
				frame.getContentPane().add(panel);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// Use a dedicate thread to run the stop() to ensure that the
						// Animator stops before program exits.
						new Thread() {
							@Override
							public void run() {
								if (animator.isStarted()) animator.stop();
								System.exit(0);
							}
						}.start();
					}
				});
				frame.setTitle(TITLE);
				JToolBar toolbar = new JToolBar("Tools", JToolBar.HORIZONTAL); // create instrument panel
				CustomAction.humanCanvas = canvas;
            	toolbar.add(new CreateMotionAction());
            	toolbar.add(Box.createHorizontalStrut(2));
            	toolbar.add(new CreateTimelineAction());
            	frame.getContentPane().add(toolbar, "North");
				frame.pack();
            	frame.setVisible(true);
            	animator.start(); // start the animation loop
         	}
		});
	}		
}