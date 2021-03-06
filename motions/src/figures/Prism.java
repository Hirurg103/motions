package figures;
import javax.media.opengl.GL2;
import javax.vecmath.Color4f;

import static javax.media.opengl.GL2.*;

public class Prism extends GraphicsObject {
	private int countFaces;
	private float height;
	private float r1;
	private float r2;
	private float r3;
	private float r4;
	private float base;
	
	public Prism(int countFaces, float height, float radius, float base) {
		setCountFaces(countFaces);
		setHeight(height);
		setR1(radius);
		setR2(radius);
		setR3(radius);
		setR4(radius);
		setBase(base);
	}
	
	public Prism(int countFaces, float height, float radius) { this(countFaces, height, radius, 0.0f); }
	
	public Prism() { this(3, 1.0f, 1.0f); }
	
	@Override
	public void draw(GL2 gl) {
		if(getColor() != null) gl.glColor3f(getColor().getX(), getColor().getY(), getColor().getZ());
		
		double angle = 2*Math.PI/countFaces;
		float top = base + height;
				
		for(int i = 0; i <= countFaces; i++) {
			gl.glBegin(GL_QUADS);
				gl.glVertex3d(base, r2*Math.sin(i*angle), r1*Math.cos(i*angle));
				gl.glVertex3d(base, r2*Math.sin((i + 1)*angle), r1*Math.cos((i + 1)*angle));
				gl.glVertex3d(top, r4*Math.sin((i + 1)*angle), r3*Math.cos((i + 1)*angle));
				gl.glVertex3d(top, r2*Math.sin(i*angle), r3*Math.cos(i*angle));
			gl.glEnd();
		}
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(base, r2*Math.sin(i*angle), r1*Math.cos(i*angle));
		}
		gl.glEnd();
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(top, r4*Math.sin(i*angle), r3*Math.cos(i*angle));
		}
		gl.glEnd();
	}
	
	public int getCountFaces() { return countFaces; }

	public Prism setCountFaces(int countFaces) { this.countFaces = countFaces; return this; }
	
	public float getHeight() { return height; }
	
	@Override
	public Prism setHeight(float height) { this.height = height; return this; }
		
	public float getBase() { return base; }

	public Prism setBase(float base) { this.base = base; return this; }

	public float getR1() { return r1; }

	public Prism setR1(float r1) { this.r1 = r1; return this; }

	public float getR2() { return r2; }

	public Prism setR2(float r2) { this.r2 = r2; return this; }

	public float getR3() { return r3; }

	public Prism setR3(float r3) { this.r3 = r3; return this; }

	public float getR4() { return r4; }

	public Prism setR4(float r4) { this.r4 = r4; return this; }
	
	@Override
	public Prism setColor(Color4f color) { super.setColor(color); return this; }
	
	@Override
	public Prism setColor(float r, float g, float b, float a) { super.setColor(r, g, b, a); return this; }
	
	@Override
	public Prism setColor(float r, float g, float b) { super.setColor(r, g, b); return this; }
}
