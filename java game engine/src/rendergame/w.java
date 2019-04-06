package rendergame;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;


public class w {
	
	private static final int WIDTH = 1280; 
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 1000;
	
	
	
	private static long lastFrameTime;
	private static float delta;
	
	
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3,2);
		attribs.withForwardCompatible(true); 
		attribs.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	public static void closeDisplay() {
		Display.destroy();
		
	}
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update(); 
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
		
	}
	public static long getCurrentTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution(); 
	}
	public static float getFrameTimeSeconds() {
		return delta;
	}
	
	
	
}
