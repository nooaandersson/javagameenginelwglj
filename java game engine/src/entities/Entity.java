package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;
import rendergame.renderer;
import rendergame.MasterRenderer;

public class Entity {
	private TextureModel model; 
	
	private Vector3f posistion;
	
	private float rotX,rotY,rotZ;
	private float scale;
	public Entity(TextureModel model, Vector3f posistion, float rotX, float rotY, float rotZ, float scale) {
	
		this.model = model;
		this.posistion = posistion;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.posistion.x+=dx;
		this.posistion.y+=dy;
		this.posistion.z+=dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	
	public TextureModel getModel() {
		return model;
	}
	public void setModel(TextureModel model) {
		this.model = model;
	}
	public Vector3f getPosistion() {
		return posistion;
	}
	public void setPosistions(Vector3f posistion) {
		this.posistion = posistion;
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
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
}
