package figures;

import gui.dimensions.MotionDimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL2;
import javax.vecmath.Color4b;
import javax.vecmath.Color4f;

import db.DatabaseUtils;

import listeners.DimensionListener;


public class SkeletonPart extends PickableObject implements DimensionListener {
	private String name;
	private float length;
	private SkeletonPart root = null;
	private SkeletonPartPosition position;
	private MotionDimension rotY = null;
	private MotionDimension rotZ = null;
	private MotionDimension rotX = null;
	private MotionDimension stretchX = null;
	private ArrayList<SkeletonPart> childParts;
	private GraphicsObject bone = null;
	
	@SuppressWarnings("serial")
	public SkeletonPart(String name, float length, SkeletonPartPosition position) {
		super();
		setColor(null);
		setName(name);
		setLength(length);
		setPosition(position);
		this.childParts = new ArrayList<SkeletonPart>();
		
		//TODO: skeleton parts must load from database automatically
		List<Map<String, Object>> queryResult = DatabaseUtils.query("select * from skeleton_parts where name = ?", new ArrayList<Object>() {{ add(getName()); }});
		if(queryResult.isEmpty()) { 
			queryResult = DatabaseUtils.execute("insert into skeleton_parts (name, length) values (?, ?)", new ArrayList<Object>() {{ add(getName()); add(getActualLength()); }}, new String[] {"id" });
		}
		if(!queryResult.isEmpty()) setId(queryResult.get(0).get("ID"));
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
	
	public ArrayList<MotionDimension> getMotionDimensions() { 
		ArrayList<MotionDimension> motionDimensions = new ArrayList<MotionDimension>();
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
	public void dimensionChanged(MotionDimension dimension) {
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

	public MotionDimension getRotY() { return rotY; }

	public SkeletonPart setRotY(MotionDimension rotY) {
		this.rotY = rotY;
		afterSet(this.rotY);
		return this; 
	}

	public MotionDimension getRotZ() { return rotZ; }

	public SkeletonPart setRotZ(MotionDimension rotZ) { 
		this.rotZ = rotZ;
		afterSet(this.rotZ);
		return this; 
	}
	
	public MotionDimension getRotX() { return rotX; }

	public SkeletonPart setRotX(MotionDimension rotX) { 
		this.rotX = rotX;
		afterSet(this.rotX);
		return this; 
	}

	public MotionDimension getStretchX() { return stretchX; }

	public SkeletonPart setStretchX(MotionDimension stretchX) { 
		this.stretchX = stretchX; 
		afterSet(this.stretchX);
		return this; 
	}
	
	private void afterSet(MotionDimension motionDimension) {
		saveDimensionIntoDb(motionDimension);
		motionDimension.setSkeletonPartId(getId());
		if(motionDimension != null) motionDimension.addDimensionListener(this);  
	}

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
	
	// TODO: dimensions must load from db
	@SuppressWarnings("serial")
	private void saveDimensionIntoDb(final MotionDimension dimension) {
		List<Map<String, Object>> queryDimension = DatabaseUtils.query("select * from dimensions where skeleton_part_id = ? and name = ?", new ArrayList<Object>() {{ add(getId()); add(dimension.getName()); }});
		if(queryDimension.isEmpty()) {
			queryDimension = DatabaseUtils.execute("insert into dimensions (skeleton_part_id, name, from_v, to_v, initial_v) values (?, ?, ?, ?, ?)", new ArrayList<Object>() {{ add(getId()); add(dimension.getName()); add(dimension.getFrom()); add(dimension.getTo()); add(dimension.getInitial()); }}, new String[] { "id" });
		}
		if(!queryDimension.isEmpty()) {
			final Integer dimensionId = (Integer)(queryDimension.get(0).get("ID"));
			dimension.setId(dimensionId);
			List<Map<String, Object>> queryDimensionMotion = DatabaseUtils.query("select * from motion_dimensions md inner join motions m on md.motion_id = m.id where name is null and md.dimension_id = ?", new ArrayList<Object>() {{ add(dimensionId); }});
			if(queryDimensionMotion.isEmpty()) {
				final List<Map<String, Object>> queryMotion = DatabaseUtils.execute("insert into motions (name) values (?)", new ArrayList<Object>() {{ add(null); }}, new String[] {"id"});
				if(!queryMotion.isEmpty()) {
					final Object motionId = queryMotion.get(0).get("ID");
					dimension.setMotionId(motionId);
					queryDimensionMotion = DatabaseUtils.execute("insert into motion_dimensions (motion_id, dimension_id, from_f, to_f, initial_f) values (?, ?, ?, ?, ?)", new ArrayList<Object>() {{ add(motionId); add(dimensionId); add(dimension.getFrom()); add(dimension.getTo()); add(dimension.getInitial()); }}, new String[] {"id"});
				}
			}
			
		}
	}
}
