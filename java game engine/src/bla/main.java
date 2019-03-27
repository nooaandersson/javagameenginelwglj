package bla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.TextureModel;
import models.rawModel;
import rendergame.Light;
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
		
		//dragon render.
		rawModel model = OBJLoader.loadObjModel("dragon", loader);		
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture("container")));
		
		rawModel model1 = OBJLoader.loadObjModel("stall", loader);		
		TextureModel staticModel1 = new TextureModel(model1, new ModelTexture(loader.loadTexture("stallTexture")));
		
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1 );
		Entity entity1 = new Entity(staticModel1, new Vector3f(20,0,-25),0,100,0,5);
		//other render
		//rawModel model1 = OBJLoader.loadObjModel("mountain_canyon_01", loader);
		//ModelTexture texture1 = new ModelTexture(loader.loadTexture("container"));
		//TextureModel teModel = new TextureModel(model1,texture1);
		//Entity ent = new Entity(teModel, new Vector3f(20,0,-50),0,0,0,1,1);
		
		
		//Entity entity1 = new Entity(texturedModel, new Vector3f(2,0,-5), 0,0,0,1,2);
		//Entity entity2 = new Entity(texturedModel, new Vector3f(-2,0,-5), 0,0,0,1,-1);
		
		
		Camera camera = new Camera();
		Light light = new Light(new Vector3f(3000,2000,3000), new Vector3f(1,1,1));
		
		List<Entity> alldrag = new ArrayList<Entity>();
		Random random = new Random();
		
		
		for(int i = 0; i < 200; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			alldrag.add(new Entity(staticModel, new Vector3f(x,y,z), random.nextFloat() * 180f, random.nextFloat() * 180f, 0f, 1f));
		}
		
		while(!Display.isCloseRequested()) {
			
			//game logic
			
			//entity.increasePosition(0, 0, 0);
			//ent.increasePosition(0, 0, 0);
			camera.move();
			//entity.increaseRotation(0, 1.2f, 0);
			
			
			
			
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			//renderer.render(ent, shader);
			//renderer.render(entity1, shader);
			//renderer.render(entity, shader);
			//renderer.render(entity1, shader);
			//renderer.render(entity2, shader);
			for(Entity cude : alldrag) {
				renderer.render(cude, shader);
				cude.increaseRotation(0, 1.2f, 0);
			}
			
			
			
			
			
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
