package bla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Guis.GuiTexture;
import Guis.guiRenderer;
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
import rendergame.MousePicker;
import rendergame.OBJLoader;
import rendergame.renderer;
import rendergame.staticShader;
import rendergame.w;
import terrains.Terrain;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;;

public class mainStater {
	
	private static enum stateWhile {
		MAIN, START;
	}
	
	private stateWhile state = stateWhile.MAIN;
	
	
	public mainStater() {
		w.createDisplay();
		claa();
		w.closeDisplay();
		
	}
	
	
	public void claa() {
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
		
		Player player = new Player(staticModel, new Vector3f(0,4,0),0,0,0,1);
		Camera camera = new Camera(player);
		Light light = new Light(new Vector3f(0,100,2), new Vector3f(1,1,1));
		Light light1 = new Light(new Vector3f(1,1,1), new Vector3f(10,1,1));
		
		List<Entity> alldrag = new ArrayList<Entity>();
		Random random = new Random();
		
		
		for(int i = 0; i < 1000; i++) {
			float x = random.nextFloat() * -100 + 50;
			float y = random.nextFloat();
			float z = random.nextFloat() * 300;
			alldrag.add(new Entity(staticModel, new Vector3f(x,y,z), 0, 0, 0f, 2f));
			
		}
		
		Terrain terrian = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
		Terrain terrian1 = new Terrain(-1,0,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		Terrain terrian2 = new Terrain(0,-1, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
		Terrain terrian3 = new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		SomeStuff spawner = new SomeStuff(staticModel, new Vector3f(0,0,0),0,0,0,10);	
		
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("health"), new Vector2f(-0.75f, -0.9f), new Vector2f(0.25f, 0.25f));
		guis.add(gui);
		
		guiRenderer guiRenderer = new guiRenderer(loader);
		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrian);
		MousePicker picker1 = new MousePicker(camera, renderer.getProjectionMatrix(), terrian1);
		MousePicker picker2 = new MousePicker(camera, renderer.getProjectionMatrix(), terrian2);
		MousePicker picker3 = new MousePicker(camera, renderer.getProjectionMatrix(), terrian3);
		
		switch(state) {
			case MAIN:
				while(!Display.isCloseRequested()) {
					w.updateDisplay();
					for(Entity cude : alldrag) {
						renderer.processEntity(cude);
					}
					renderer.processEntity(entity);
					picker.update();
					System.out.println(picker.getCurrentRay());
					Vector3f terrainPoint = picker.getCurrentTerrainPoint();
					if(terrainPoint!=null){
						entity.setPosistions(terrainPoint);
						light.setPosision(new Vector3f(terrainPoint.x, terrainPoint.y+15, terrainPoint.z));
					}
					
					
					
					camera.move();
					renderer.processTerrain(terrian3);
					renderer.processTerrain(terrian2);
					renderer.processTerrain(terrian);
					renderer.processTerrain(terrian1);	
					player.move(terrian);
					player.move(terrian1);
					player.move(terrian3);
					player.move(terrian2);
					renderer.render(light, camera);
					renderer.processEntity(player);
					guiRenderer.render(guis);
					System.out.print(state);
					
					
					if(Keyboard.isKeyDown(Keyboard.KEY_5)) {
						GL11.glEnable(GL11.GL_DEPTH_TEST);
						//GL11.glClearColor(0,0.6f,0.0f,1);
						GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
						 
						state = stateWhile.START;
						
						//w.updateDisplay();
					}
					//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					
				}
				guiRenderer.cleanUp();
				renderer.cleanUp();
				shader.cleanUp();
				loader.cleanUp();
				
				break;
			case START:
				
				while(!Display.isCloseRequested()) {
					
				
					w.updateDisplay();
					//GL11.glClearColor(0,0.3f,0.0f,1);
//				for(Entity cude : alldrag) {
//					renderer.processEntity(cude);
//				}
//				camera.move();
//				player.move(terrian);
//				player.move(terrian1);
//				player.move(terrian3);
//				player.move(terrian2);
//				renderer.processTerrain(terrian3);
//				renderer.processTerrain(terrian2);
//				renderer.processTerrain(terrian);
//				renderer.processTerrain(terrian1);			
//				renderer.render(light, camera);
//				renderer.processEntity(player);
				System.out.print(state);
//			
				if(Keyboard.isKeyDown(Keyboard.KEY_6)) {
					shader.cleanUp();
					loader.cleanUp();
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					//GL11.glClearColor(0,0.3f,0.0f,1);
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
					state = stateWhile.MAIN;
					//w.updateDisplay();
				}
//			
//				
//				
//				
				}
				shader.cleanUp();
				loader.cleanUp();
				
				break;
				
		}
	
			
	}
	
