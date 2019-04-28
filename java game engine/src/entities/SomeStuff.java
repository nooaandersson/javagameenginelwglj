package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;

public class SomeStuff extends Entity{
	
	
	private static enum stateWhile1 {
		UP, DOWN;
	}
	
	private stateWhile1 state = stateWhile1.UP;
	
	public Vector3f y = super.getPosistion();
	private Vector3f position =  new Vector3f(0,0,0);
	public SomeStuff(TextureModel model, Vector3f posistion, float rotX, float rotY, float rotZ, float scale) {
		super(model, posistion, rotX, rotY, rotZ, scale);
	}
	
	public void Spawner() {
		
		while(Keyboard.next()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_3)) {
				
				
			}
		}
				
				if(Keyboard.getEventKeyState()) {
					
					if(Keyboard.getEventKey()  == Keyboard.KEY_2) {
						System.out.println(y);
						
						    
					}
					
					
				}
	
	}
	public void cla() {
		switch(state) {
		case UP:
			super.increasePosition(0, 1, 0);
			break;
		case DOWN: 
			super.increasePosition(0, -1, 0);
			break;
		}
		
		
	}

	public void input() {
		switch(state) {
		case UP:
				
				if(y.y == 10) {
					state = stateWhile1.DOWN;
				}else if(y.y == y.y){
					super.increasePosition(0, 1, 0);
				}
			break;
		case DOWN: 
			
				super.increasePosition(0, -1, 0);
			break;
		}
	}
	


	
}
