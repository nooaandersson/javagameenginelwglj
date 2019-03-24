package models;

import rendergame.ModelTexture;

public class TextureModel {
	private rawModel rawModel;
	private ModelTexture texture; 
	
	public TextureModel(rawModel model, ModelTexture texture) {
		this.rawModel = model;
		this.texture = texture;
	}

	public rawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}
}
