package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jogamp.opengl.util.FPSAnimator;

public class MainPanel extends JPanel {
	/**
	 *  Main application frame
	 */
	private static final long serialVersionUID = 4842055135769775781L;
	private final HumanCanvas humanCanvas; // display human skeleton
	private static RightPane rightPanel; // Panel to display human controls such as sliders
	private final FPSAnimator fpsAnimator;
	public static int MAIN_PANEL_HEIGHT = 480;

	public MainPanel() {
		super();
		// Create the OpenGL rendering canvas
		humanCanvas = new HumanCanvas();
		
		// create right panel
		rightPanel = new RightPane();
		
		// Create a animator that drives human canvas' display() at the specified FPS.
		fpsAnimator = new FPSAnimator(humanCanvas, 60, true);

		add(humanCanvas);
		add(rightPanel);
	}
	
	public FPSAnimator getFPSAnimator() { return fpsAnimator; }
	
	public HumanCanvas getHumanCanvas() { return humanCanvas; }
	
	public static JTabbedPane getRightPanel() { return rightPanel; }
}
