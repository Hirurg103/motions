package figures;

import gui.dimensions.MotionDimension;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.vecmath.Color4b;
import javax.vecmath.Color4f;

import listeners.DimensionListener;


public class SkeletonPart extends PickableObject implements DimensionListener {
	private String name;
	private float length;
	private SkeletonPart root = null;
	private SkeletonPartPosition position;
	private MotionDimension<Integer> rotY = null;
	private MotionDimension<Integer> rotZ = null;
	private MotionDimension<Integer> rotX = null;
	private MotionDimension<Float> stretchX = null;
	private ArrayList<SkeletonPart> childParts;
	private GraphicsObject bone = null;
	
	public SkeletonPart(String name, float length, SkeletonPartPosition position) {
		super();
		setColor(null);
		setName(name);
		setLength(length);
		setPosition(position);
		this.childParts = new ArrayList<SkeletonPart>();
	}
	
	public SkeletonPart(String name, float length) {
		this(name, length, new SkeletonPartPosition());
	}
	
	public SkeletonPart(String name) {
		this(name, 0);
	}
	
	@Override
	public void draw(GL2 gl) { draw(gl, false); }
	
	@Override
	public void draw(GL2 gl, boolean withMyColorID) {
		if(withMyColorID) {
			// object will draw with color 
			gl.glColor4b(myColorID.getX(), myColorID.getY(), myColorID.getZ(), myColorID.getW());
		} else {
			// set other graphics parameters
			if(getColor() != null) gl.glColor3f(getColor().getX(), getColor().getY(), getColor().getZ());
		}
		
		// set into position relative to root part
		if(root != null) {
			gl.glTranslatef(root.getActualLength()*position.getRelativeX(), position.getY(), position.getZ());
		}
		if(position.getRotY() != 0) gl.glRotatef(position.getRotY(), 0, 1, 0);
		if(position.getRotZ() != 0) gl.glRotatef(position.getRotZ(), 0, 0, 1);
		if(position.getRotX() != 0) gl.glRotatef(position.getRotX(), 1, 0, 0);
		
		
		if (rotY != null) gl.glRotatef(rotY.getConvertedValue(), 0, 1, 0);
		if (rotZ != null) gl.glRotatef(rotZ.getConvertedValue(), 0, 0, 1);
		if (rotX != null) gl.glRotatef(rotX.getConvertedValue(), 1, 0, 0);
		
		if(bone != null) bone.draw(gl, !withMyColorID);
		
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
		if(stretchX != null) return stretchX.getConvertedValue()*length;
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

	public SkeletonPart setRotY(MotionDimension<Integer> rotY) { this.rotY = rotY; if(rotY != null) rotY.addDimensionListener(this); return this; }

	public MotionDimension<Integer> getRotZ() { return rotZ; }

	public SkeletonPart setRotZ(MotionDimension<Integer> rotZ) { this.rotZ = rotZ; if(rotZ != null) rotZ.addDimensionListener(this); return this; }
	
	public MotionDimension<Integer> getRotX() { return rotX; }

	public SkeletonPart setRotX(MotionDimension<Integer> rotX) { this.rotX = rotX; if(rotX != null) rotX.addDimensionListener(this); return this; }

	public MotionDimension<Float> getStretchX() { return stretchX; }

	public SkeletonPart setStretchX(MotionDimension<Float> stretchX) { this.stretchX = stretchX; if(stretchX != null) stretchX.addDimensionListener(this); return this; }

	public GraphicsObject getBone() { return bone; }
	
	public SkeletonPart setBone(GraphicsObject bone) {
		bone.setHeight(getActualLength());
		this.bone = bone;
		return this;
	}
	
	@Override
	public SkeletonPart setColor(Color4f color) { super.setColor(color); return this; }
	
	@Override
	public SkeletonPart setColor(float r, float g, float b, float a) { super.setColor(r, g, b, a); return this; }
	
	@Override
	public SkeletonPart setColor(float r, float g, float b) { super.setColor(r, g, b); return this; }
}