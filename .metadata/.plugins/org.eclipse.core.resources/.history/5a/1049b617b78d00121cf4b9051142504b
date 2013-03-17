import javax.swing.JPanel;
import javax.media.opengl.GL2;
import types.Range;
import javax.vecmath.Vector3f;

public class HumanSkeleton {
	private SkeletonPart root;
	
	public HumanSkeleton(JPanel controlPanel) {
		this.root = new SkeletonPart("Shoulder", 1);
		this.root.addRotateDimension(new RotateDimension("Down-Up", new Vector3f(0, 0, 1), new Range<Integer>(-90, 90)));
		this.root.addRotateDimension(new RotateDimension("Left-Right", new Vector3f(0, 1, 0), new Range<Integer>(-90, 90)));
	    SkeletonPart elbow = new SkeletonPart("Elbow", 0.8f);
	    elbow.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), 0, 0), elbow);
	    
	    SkeletonPart elbow1 = new SkeletonPart("Elbow1", 0.8f);
	    elbow1.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), -1, 0), elbow1);
	    
	    SkeletonPart elbow2 = new SkeletonPart("Elbow2", 0.8f);
	    elbow2.addRotateDimension(new RotateDimension("Bend-Unbend", new Vector3f(0, 0, 1), new Range<Integer>(0, 170)));
	    root.addChildPart(new Vector3f(this.root.getLength(), -2, 0), elbow2);
	    
	}
	
	public HumanSkeleton() {
		this(null);
	}
	
	public void draw(GL2 gl) {
		gl.glLineWidth(100);
	    this.root.draw(gl);
	}
	
	public SkeletonPart getActiveSkeletonPart() {
		return this.root;
	}
}
