package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;
import rendergame.w;

public class Player extends Entity {
	
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 50;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float dy = 0;
	private float speed = 1;
	
	public Player(TextureModel model, Vector3f posistion, float rotX, float rotY, float rotZ, float scale) {
		super(model, posistion, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
	}
	public void move() {
		checkInputs(); 
		super.increaseRotation(0, currentTurnSpeed * w.getFrameTimeSeconds(),0);
		float distance = currentSpeed * w.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		//float dy = (float) (distance * Math.sin(Math.toRadians(super.getRotZ())));
		super.increasePosition(dx, dy, dz);
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = -RUN_SPEED;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = RUN_SPEED;
		}else {
			this.currentSpeed = 0;
		}
		
		if(Mouse.isButtonDown(1)) {
			this.currentTurnSpeed = -TURN_SPEED;
		}else if(Mouse.isButtonDown(0)) {
			this.currentTurnSpeed = TURN_SPEED;
		}else {
			this.currentTurnSpeed = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_P) ) {
			this.dy = speed;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
			this.dy = -speed;
		}
		else {
			this.dy = 0;
		}
		
	}
}
