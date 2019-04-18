package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;





public class SomeStuff extends Entity{
	public Vector3f posistion = new Vector3f(0,0,0);
	
	public SomeStuff(TextureModel model, Vector3f posistion, float rotX, float rotY, float rotZ, float scale) {
		super(model, posistion, rotX, rotY, rotZ, scale);
	}
	
	public void Spawner() {
		while(Keyboard.next()) {
					
				}
				
				
				if (Keyboard.getEventKey() == Keyboard.KEY_1) {
				    System.out.println("A Key Event");
				    
				     while(posistion.x > (posistion.x + 10) ) {
				    	 posistion.x += 0.02f;
				     }
				    
				    
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_O) {
					
					if(Keyboard.getEventKeyState()) {
						System.out.println("hello");
					}
					
					
				}
	}


	
}
