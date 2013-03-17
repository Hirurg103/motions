package figures;
import javax.media.opengl.GL2;

import static javax.media.opengl.GL2.*;

public class Prism extends CustomFigure {
	private int countFaces;
	private float height;
	private float radius;
	
	public Prism(int countFaces, float height, float radius) {
		this.countFaces = countFaces;
		this.height = height;
		this.radius = radius;
	}
	
	public Prism() { this(3, 1.0f, 1.0f); }
	
	@Override
	public void draw(GL2 gl) {
		double angle = 2*Math.PI/countFaces;
		for(int i = 0; i <= countFaces; i++) {
			gl.glBegin(GL_QUADS);
				gl.glVertex3d(0.0, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
				gl.glVertex3d(height, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
				gl.glVertex3d(height, radius*Math.sin((i + 1)*angle), radius*Math.cos((i + 1)*angle));
				gl.glVertex3d(0.0, radius*Math.sin((i + 1)*angle), radius*Math.cos((i + 1)*angle));
			gl.glEnd();
		}
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(0.0, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
		}
		gl.glEnd();
		
		gl.glBegin(GL_POLYGON);
		for(int i = 0; i < countFaces; i++) {
			gl.glVertex3d(height, radius*Math.sin(i*angle), radius*Math.cos(i*angle));
		}
		gl.glEnd();
	}
}
