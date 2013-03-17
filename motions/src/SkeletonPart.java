import java.util.ArrayList;
import java.util.HashMap; 

import javax.media.opengl.GL2;
import javax.vecmath.Color4b;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

import figures.Prism;


public class SkeletonPart extends GraphicsObject implements UpdateListener {
	private String name;
	private float length;
	private ArrayList<RotateDimension> rotateDimensions;
	private Prism bone;
	private HashMap<Vector3f, SkeletonPart> childParts;
	private Matrix4f worldMatrix;
	private SkeletonPart root = null;
	
	public SkeletonPart(String name, float length) {
		super();
		this.setName(name);
		this.setLength(length);
		this.rotateDimensions = new ArrayList<RotateDimension>();
		this.bone = new Prism(5, length, length/5);
		this.childParts = new HashMap<Vector3f, SkeletonPart>();
		this.worldMatrix = new Matrix4f(); worldMatrix.set(1);
	}
	
	public void draw(GL2 gl) {
		draw(gl, false);
	}
	
	public void draw(GL2 gl, boolean withMyColorID) {
		if(withMyColorID) {
			// object will draw with color 
			gl.glColor4b(myColorID.getX(), myColorID.getY(), myColorID.getZ(), myColorID.getW());
		} else {
			// set other graphics parameters
		}
		for(RotateDimension rotateDimension : rotateDimensions) {
			gl.glRotatef(rotateDimension.getAngle(), rotateDimension.getAxis().x, rotateDimension.getAxis().y, rotateDimension.getAxis().z);
		}
		bone.draw(gl);
		
		for(Vector3f place : childParts.keySet()) {
			gl.glPushMatrix();
			gl.glTranslatef(place.x, place.y, place.z);
			childParts.get(place).draw(gl, withMyColorID);
			gl.glPopMatrix();
		}
	}
		
	public void addRotateDimension(RotateDimension rotateDimension) {
		rotateDimensions.add(rotateDimension);
		rotateDimension.addUpdateListener(this);
		update();
	}
	
	public ArrayList<RotateDimension> getRotateDimensions() { return this.rotateDimensions; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addChildPart(final Vector3f place, SkeletonPart childPart) {
		childParts.put(place, childPart);
		childPart.setRoot(this);
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public SkeletonPart getRoot() {
		return root;
	}

	public void setRoot(SkeletonPart root) {
		this.root = root;
	}
	
	public Matrix4f getWorldMatrx() {
		return worldMatrix;
	}
	
	public void update() {
		if(root != null) { worldMatrix = root.getWorldMatrx(); } else { worldMatrix.set(1); } 
	    for(RotateDimension rotateDimension: rotateDimensions) {
	    	worldMatrix.mul(rotateDimension.getRotationMatrix());
	    }
	    for(Vector3f place: childParts.keySet()) {
	    	childParts.get(place).update();
	    }
	}
	
	public SkeletonPart getWithColor(Color4b pixelColor) {
		if(this.myColorID.equals(pixelColor)) {
			return this;
		}
		SkeletonPart selectedPart;
		for(Vector3f location: childParts.keySet()) {
			if((selectedPart = childParts.get(location).getWithColor(pixelColor)) != null) {
				return selectedPart;
			}
		}
		return null;
	}
}
