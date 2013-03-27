package figures;
import javax.media.opengl.GL2;
import javax.vecmath.Color4f;

public class GraphicsObject {
	protected Color4f color;
	
	public void draw(GL2 gl) {};
	
	public void draw(GL2 gl, boolean withMyColor) { }
	
	public GraphicsObject setHeight(float height) { return this; }
	
	public Color4f getColor() { return color; }

	public GraphicsObject setColor(Color4f color) { this.color = color; return this; }
	
	public GraphicsObject setColor(float r, float g, float b, float a) { this.color = new Color4f(r, g, b, a); return this; }
	
	public GraphicsObject setColor(float r, float g, float b) { return setColor(r, g, b, 1); }
}
