import java.awt.Dimension;
import figures.Prism;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*;					// GL constants
import static javax.media.opengl.GL2.*;					// GL2 constants

public class HumanCanvas extends GLCanvas implements GLEventListener, MouseListener {
	/**
	 * 
	 */
	
	// Setup OpenGL Graphics Renderer
	private static final long serialVersionUID = 1L;
	private GLU glu;
	private FPSAnimator animator;  						// for the GL Utility
	private static final int CANVAS_WIDTH = 640;  		// width of the drawable
	private static final int CANVAS_HEIGHT = 480; 		// height of the drawable
	private static final int FPS = 60; 					// animator's target frames per second
	private HumanSkeleton humanSkeleton;                // human skeleton to draw
	private JPanel controlPanel = null;                        // panel to display human controls such as sliders
	
	/** Constructors to setup the GUI for this Component */
	public HumanCanvas() {
		this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		this.animator = new FPSAnimator(this, FPS, true);
		this.addGLEventListener(this);
		this.addMouseListener(this);
	}
	
	public HumanCanvas(Dimension dimension) {
		this();
		this.setPreferredSize(dimension);
	}
	
	public HumanCanvas(Dimension dimension, int fps) {
		this(dimension);
		// Create a animator that drives canvas' display() at the specified FPS.
		this.animator = new FPSAnimator(this, fps, true);
	}
	
	 
	// ------ Implement methods declared in GLEventListener ------

	/**
	 * Called back by the animator to perform rendering.
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		// Get the OpenGL graphics context
	    GL2 gl = drawable.getGL().getGL2();
	    // Clear the color and the depth buffers
	    gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	    // Reset the view (x, y, z axes back to normal)
	    gl.glLoadIdentity();
	    gl.glMatrixMode(GL_MODELVIEW);
	    gl.glColor3f(255, 255, 10);
	    humanSkeleton.draw(gl);
	}
	
	 /**
	 * Called back before the OpenGL context is destroyed. Release resource such as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable arg0) {
		
		
	}

	/**
	 * Called back immediately after the OpenGL context is initialized. Can be used
	 * to perform one-time initialization. Run only once.
	 */
	 @Override
	 public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();      		// get the OpenGL graphics context
		glu = new GLU();                         		// get GL Utilities
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 		// set background (clear) color
	    gl.glClearDepth(1.0f);      					// set clear depth value to farthest
	    gl.glEnable(GL_DEPTH_TEST); 					// enables depth testing
	    gl.glDepthFunc(GL_LEQUAL);  					// the type of depth test to do
	    gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
	    gl.glShadeModel(GL_SMOOTH); 					// blends colors nicely, and smoothes out lighting
	     
	    // add human skeleton control panel
		this.humanSkeleton = new HumanSkeleton(controlPanel); 
	 }

	/**
	 * Call-back handler for window re-size event. Also called when the drawable is
	 * first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();  			// get the OpenGL 2 graphics context
 
		if (height == 0) height = 1;   					// prevent divide by zero
		float aspect = (float)width / height;
 
		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);
 
		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION);  				// choose projection matrix
		gl.glLoadIdentity();             				// reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); 	// fovy, aspect, zNear, zFar
		gl.glTranslatef(0, 0, -20);
		gl.glRotatef(-30, 0, 1, 0);
		//gl.glRotatef(30, 1, 0, 0;
 
		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); 							// reset
	}
	
	public FPSAnimator animator() {
		return this.animator;
	}
	
	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public void mouseClicked(MouseEvent arg0) {
		SkeletonPart activeSkeletonPart = this.humanSkeleton.getActiveSkeletonPart();
		for(RotateDimension rotateDimension : activeSkeletonPart.getRotateDimensions()) {
			controlPanel.add(rotateDimension);
		}
		controlPanel.revalidate();
	}

	public void mouseDragged(MouseEvent arg0) { }

	public void mouseEntered(MouseEvent arg0) { }

	public void mouseExited(MouseEvent arg0) { }

	public void mouseMoved(MouseEvent arg0) { }

	public void mousePressed(MouseEvent arg0) { }

	public void mouseReleased(MouseEvent arg0) { }

	public void mouseWheelMoved(MouseEvent arg0) { }
}
