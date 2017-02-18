package komorebi.bean.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.SoundStore;

import komorebi.bean.editor.Editor;
import komorebi.bean.graphics.Graphics;

public class Main {

  public static int scale = 1;

  public static final int WIDTH = 320, HEIGHT = 256;
  
  public static void main(String[] args){
    run();
  }

  /**
   * Runs the game
   */
  private static void run() {
    try {
      BufferedReader read = new BufferedReader(
          new FileReader(new File("res/settings")));
      String str;

      while ((str = read.readLine()) != null) {
        if(str.equals("") || str.charAt(0) == '#'){
          continue;
        }
        scale = Integer.parseInt(str);
      }

      read.close();

    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
      scale = 1;
    }

    initDisplay(WIDTH, HEIGHT);
    initGL(WIDTH, HEIGHT);

    initGame();
    gameLoop();

    cleanUp();
  }


  /**
   *  Initializes the Display using the Display Class, properly Scaling it
   */
  public static void initDisplay(int width, int height){
    //create display
    try {
      Display.setDisplayMode(new DisplayMode(width*scale,height*scale));
      Display.setTitle("Bean");
      Display.create();
      Display.setVSyncEnabled(true);

    } catch (LWJGLException e) {
      e.printStackTrace();
    }
  }

  /**
   *  Creates a new game and initializes the audio
   *  @see GameHandler
   */
  private static void initGame(){
    Graphics.initialize();
    GameHandler.initialize();
  }


  private static void getInput(){
    GameHandler.getInput();
  }

  private static void update(){
    GameHandler.update();
  }


  private static void render(){
    glClear(GL_COLOR_BUFFER_BIT);   //clears the matrix with black
    glLoadIdentity();

    GameHandler.render();
    
    Display.update();   //updates the display with the changes
    Display.sync(60);   //makes up for lost time

  }

  /**
   *  Goes through the game loop, starting the music once
   */
  private static void gameLoop(){

    while(!Display.isCloseRequested()){
      getInput();
      update();
      render();
      SoundStore.get().poll(0);

      if (!Keyboard.isCreated())
      {
        try {
          Keyboard.create();
        } catch (LWJGLException e) {
          e.printStackTrace();
        }
      }
      if(Keyboard.isKeyDown(Keyboard.KEY_F4)){
        break;
      }

    }
  }

  /**
   *  Currently Enabled:<br>
   *  -Textures<br>
   *  -Transparency
   *  
   *  <p>Size: 256 x 224
   */
  private static void initGL(int width, int height){
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();              //resets the Matrix
    glOrtho(0,width,0,height,-1,1);     //creates a 3D space
    glMatrixMode(GL_MODELVIEW);
    glEnable(GL_TEXTURE_2D);       //enables Textures
    glEnable (GL_BLEND);

    //Enables transparency
    glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    glClearColor(0,0,0,1);         //sets the clearing color to black

    glDisable(GL_DEPTH_TEST);      //kills off the third dimension
  }

  /**
   *  Destroys the display and keyboard, closing the window
   */
  private static void cleanUp(){
    Display.destroy();
    AL.destroy();
    System.exit(0);
  }

  public static void adjustScreenSize(boolean expand)
  {
    int width, height;

    if (expand)
    {
      width = Editor.WIDTH; 
      height = Editor.HEIGHT;
    } else
    {
      width = WIDTH;
      height = HEIGHT;
    }
        
    try {
      Display.setDisplayMode(new DisplayMode(width*scale,height*scale));
      glMatrixMode(GL_PROJECTION);
      glLoadIdentity();              //resets the Matrix
      glOrtho(0,width,0,height,-1,1); 
      GL11.glViewport(0, 0, width*scale, height*scale); //NEW
      glMatrixMode(GL_MODELVIEW);

    } catch (LWJGLException e) {
      e.printStackTrace();
    }
  }


}
