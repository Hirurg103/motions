
public class SkeletonPartPosition {
	private float relativeX;
	private float Y;
	private float Z;
	private int rotY;
	private int rotZ;
	private int rotX;
	
	
	public SkeletonPartPosition(float relativeX, float Y, float Z, int rotY, int rotZ, int rotX) {
		setRelativeX(relativeX);
		setY(Y);
		setZ(Z);
		setRotY(rotY);
		setRotZ(rotZ);
		setRotX(rotX);
	}
	
	public SkeletonPartPosition(float relativeX, float Y, float Z, int rotY, int rotZ) {
		this(relativeX, Y, Z, rotY, rotZ, 0);
	}
	
	public SkeletonPartPosition(float relativeX, float Y, float Z, int initRotY) { 
		this(relativeX, Y, Z, initRotY, 0);
	}
	
	public SkeletonPartPosition(float relativeX, float Y, float Z) { 
		this(relativeX, Y, Z, 0);
	}
	
	public SkeletonPartPosition(float relativeX, float Y) { 
		this(relativeX, Y, 0);
	}
	
	public SkeletonPartPosition(float relativeX) { 
		this(relativeX, 0);
	}
	
	public SkeletonPartPosition() { 
		this(0);
	}

	public float getRelativeX() {
		return relativeX;
	}

	public SkeletonPartPosition setRelativeX(float relativeX) { this.relativeX = relativeX; return this; }

	public float getY() { return Y; }

	public SkeletonPartPosition setY(float y) { Y = y; return this; }

	public float getZ() { return Z; }

	public SkeletonPartPosition setZ(float z) { Z = z; return this; }

	public int getRotY() { return rotY; }

	public SkeletonPartPosition setRotY(int rotY) { this.rotY = rotY; return this; }

	public int getRotZ() { return rotZ; }

	public SkeletonPartPosition setRotZ(int rotZ) { this.rotZ = rotZ; return this; }

	public int getRotX() { return rotX; }

	public SkeletonPartPosition setRotX(int rotX) { this.rotX = rotX; return this; }
}
