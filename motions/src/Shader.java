import javax.media.opengl.GL2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;


import static javax.media.opengl.GL2.*;

public class Shader {
    int id = 0;

    public static Shader load(GL2 gl, URL resource, URL resource1) {
        System.out.println("Loading shader:" + resource1);
        int id = loadShaderProgram(gl, resource, resource1);
        Shader shader = new Shader();
        shader.id = id;
        return shader;
    }

    public void use(GL2 gl) {
        gl.glUseProgram(id);
    }

    public void setFloatParameter(GL2 gl, String parameter, float value) {
        int loc = gl.glGetUniformLocation(id, parameter);
        gl.glUniform1f(loc, value);
    }

    public void setVec2Parameter(GL2 gl, String parameter, float value, float value2) {
        int loc = gl.glGetUniformLocation(id, parameter);
        gl.glUniform2f(loc, value, value2);
    }

    public void setVec4Parameter(GL2 gl, String parameter, float r, float g, float b, float a) {
        int loc = gl.glGetUniformLocation(id, parameter );
        gl.glUniform4f(loc, r, g, b, a);
    }
    
    public void setIntParameter(GL2 gl, String parameter, int value) {
        int loc = gl.glGetUniformLocation(id, parameter);
        gl.glUniform1i(loc, value);
    }
    
    public static int loadShaderProgram(GL2 gl, URL vert, URL frag) {
        if (gl.isExtensionAvailable("GL_ARB_vertex_shader")) {
            String vertexShaderSource = null;
            String fragmentShaderSource = null;

            try {
                BufferedReader shaderReader = new BufferedReader(
                    new InputStreamReader(
                        vert.openStream()
                    )
                );
                StringBuilder b = new StringBuilder();
                String line = shaderReader.readLine();
                while (line != null) {
                    b.append(line);
                    b.append("\n");
                    line = shaderReader.readLine();
                }
                vertexShaderSource = b.toString();

                shaderReader = new BufferedReader(
                    new InputStreamReader(
                            frag.openStream()
                    )
                );
                b = new StringBuilder();
                line = shaderReader.readLine();
                while (line != null) {
                    b.append(line);
                    b.append("\n");
                    line = shaderReader.readLine();
                }
                fragmentShaderSource = b.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if ( vertexShaderSource != null && fragmentShaderSource != null ) {
                int vertexShader = gl.glCreateShader(GL_VERTEX_SHADER);
                gl.glShaderSource(vertexShader, 1, new String[]{vertexShaderSource}, (int[]) null, 1);
                gl.glCompileShader(vertexShader);
                shaderStatus(gl, vertexShader);

                int fragmentShader = gl.glCreateShader(GL_FRAGMENT_SHADER);
                gl.glShaderSource(fragmentShader, 1, new String[]{fragmentShaderSource}, (int[]) null, 1);
                gl.glCompileShader(fragmentShader);
                shaderStatus(gl, fragmentShader);
                
                int program = gl.glCreateProgram();
                gl.glAttachShader(program, vertexShader);
                gl.glAttachShader(program, fragmentShader);

                gl.glLinkProgram(program);
                gl.glValidateProgram(program);
                programStatus(gl, program);
                return program;
            } else {
                return 0;
            }
        } else {
            throw new RuntimeException("GL_ARB_vertex_shader not supported!");
        }
    }
    
    private static int shaderStatus(GL2 gl, int shader) {
    	IntBuffer status = IntBuffer.allocate(2);
    	gl.glGetShaderiv(shader, GL_COMPILE_STATUS, status);
    	if(status.get() == GL_FALSE) {
    		IntBuffer infoLogLength = IntBuffer.allocate(1); 
    		gl.glGetShaderiv(shader, GL_INFO_LOG_LENGTH, infoLogLength);
    		ByteBuffer infoLog = ByteBuffer.allocate(infoLogLength.get());
    		int infoLogLengthWithNull = infoLogLength.get();
    		infoLogLength.flip();
    		gl.glGetShaderInfoLog(shader, infoLogLengthWithNull, infoLogLength, infoLog);
    		byte[] infoBytes = new byte[infoLogLength.get()];
    		infoLog.get(infoBytes);
    		System.out.println("Compilation shader >> " + new String(infoBytes));
    		throw new Error("Compilation the shader failed.");
    	}
    	return 1;
    }
    
    private static int programStatus(GL2 gl, int program) {
    	IntBuffer status = IntBuffer.allocate(2);
    	gl.glGetProgramiv(program, GL_LINK_STATUS, status);
    	if(status.get() == GL_FALSE) {
    		IntBuffer infoLogLength = IntBuffer.allocate(1); 
    		gl.glGetProgramiv(program, GL_INFO_LOG_LENGTH, infoLogLength);
    		ByteBuffer infoLog = ByteBuffer.allocate(infoLogLength.get());
    		int infoLogLengthWithNull = infoLogLength.get();
    		infoLogLength.flip();
    		gl.glGetProgramInfoLog(program, infoLogLengthWithNull, infoLogLength, infoLog);
    		byte[] infoBytes = new byte[infoLogLength.get()];
    		infoLog.get(infoBytes);
    		System.out.println("Linking shader program >> " + new String(infoBytes));
    		throw new Error("Linking the shader program failed.");
    	}
    	return status.get();
    }
}