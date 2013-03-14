import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap; 

import javax.media.opengl.GL2;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3f;

import figures.Prism;
import static javax.media.opengl.GL2.*;

public class SkeletonPart {
	private String name;
	private float length;
	private ArrayList<RotateDimension> rotateDimensions;
	private Prism bone;
	private HashMap<Vector3f, SkeletonPart> childParts;
	
	public SkeletonPart(String name, float length) {
		this.setName(name);
		this.setLength(length);
		this.rotateDimensions = new ArrayList<RotateDimension>();
		this.bone = new Prism(5, length, length/5);
		this.childParts = new HashMap<Vector3f, SkeletonPart>();
	}
	
	public void draw(GL2 gl) {
		for(RotateDimension rotateDimension : rotateDimensions) {
			gl.glRotatef(rotateDimension.getAngle(), rotateDimension.getAxis().x, rotateDimension.getAxis().y, rotateDimension.getAxis().z);
		}
		bone.draw(gl);
		
		for(Vector3f place : childParts.keySet()) {
			gl.glPushMatrix();
			gl.glTranslatef(place.x, place.y, place.z);
			childParts.get(place).draw(gl);
			gl.glPopMatrix();
		}
	}
	
	public void addRotateDimension(RotateDimension rotateDimension) {
		rotateDimensions.add(rotateDimension);
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
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}
}
