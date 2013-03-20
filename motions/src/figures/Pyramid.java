package figures;
import javax.media.opengl.GL2;

import static javax.media.opengl.GL2.*;


public class Pyramid extends GraphicsObject {
	// ----- Render the Pyramid -----
	public static void draw(GL2 gl, float u, float v, float h) {
		gl.glBegin(GL_QUADS); // of the pyramid
		// bottom
		gl.glColor3f(1, 1, 0);
		gl.glVertex3f(u, 0, u);
		gl.glVertex3f(-u, 0, u);
		gl.glVertex3f(-u, 0, -u);
		gl.glVertex3f(u, 0, -u);
		
		// top
		gl.glColor3f(1, 0, 1);
		gl.glVertex3f(v, h, v);
		gl.glVertex3f(v, h, -v);
		gl.glVertex3f(-v, h, -v);
		gl.glVertex3f(-v, h, v);
		
		// front
		gl.glColor3f(0, 1, 0);
		gl.glVertex3f(u, 0, u);
		gl.glVertex3f(v, h, v);
		gl.glVertex3f(-v, h, v);
		gl.glVertex3f(-u, 0, u);
		
		// left
		gl.glColor3f(0, 0, 1);
		gl.glVertex3f(u, 0, u);
		gl.glVertex3f(u, 0, -u);
		gl.glVertex3f(v, h, -v);
		gl.glVertex3f(v, h, v);
		
		//back
		gl.glColor3f(1, 0, 0);
		gl.glVertex3f(u, 0, -u);
		gl.glVertex3f(-u, 0, -u);
		gl.glVertex3f(-v, h, -v);
		gl.glVertex3f(v, h, -v);
		
		//right
		gl.glColor3f(0, 1, 1);
		gl.glVertex3f(-u, 0, -u);
		gl.glVertex3f(-u, 0, u);
		gl.glVertex3f(-v, h, v);
		gl.glVertex3f(-v, h, -v);
		gl.glEnd(); // of pyramig
	}
}
