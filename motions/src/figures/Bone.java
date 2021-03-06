package figures;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.vecmath.Color4f;

import figures.Prism;

public class Bone extends Prism {
	private float leftCapsuleRadius;
	private float rightCapsuleRadius;
	private GLU glu;
	
	public Bone(float height, float radius, float base) {
		super(6, height, radius, base);
		setLeftCapsuleRadius(0);
		setRightCapsuleRadius(0);
		setColor(null);
		this.glu = new GLU();
	}
	
	@Override
	public void draw(GL2 gl, boolean withMyColor) {
		if(withMyColor && getColor() != null) gl.glColor3f(getColor().getX(), getColor().getY(), getColor().getZ());

		super.draw(gl);
		GLUquadric quadric = glu.gluNewQuadric();
		gl.glTranslatef(super.getBase(), 0, 0);
		glu.gluSphere(quadric, leftCapsuleRadius, 10, 5);
		gl.glTranslatef(super.getHeight(), 0, 0);
		glu.gluSphere(quadric, rightCapsuleRadius, 10, 5);
		gl.glTranslatef(-super.getBase() - super.getHeight(), 0, 0);
	}
	
	@Override
	public void draw(GL2 gl) { draw(gl, true); }
	
	public float getLeftCapsuleRadius() { return leftCapsuleRadius; }
	
	public Bone setLeftCapsuleRadius(float leftCapsuleRadius) { this.leftCapsuleRadius = leftCapsuleRadius; return this; }
	
	public float getRightCapsuleRadius() { return rightCapsuleRadius; }
	
	public Bone setRightCapsuleRadius(float rightCapsuleRadius) { this.rightCapsuleRadius = rightCapsuleRadius; return this; }
	
	@Override
	public Bone setColor(Color4f color) { super.setColor(color); return this; }
	
	@Override
	public Bone setColor(float r, float g, float b, float a) { super.setColor(r, g, b, a); return this; }
	
	@Override
	public Bone setColor(float r, float g, float b) { super.setColor(r, g, b); return this; }
	
	@Override
	public Bone setCountFaces(int countFaces) { super.setCountFaces(countFaces); return this; }
	
	@Override
	public Bone setHeight(float height) { super.setHeight(height); return this; }
	
	@Override
	public Bone setBase(float base) { super.setBase(base); return this; }
	
	@Override
	public Bone setR1(float r1) { super.setR1(r1); return this; }
	
	@Override
	public Bone setR2(float r2) { super.setR2(r2); return this; }
	
	@Override
	public Bone setR3(float r3) { super.setR3(r3); return this; }
	
	@Override
	public Bone setR4(float r4) { super.setR4(r4); return this; }
		
}
