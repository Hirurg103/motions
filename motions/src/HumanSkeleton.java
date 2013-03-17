import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.media.opengl.GL2;
import types.Range;

import javax.vecmath.Color4b;
import javax.vecmath.Vector3f;

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
		this.root.addRotateDimension(new RotateDimension("Down-Up", new Vector3f(0, 0, 1), new Range<Integer>(-90, 90)));
		this.root.addRotateDimension(new RotateDimension("Left-Right", new Vector3f(0, 1, 0), new Range<Integer>(-90, 90)));
	    SkeletonPart elbow = new SkeletonPart("Elbow", 0.8f); skeletonParts.add(elbow);
	    elbow.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), 0, 0), elbow);
	    
	    SkeletonPart elbow1 = new SkeletonPart("Elbow1", 0.8f); skeletonParts.add(elbow1);
	    elbow1.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), -1, 0), elbow1);
	    
	    SkeletonPart elbow2 = new SkeletonPart("Elbow2", 0.8f); skeletonParts.add(elbow2);
	    elbow2.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), -2, 0), elbow2);
	    
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
