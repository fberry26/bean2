package komorebi.bean.editor;

import org.lwjgl.input.Mouse;

import komorebi.bean.engine.GameHandler;
import komorebi.bean.engine.GameHandler.State;
import komorebi.bean.engine.GameHandler.States;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.Main;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class Editor implements State {
  
  public static final int WIDTH = 384, HEIGHT = 272;
  public static final int MAP_WIDTH = 20, MAP_HT = 16;
  
  private Palette palette;
  private EditorLevel level;
  
  private int currentMouseMode;
  private MouseMode[] mouseModes = new MouseMode[3];
  
  private Image[] editorFunctions = 
    {Graphics.PENCIL, Graphics.MOUSE_POINTER, Graphics.DELETE_X};
  
  public Editor()
  {
    palette = new Palette();
    level = new EditorLevel();
    
    mouseModes[0] = new PencilMode(level, palette);
    mouseModes[1] = new ClickAndDragMode(level);
    mouseModes[2] = new EraserMode(level);
  }
  
  @Override
  public void getInput() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update() {
    //TODO Debug
    
    if (KeyHandler.keyClick(Key.ENTER))
    {
      Main.adjustScreenSize(false);
      GameHandler.setState(States.TITLE);
    }
    
    if (KeyHandler.controlDown())
      if (KeyHandler.keyClick(Key.RIGHT))
        nextMouseMode();
      else if (KeyHandler.keyClick(Key.LEFT))
        previousMouseMode();
    
    mouseModes[currentMouseMode].update();
    palette.update();
    
    if (KeyHandler.keyClick(Key.C))
    {
      Tile.nextColor();
    }
    
  }

  @Override
  public void render() {
    Draw.fill(Graphics.GREY_BACKGROUND, 0, 0, WIDTH, HEIGHT);
    
    for (int i = 0; i < MAP_HT; i++)
    {
      for (int j = 0; j < MAP_WIDTH; j++)
      {
        Draw.draw(Graphics.GRID, j*16, i*16, 1, 0);
      }
    }
    
    Draw.draw(Graphics.EDITOR_BUTTONS, 0, MAP_HT*16);
    
    for (int i = 0; i < editorFunctions.length; i++)
    {
      if (currentMouseMode == i)
      {
        Draw.fill(Graphics.BLUE_SELECTED_FUNCTION, 
            (MAP_WIDTH-editorFunctions.length+i)*16, MAP_HT*16, 16, 16);
      }
      Draw.draw(editorFunctions[i], (MAP_WIDTH-editorFunctions.length+i)*16, 
          MAP_HT*16);
    }

    if (onButtons())
    {
      Draw.draw(Graphics.BUTTON_HOVER_SQUARE, getMouseTx()*16, MAP_HT*16,
          0.5f, 0);
    }
    
    palette.render();
    level.render();
    mouseModes[currentMouseMode].render();
       
  }

  @Override
  public void gainFocus() {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * 
   * @return
   */
  private static boolean onButtons()
  {
    return (getMouseTy()==MAP_HT && getMouseTx() < 4);

  }
  
  public static int getMouseTx()
  {
    return (int) ((float) Mouse.getX()/Main.scale) / 16;
  }
  
  public static int getMouseTy()
  {
    return (int) ((float) Mouse.getY()/Main.scale) / 16;
  }
  
  private void nextMouseMode()
  {
    currentMouseMode++;
    if (currentMouseMode >= mouseModes.length)
    {
      currentMouseMode = 0;
    }
  }
  
  private void previousMouseMode()
  {
    currentMouseMode--;
    if (currentMouseMode < 0)
    {
      currentMouseMode = mouseModes.length - 1;
    }
  }

}
