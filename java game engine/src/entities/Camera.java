package entities;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	Random random = new Random();
	public static float camera_x, camera_y, camera_z, camera_rot;
	private float rotX, rotY, rotZ;
	//public Vector3f camera_rot = new Vector3f(1,1,0);
	private float x = random.nextFloat() * 2;
	private float y = random.nextFloat() * 2;
	private float z = random.nextFloat() * 2;
	private Vector3f position =  new Vector3f(x,y,z);
	private float pitch; 
	private float roll;
	private float yaw;
	private float t = Mouse.getDX();
	private float tt = Mouse.getEventDX();

	
	//Camera camera = new Camera(1,0,1);
	
	
	public Camera(float rotX, float rotY, float rotZ) {
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	public void move() {
		
//		
	camera_x = 0;
	    camera_y = 0;
	    camera_z = 0;
	    camera_rot = 0;
	    
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			position.z-=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			position.x+=0.04f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
			position.x-=0.04f;		
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_H)) {
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
		if(Mouse.isInsideWindow()) {
			tt = position.x;
			System.out.println(tt);
		}
		
		
	}
	public float getRotX() {
		return rotX;
	}
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
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