	public void checkInput() {
		switch(state) {
		case MAIN:
			if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			
				state = stateWhile.START;
				
			}
			break;
		case START: 
			if(Keyboard.isKeyDown(Keyboard.KEY_2)) {
				
				state = stateWhile.MAIN;
				
			}
			break;
		}
	}
	
	
	
	
	
//	public static void main(String[] args) {
//	
//		new mainStater();
//		
////		w.createDisplay();
////		Loader loader = new Loader(); 
////		
////		
////
////		staticShader shader = new staticShader();
////		MasterRenderer renderer = new MasterRenderer(); 
////		
////		//dragon render.
////		rawModel model = OBJLoader.loadObjModel("tree", loader);		
////		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture("tree")));
////		
////		rawModel model1 = OBJLoader.loadObjModel("stall", loader);		
////		TextureModel staticModel1 = new TextureModel(model1, new ModelTexture(loader.loadTexture("stallTexture")));
////		
////		ModelTexture texture = staticModel.getTexture();
////		texture.setShineDamper(10);
////		texture.setReflectivity(1);
////		
////		Entity entity = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1 );
////		Entity entity1 = new Entity(staticModel1, new Vector3f(20,0,-25),0,100,0,5);
//		
//		
//		
//		//other render
//		//rawModel model1 = OBJLoader.loadObjModel("mountain_canyon_01", loader);
//		//ModelTexture texture1 = new ModelTexture(loader.loadTexture("container"));
//		//TextureModel teModel = new TextureModel(model1,texture1);
//		//Entity ent = new Entity(teModel, new Vector3f(20,0,-50),0,0,0,1,1);
//		
//		//rawModel model3 = OBJLoader.loadObjModel("blaa", loader);
//		//TextureModel staM = new TextureModel(model3, new ModelTexture(loader.loadTexture("red")));
//		//Enitity e = new Entity(staM, new Vector3f(40, 0,-25)0,0,0,1);
//		
//		//Entity entity1 = new Entity(texturedModel, new Vector3f(2,0,-5), 0,0,0,1,2);
//		//Entity entity2 = new Entity(texturedModel, new Vector3f(-2,0,-5), 0,0,0,1,-1);
//		
////		Player player = new Player(staticModel, new Vector3f(0,0,0),0,0,0,1);
////		Camera camera = new Camera(player);
////		Light light = new Light(new Vector3f(0,100,2), new Vector3f(1,1,1));
////		
////		List<Entity> alldrag = new ArrayList<Entity>();
////		Random random = new Random();
////		
////		
////		for(int i = 0; i < 1000; i++) {
////			float x = random.nextFloat() * -100 + 50;
////			float y = random.nextFloat();
////			float z = random.nextFloat() * 300;
////			alldrag.add(new Entity(staticModel, new Vector3f(x,y,z), 0, 0, 0f, 2f));
////			
////		}
//		
////		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
////		Terrain terrain3 = new Terrain(2,0,loader,new ModelTexture(loader.loadTexture("grass")));
////		Terrain terrain4 = new Terrain(-2,0,loader,new ModelTexture(loader.loadTexture("grass")));
////		Terrain terrain5 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")));
////		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
////		
////		Terrain terrian = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
////		Terrain terrian1 = new Terrain(-1,0,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
////		Terrain terrian2 = new Terrain(0,-1, loader, new ModelTexture(loader.loadTexture("n")), "heightMap");
////		Terrain terrian3 = new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
////		SomeStuff spawner = new SomeStuff(staticModel, new Vector3f(0,0,0),0,0,0,10);		
////		
//		
////		while(!Display.isCloseRequested()) {
//// 
////			w.updateDisplay();
////			
////			claa();
////			renderer.processEntity(spawner);
////			spawner.Spawner();
////			//spawner.input();
////			//spawner.cla();
////			renderer.processTerrain(terrian3);
////			renderer.processTerrain(terrian2);
////			renderer.processTerrain(terrian);
////			renderer.processTerrain(terrian1);
////			w.frame();			
////			renderer.render(light, camera);
////			renderer.processEntity(player);
////	//		for(Entity cude : alldrag) {
////	//			renderer.processEntity(cude);
////	//		}
////			camera.move();
////			player.move(terrian);
////			player.move(terrian1);
////			player.move(terrian3);
////			player.move(terrian2);
//		}
//		
		
//		shader.cleanUp();
//		loader.cleanUp();
//		w.closeDisplay();
	}
	
