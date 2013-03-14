import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

import com.jogamp.opengl.util.FPSAnimator;

 
@SuppressWarnings("serial")
public class Main {
	// Define constants for the top-level container
	private static String TITLE = "Human motion";  // window's title

	/** The entry main() method to setup the top-level container and canvas */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				// Panel to display human controls such as sliders
//				JPanel controlPanel = new JPanel();
//				
//				// Create the OpenGL rendering canvas
//				HumanCanvas canvas = new HumanCanvas();
//				canvas.setControlPanel(controlPanel);
//				controlPanel.setPreferredSize(new Dimension(200, 480));
//				
//				final FPSAnimator animator = canvas.animator();
//             
//				final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
//				JPanel panel = new JPanel();
//				panel.add(canvas);
//				panel.add(controlPanel);
//				
//				frame.getContentPane().add(panel);
//				frame.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosing(WindowEvent e) {
//						// Use a dedicate thread to run the stop() to ensure that the
//						// animator stops before program exits.
//						new Thread() {
//							@Override
//							public void run() {
//								if (animator.isStarted()) animator.stop();
//								System.exit(0);
//							}
//						}.start();
//					}
//				});
//				frame.setTitle(TITLE);
//				frame.pack();
//            	frame.setVisible(true);
//            	animator.start(); // start the animation loop
//         	}
//		});
		Matrix4f m = new Matrix4f();
		m.set(1);
		System.out.println("start");
		for(int i = 1; i < 100000; i++) {
			m.setRotation(new AxisAngle4f((float)Math.sqrt(i)/i,(float)Math.sqrt(i)/i,(float)Math.sqrt(i)/i, (float)Math.PI/3));
		}
		System.out.println("end");
		System.out.println(m.toString());
	}
}