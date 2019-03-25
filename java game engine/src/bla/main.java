package bla;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.TextureModel;
import models.rawModel;
import rendergame.Loader;
import rendergame.ModelTexture;
import rendergame.OBJLoader;
import rendergame.renderer;
import rendergame.staticShader;
import rendergame.w;

public class main {
	
	
	
	public static void main(String[] args) {
		w.createDisplay();
		Loader loader = new Loader(); 
		

		staticShader shader = new staticShader();
		renderer renderer = new renderer(shader); 
		
		
		rawModel model = OBJLoader.loadObjModel("dragon", loader);
				
				
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TextureModel texturedModel = new TextureModel(model,texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,0,0,1, 1);
		//Entity entity1 = new Entity(texturedModel, new Vector3f(2,0,-5), 0,0,0,1,2);
		//Entity entity2 = new Entity(texturedModel, new Vector3f(-2,0,-5), 0,0,0,1,-1);
		
		
		Camera camera = new Camera();
		
		
		while(!Display.isCloseRequested()) {
			
			//game logic
			
			entity.increasePosition(0, 0, 0);
			camera.move();
			entity.increaseRotation(0, 1.2f, 0);
			
			
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			//renderer.render(entity1, shader);
			renderer.render(entity, shader);
			//renderer.render(entity2, shader);
			shader.stop();
			//Game loop 
			//render game
			
			w.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		w.closeDisplay();
	}
	
}
