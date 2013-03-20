import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.vecmath.Color4b;

import figures.Prism;

public class SkeletonPart extends PickableObject implements DimensionListener {
	private String name;
	private float length;
	private SkeletonPart root = null;
	private SkeletonPartPosition position;
	private MotionDimension<Integer> rotY;
	private MotionDimension<Integer> rotZ;
	private MotionDimension<Integer> rotX;
	private MotionDimension<Float> stretchX;
	private ArrayList<SkeletonPart> childParts;
	private Prism bone;
	
	public SkeletonPart(String name, float length, SkeletonPartPosition position) {
		super();
		this.setName(name);
		this.setLength(length);
		setRotY(null);
		setRotZ(null);;
		setRotX(null);
		setStretchX(null);
		this.childParts = new ArrayList<SkeletonPart>();
		this.bone = new Prism(5, getActualLength(), 0.3f*getActualLength());
	}
	
	public SkeletonPart(String name, float length) {
		this(name, length, new SkeletonPartPosition());
	}
	
	public SkeletonPart(String name) {
		this(name, 0);
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
		
		// set into position relative to root part
		if(getRoot() != null) {
			gl.glTranslatef(root.getActualLength()*position.getRelativeX(), position.getY(), position.getZ());
		}
		if(position.getRotY() != 0) gl.glRotatef(position.getRotY(), 0, 1, 0);
		if(position.getRotZ() != 0) gl.glRotatef(position.getRotZ(), 0, 0, 1);
		if(position.getRotX() != 0) gl.glRotatef(position.getRotX(), 1, 0, 0);
		
		
		if (rotY != null) gl.glRotatef(rotY.getNumericValue(), 0, 1, 0);
		if (rotZ != null) gl.glRotatef(rotZ.getNumericValue(), 0, 0, 1);
		if (rotX != null) gl.glRotatef(rotX.getNumericValue(), 1, 0, 0);
		
		bone.draw(gl);
		
		for(SkeletonPart childPart : childParts) {
			gl.glPushMatrix();
			childPart.draw(gl, withMyColorID);
			gl.glPopMatrix();
		}
	}
	
	public ArrayList<MotionDimension<? extends Number>> getMotionDimensions() { 
		ArrayList<MotionDimension<? extends Number>> motionDimensions = new ArrayList<MotionDimension<? extends Number>>();
		if(rotY != null) motionDimensions.add(rotY);
		if(rotZ != null) motionDimensions.add(rotZ);
		if(rotX != null) motionDimensions.add(rotX);
		if(stretchX != null) motionDimensions.add(stretchX);
		return motionDimensions;
	}
	
	public void addChildPart(SkeletonPart childPart) {
		childParts.add(childPart);
		childPart.setRoot(this);
	}
	
	public SkeletonPart getWithColor(Color4b pixelColor) {
		if(this.myColorID.equals(pixelColor)) {
			return this;
		}
		SkeletonPart selectedPart;
		for(SkeletonPart childPart: childParts) {
			if((selectedPart = childPart.getWithColor(pixelColor)) != null) {
				return selectedPart;
			}
		}
		return null;
	}
	
	public float getActualLength() {
		if(stretchX != null) return stretchX.getNumericValue()*length;
		return length;
	}
	
	@Override
	public void dimensionChanged(MotionDimension<? extends Number> dimension) {
		if(dimension == stretchX) bone.setHeight(this.getActualLength());
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public float getLength() { return length; }

	public void setLength(float length) { this.length = length; }

	public SkeletonPart getRoot() { return root; }

	public void setRoot(SkeletonPart root) { this.root = root; }
	
	public SkeletonPartPosition getPosition() { return position; }

	public SkeletonPart setPosition(SkeletonPartPosition position) { this.position = position; return this; }

	public MotionDimension<Integer> getRotY() { return rotY; }

	public SkeletonPart setRotY(MotionDimension<Integer> rotY) { this.rotY = rotY; rotY.addDimensionListener(this); return this; }

	public MotionDimension<Integer> getRotZ() { return rotZ; }

	public SkeletonPart setRotZ(MotionDimension<Integer> rotZ) { this.rotZ = rotZ; rotZ.addDimensionListener(this); return this; }
	
	public MotionDimension<Integer> getRotX() { return rotX; }

	public SkeletonPart setRotX(MotionDimension<Integer> rotX) { this.rotX = rotX; rotX.addDimensionListener(this); return this; }

	public MotionDimension<Float> getStretchX() { return stretchX; }

	public SkeletonPart setStretchX(MotionDimension<Float> stretchX) { this.stretchX = stretchX; stretchX.addDimensionListener(this); return this; }

	public Prism getBone() { return bone; }
	
	public SkeletonPart setBone(Prism bone) {
		bone.setHeight(getActualLength());
		this.bone = bone;
		return this;
	}
}
