package figures;
import javax.media.opengl.GL2;

import static javax.media.opengl.GL2.*;

public class Prism extends GraphicsObject {
	private int countFaces;
	private float height;
	private float radius;
	private float base;
	
	public Prism(int countFaces, float height, float radius, float base) {
		setCountFaces(countFaces);
		setHeight(height);
		setRadius(radius);
		setBase(base);
	}
	
	public Prism(int countFaces, float height, float radius) { this(countFaces, height, radius, 0.0f); }
	
	public Prism() { this(3, 1.0f, 1.0f); }
	
	@Override
	public void draw(GL2 gl) {
		double angle = 2*Math.PI/countFaces;
		float top = base + height;
		double 	Z1, Z2, Y1, Y2;
				
		for(int i = 0; i <= countFaces; i++) {
			Z1 = radius*Math.cos(i*angle);
			Z2 = radius*Math.cos((i + 1)*angle);
			Y1 = radius*Math.sin(i*angle);
			Y2 = radius*Math.sin((i + 1)*angle);
			gl.glBegin(GL_QUADS);
				gl.glVertex3d(base, Y1, Z1);
				gl.glVertex3d(base, Y2, Z2);
				gl.glVertex3d(top, Y2, Z2);
				gl.glVertex3d(top, Y1, Z1);
			gl.glEnd();
		}
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(base, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
		}
		gl.glEnd();
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(top, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
		}
		gl.glEnd();
	}
	
	public int getCountFaces() { return countFaces; }

	public Prism setCountFaces(int countFaces) { this.countFaces = countFaces; return this; }
	
	public float getHeight() { return height; }

	public Prism setHeight(float height) { this.height = height; return this; }
	
	public float getRadius() { return radius; }

	public Prism setRadius(float radius) { this.radius = radius; return this; }
	
	public float getBase() { return base; }

	public Prism setBase(float base) { this.base = base; return this; }
}
