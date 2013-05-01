package gui;

import java.awt.Component;
import java.awt.Dimension;

import java.nio.FloatBuffer;
import java.util.HashMap;

import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseAdapter;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.event.awt.AWTAdapter;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.newt.event.awt.AWTMouseAdapter;

import figures.HumanSkeleton;
import figures.SkeletonPart;
import gui.motions.build.CreateMotionPanel;
import gui.motions.build.SkeletonPartsSettingPanel;

import static javax.media.opengl.GL.*;					// GL constants
import static javax.media.opengl.GL2.*;					// GL2 constants
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static java.awt.event.KeyEvent.*;

public class HumanCanvas extends GLCanvas implements GLEventListener, MouseListener, KeyListener {
	/** This class represents canvas on which performs drawing.
	 * 
	 */
	
	// Setup OpenGL Graphics Renderer
	private static final long serialVersionUID = 1L;
	private GLU glu;
	private static final int HUMAN_CANVAS_WIDTH = 640; 	// width of the drawable
	private HumanSkeleton humanSkeleton;                // human skeleton to draw
	private GL2 gl = null;
	private float translateX = 0; private float translateZ = 0;
	private float rotateX = 0; private float rotateY = 0;
	private float dragX = -1; private float dragY = -1;
	private float mouseSensitivity = 0.1f;
	private HashMap<String, MotionsAWTMouseAdapter<? extends MouseAdapter>> motionsAWTMouseAdapters;
	private AWTMouseAdapter currentAWTMouseAdapter = null;
	
	/** Constructors to setup the GUI for this Component */
	public HumanCanvas() {
		super(new GLCapabilities(GLProfile.getDefault()));
		
		this.setPreferredSize(new Dimension(HUMAN_CANVAS_WIDTH, MainPanel.MAIN_PANEL_HEIGHT));
		this.addGLEventListener(this);
		// new AWTMouseAdapter(this).addTo(this);
		new AWTKeyAdapter(this).addTo(this);
		this.motionsAWTMouseAdapters = new HashMap<String, MotionsAWTMouseAdapter<? extends MouseAdapter>>();
		this.motionsAWTMouseAdapters.put("Create motion", new MotionsAWTMouseAdapter<>(new CreateMotionMouseAdapter()));
		this.motionsAWTMouseAdapters.put("Create timeline", new MotionsAWTMouseAdapter<>(new CreateTimelineMouseAdapter()));
		setMouseAdapter("Create motion");
	}
	
