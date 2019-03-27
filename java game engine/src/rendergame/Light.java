package rendergame;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	private Vector3f posision; 
	private Vector3f colour;
	
	
	public Light(Vector3f posision, Vector3f colour) {
		this.posision = posision; 
		this.colour = colour;
	}


	public Vector3f getPosision() {
		return posision;
	}


	public void setPosision(Vector3f posision) {
		this.posision = posision;
	}


	public Vector3f getColour() {
		return colour;
	}


	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	
	
	
	
}
