package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	public static float camera_x, camera_y, camera_z, camera_rot;
	//public Vector3f camera_rot = new Vector3f(1,1,0);
	private Vector3f position = new Vector3f(0,0,0); 
	private float pitch; 
	private float roll;
	private float yaw;
	
	
	public Camera() {
		
	}
	
	public void move() {
		
		
		camera_x = 0;
	    camera_y = 0;
	    camera_z = 0;
	    camera_rot = 0;
	    
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z+=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			position.y-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			position.y+=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_9)) {
			position.z-=0.4f;
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_0)) {
			camera_rot += Mouse.getX();
			
		}
			
		
		
		
		
	}
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getRoll() {
		return roll;
	}
	public float getYaw() {
		return yaw;
	}
}