	public HumanCanvas(Dimension dimension) {
		this();
		this.setPreferredSize(dimension);
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
	    gl.glMatrixMode(GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glColor3f(1.0f, 1.0f, 1.0f);
		humanSkeleton.draw(gl);
		
	    // move the camera
	    gl.glMatrixMode(GL_PROJECTION);
	    FloatBuffer pM = FloatBuffer.allocate(16);
	    gl.glGetFloatv(GL_PROJECTION_MATRIX, pM);
	    gl.glRotatef(rotateX, pM.get(0), pM.get(4), pM.get(8));
	    gl.glRotatef(rotateY, pM.get(1), pM.get(5), pM.get(9));
	    rotateX = 0; rotateY = 0;
	    gl.glTranslatef(translateX*pM.get(0), translateX*pM.get(4), translateX*pM.get(8));
	    gl.glTranslatef(translateZ*pM.get(3), translateZ*pM.get(7), translateZ*pM.get(11));
	    translateX = translateZ = 0;
	}
	
	 /**
	 * Called back before the OpenGL context is destroyed. Release resource such as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable arg0) {}

	/**
	 * Called back immediately after the OpenGL context is initialized. Can be used
	 * to perform one-time initialization. Run only once.
	 */
	 @Override
	 public void init(GLAutoDrawable drawable) {
		this.gl = drawable.getGL().getGL2();      				// get the OpenGL graphics context
		glu = new GLU();                         				// get GL Utilities
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 				// set background (clear) color
	    gl.glClearDepth(1.0f);      							// set clear depth value to farthest
	    gl.glEnable(GL_DEPTH_TEST); 							// enables depth testing
	    gl.glDepthFunc(GL_LEQUAL);  							// the type of depth test to do
	    gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 	// best perspective correction
	    gl.glShadeModel(GL_SMOOTH); 							// blends colors nicely, and smoothes out lighting
	     
	    // add human skeleton control panel
		this.humanSkeleton = new HumanSkeleton(); 
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
		gl.glTranslatef(0, 0, -3); 					// translate into scene
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); 							// reset
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case VK_W: translateZ = -0.01f; break;
			case VK_S: translateZ = 0.01f; break;
			case VK_D: translateX = -0.01f; break;
			case VK_A: translateX = 0.01f; break;
				
			case VK_UP: rotateX = -5; break;
			case VK_DOWN: rotateX = 5; break;
			case VK_RIGHT: rotateY = 5; break;
			case VK_LEFT: rotateY = -5; break;
			default: break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseDragged(MouseEvent e) { 
		if(dragX < 0 || dragY < 0) { dragX = e.getX(); dragY = e.getY(); return; }
		rotateX = mouseSensitivity*(dragY - e.getY());
		rotateY = mouseSensitivity*(dragX - e.getX());
		dragX = e.getX(); dragY = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mouseMoved(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) { }

	@Override
	public void mouseReleased(MouseEvent arg0) { dragX = dragY = -1; }

	@Override
	public void mouseWheelMoved(MouseEvent arg0) { }
	
	public AWTMouseAdapter getCurrentAdapter() { return currentAWTMouseAdapter; }

	public void setCurrentAdapter(AWTMouseAdapter currentAWTMouseAdapter) { this.currentAWTMouseAdapter = currentAWTMouseAdapter; }
	
	
	public void setMouseAdapter(String name) {
		if(currentAWTMouseAdapter != null) currentAWTMouseAdapter.removeFrom(this);
		currentAWTMouseAdapter = motionsAWTMouseAdapters.get(name);
		if(currentAWTMouseAdapter != null) currentAWTMouseAdapter.addTo(this);
		
	}
	
	public class MotionsAWTMouseAdapter<T extends MouseAdapter & Initializable> extends AWTMouseAdapter {
		private T mouseAdapter;
		
		public MotionsAWTMouseAdapter(T mouseAdapter) {
			super(mouseAdapter);
			this.mouseAdapter = mouseAdapter;
		}

		@Override
		public AWTAdapter addTo(Component component) {
			super.addTo(component);
			if(mouseAdapter != null) mouseAdapter.initialize();
			return this;
		}
		
		@Override
		public AWTAdapter removeFrom(Component component) {
			super.removeFrom(component);
			if(mouseAdapter != null) mouseAdapter.destroy();
			return this;
		}
	}
	
	public interface Initializable { public void initialize(); public void destroy(); }
	
	public class MotionsMouseAdapter extends MouseAdapter implements Initializable {
		@Override
		public void initialize() { }
		
		@Override
		public void destroy() { }
	}
	
	public class CreateMotionMouseAdapter extends MotionsMouseAdapter {
		protected SkeletonPartsSettingPanel skeletonPartsSettingPanel = null;
		
		public CreateMotionMouseAdapter() {
			super();
			skeletonPartsSettingPanel = CreateMotionPanel.skeletonPartsSettingPanel;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			getContext().makeCurrent();
			SkeletonPart activeSkeletonPart = humanSkeleton.getActiveSkeletonPart(gl, e.getX(), e.getY());
			getContext().release();
			if(activeSkeletonPart == null) return;
			skeletonPartsSettingPanel.addPart(activeSkeletonPart);
			skeletonPartsSettingPanel.revalidate(); 
			skeletonPartsSettingPanel.repaint(); 
		}
		
		@Override
		public void initialize() { }
		
		@Override
		public void mouseDragged(MouseEvent e) { }
	}
	
	public class CreateTimelineMouseAdapter extends MotionsMouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("CreateTimelineMouseAdapter");
		}
	}
}