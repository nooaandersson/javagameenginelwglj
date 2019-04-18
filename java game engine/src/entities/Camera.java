package entities;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	Random random = new Random();
	public static float camera_x, camera_y, camera_z, camera_rot;
	
	private float distanceFromPlayer = 50; 
	private float angleAroundPlayer = 0;
	
	
	private float rotX, rotY, rotZ;
	//public Vector3f camera_rot = new Vector3f(1,1,0);
	//private float x = random.nextFloat() * 2;
	//private float y = random.nextFloat() * 2;
	//private float z = random.nextFloat() * 2;
	private float x = (float) 0.812544;
	private float y = (float) 31.4763; 
	private float z = (float) 250;
	private Vector3f position =  new Vector3f(0,0,0);
	private float pitch; 
	private float roll;
	private float yaw;
	private float t = Mouse.getDX();
	public float tt = Mouse.getEventDX();
	public float tty = Mouse.getEventDY();
	public float ttz = 40;
	
	private Player player;
	

	
	//Camera camera = new Camera(1,0,1);
	
	
	public Camera(Player player) {
		this.player = player;
	}
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	public void move() { 	
	    
		calculateZoom(); 
		calculatePitch();
		calculateAngelAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance(); 
		float verticalDistance = calculateVerticalDistance();
		caluculateCameraPosition(horizontalDistance, verticalDistance);
		
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		
		
//		if(Keyboard.isKeyDown(Keyboard.KEY_Y)) {
//			position.z-=0.04f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
//			position.x+=0.04f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
//			position.x-=0.04f;		
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_H)) {
//			position.z+=0.04f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
//			position.y-=0.04f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {			
//			position.y+=0.04f;
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_9)) {
//			position.z-=0.4f;
//			
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_0)) {
//			camera_rot += Mouse.getX();			
//		}
//		if(Mouse.isInsideWindow()) {
//			tt = position.x;
//			tty = position.y;
//			ttz = position.z;
//			System.out.println("x" + tt);
//			System.out.println("y" + tty);
//			System.out.println("z" + ttz);
//		}
		
		
		
		
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
	private void caluculateCameraPosition(float horizDistance, float verticDistance) {
		float theta = player.getRotY() +  angleAroundPlayer;
		float offsetX = ( float ) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = ( float ) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.y = player.getPosistion().y + verticDistance;
		position.z = player.getPosistion().z - offsetZ;
		position.x = player.getPosistion().x - offsetX;
	}
	
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
	}
	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			
		}
	}
	
	private void calculateAngelAroundPlayer() {
		
		if(Mouse.isInsideWindow()) {
			if(Mouse.isButtonDown(0)) {
				float angleChange = Mouse.getDX() * 3f;
				angleAroundPlayer -= angleChange;
				System.out.print(angleChange);
			}
			else if(!Mouse.isButtonDown(0)){
				float angleChange = Mouse.getDX();
				angleAroundPlayer = angleChange;
			}
		}
	}
}




















