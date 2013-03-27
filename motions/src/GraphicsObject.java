import javax.vecmath.Color4b;

public class GraphicsObject {
	protected Color4b myColorID;
	private static long lastID = 1;
	
	public GraphicsObject() {
		this.myColorID = new Color4b();
		this.myColorID.setX((byte)(lastID % 128)); 
		this.myColorID.setY((byte)(lastID/128 % 128));
		this.myColorID.setZ((byte)(lastID/128 % 128));
		this.myColorID.setW((byte)127);
		lastID++;
	}
	
	@Override
	public String toString() {
		return "(" + myColorID.getX() + ", " + myColorID.getY() + ", " +  myColorID.getZ() + ", " + myColorID.getW() + ")";
	}
}