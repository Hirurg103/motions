import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.media.opengl.GL2;

import javax.vecmath.Color4b;

import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2ES1.GL_FOG;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;

public class HumanSkeleton {
	private SkeletonPart root;
	private ArrayList<SkeletonPart> skeletonParts;
	
	public HumanSkeleton(JPanel controlPanel) {
		this.skeletonParts = new ArrayList<SkeletonPart>();
		
		this.root = new SkeletonPart("Shoulder", 1); skeletonParts.add(root);
		
//		this.root.addRotateDimension(new RotateDimension("Left-Right", new Vector3f(0, 1, 0), -30, 170, 0));
//		this.root.addRotateDimension(new RotateDimension("Down-Up", new Vector3f(0, 0, 1), -90, 90, 0));
//		
//		this.root.addRotateDimension(new RotateDimension("Curl", new Vector3f(1, 0, 0), -90, 90, 0));
//		
//	    SkeletonPart elbow = new SkeletonPart("Elbow", 0.8f); skeletonParts.add(elbow);
//	    elbow.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), 0, 170, 0));
//	    root.addChildPart(elbow);
//	    
//	    SkeletonPart hand = new SkeletonPart("Hand", 0.3f); skeletonParts.add(hand);
//	    hand.addRotateDimension(new RotateDimension("Left-Right", new Vector3f(0, 1, 0), -90, 80, 0));
//	    hand.addRotateDimension(new RotateDimension("Down-Up", new Vector3f(0, 0, 1), -45, 45, 0));
//	    elbow.addChildPart(hand);
	    
	}
	
	public HumanSkeleton() {
		this(null);
	}
	
	public void draw(GL2 gl) {
	    this.root.draw(gl);
	}
	
	public SkeletonPart getActiveSkeletonPart(GL2 gl, int x, int y) {
		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		gl.glDisable(GL_TEXTURE_2D);
		gl.glDisable(GL_FOG);
		gl.glDisable(GL_LIGHTING);
		root.draw(gl, true);
		IntBuffer viewPort = IntBuffer.allocate(4);
		gl.glGetIntegerv(GL_VIEWPORT, viewPort);
		ByteBuffer pixelColor = ByteBuffer.allocate(4);
		gl.glReadPixels(x, viewPort.get(3) - y, 1, 1, GL_RGBA, GL_BYTE, pixelColor);
		return root.getWithColor(new Color4b(pixelColor.array()));
	}
}
