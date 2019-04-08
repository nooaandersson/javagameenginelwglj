package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;
import rendergame.w;
import terrains.Terrain;

public class Player extends Entity {
	
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 50;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 50;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	private float dy = 0;
	private float speed = 0.02f;
	private boolean InAir = false;
	
	private static final float TERRAIN_HEIGHT = 0;
	
	public Player(TextureModel model, Vector3f posistion, float rotX, float rotY, float rotZ, float scale) {
		super(model, posistion, rotX, rotY, rotZ, scale);
	}
	public void move(Terrain terrain) {
		checkInputs(); 
		super.increaseRotation(0, currentTurnSpeed * w.getFrameTimeSeconds(),0);
		float distance = currentSpeed * w.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		//float dy = (float) (distance * Math.sin(Math.toRadians(super.getRotZ())));
		super.increasePosition(dx, dy, dz);
		upwardsSpeed += GRAVITY * w.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * w.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosistion().x, super.getPosistion().z);
		if(super.getPosistion().y<terrainHeight) {
			upwardsSpeed = 0; 
			InAir = false;
			super.getPosistion().y = terrainHeight;
		}
	}
	
	private void jump() {
		
		if(!InAir) {
			this.upwardsSpeed = JUMP_POWER;
			InAir = true; 
		}
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}else {
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
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
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
	}
}
