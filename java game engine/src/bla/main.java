package bla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Player;
import entities.SomeStuff;
import models.TextureModel;
import models.rawModel;
import rendergame.Light;
import rendergame.Loader;
import rendergame.MasterRenderer;
import rendergame.ModelTexture;
import rendergame.OBJLoader;
import rendergame.renderer;
import rendergame.staticShader;
import rendergame.w;
import terrains.Terrain;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;;

public class main {
	
	public static void main(String[] args) {
		
		
		w.createDisplay();
		Loader loader = new Loader(); 
		
		

		staticShader shader = new staticShader();
		MasterRenderer renderer = new MasterRenderer(); 
		
		//dragon render.
		rawModel model = OBJLoader.loadObjModel("tree", loader);		
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture("tree")));
		
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
		
		//rawModel model3 = OBJLoader.loadObjModel("blaa", loader);
		//TextureModel staM = new TextureModel(model3, new ModelTexture(loader.loadTexture("red")));
		//Enitity e = new Entity(staM, new Vector3f(40, 0,-25)0,0,0,1);
		
		//Entity entity1 = new Entity(texturedModel, new Vector3f(2,0,-5), 0,0,0,1,2);
		//Entity entity2 = new Entity(texturedModel, new Vector3f(-2,0,-5), 0,0,0,1,-1);
		
		Player player = new Player(staticModel, new Vector3f(0,0,0),0,0,0,1);
		Camera camera = new Camera(player);
		Light light = new Light(new Vector3f(0,100,2), new Vector3f(1,1,1));
		
		List<Entity> alldrag = new ArrayList<Entity>();
		Random random = new Random();
		
		
		for(int i = 0; i < 1000; i++) {
			float x = random.nextFloat() * -100 + 50;
			float y = random.nextFloat();
			float z = random.nextFloat() * 300;
			alldrag.add(new Entity(staticModel, new Vector3f(x,y,z), 0, 0, 0f, 2f));
			
		}
		
//		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain3 = new Terrain(2,0,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain4 = new Terrain(-2,0,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain5 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")));
//		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
//		
		Terrain terrian = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
		Terrain terrian1 = new Terrain(-1,0,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		Terrain terrian2 = new Terrain(0,-1, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
		Terrain terrian3 = new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		SomeStuff spawner = new SomeStuff(staticModel, new Vector3f(0,0,0),0,0,0,10);		
		
		
		while(!Display.isCloseRequested()) {
 
			w.updateDisplay();
			renderer.processEntity(spawner);
			spawner.Spawner();
			
			renderer.processTerrain(terrian3);
			renderer.processTerrain(terrian2);
			renderer.processTerrain(terrian);
			renderer.processTerrain(terrian1);
			w.frame();			
			renderer.render(light, camera);
			renderer.processEntity(player);
	//		for(Entity cude : alldrag) {
	//			renderer.processEntity(cude);
	//		}
			camera.move();
			player.move(terrian);
			player.move(terrian1);
			player.move(terrian3);
			player.move(terrian2);
		}
		
		
		shader.cleanUp();
		loader.cleanUp();
		w.closeDisplay();
	}
	
}
