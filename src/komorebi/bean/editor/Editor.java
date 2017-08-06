package komorebi.bean.editor;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import komorebi.bean.editor.attributes.AttributeWindow;
import komorebi.bean.editor.attributes.buttonlink.Linkable;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.tools.clickanddrag.MultiSelection;
import komorebi.bean.engine.GameHandler;
import komorebi.bean.engine.GameHandler.State;
import komorebi.bean.engine.GameHandler.States;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.Main;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Transformation;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class Editor implements State {
  
  public static final int WIDTH = 384, HEIGHT = 272;
  public static final int MAP_WIDTH = 20, MAP_HT = 16;
  
  public static final Rectangle MAP = new Rectangle(0, 0, MAP_WIDTH, MAP_HT);
  
  private static boolean gridOn = true;
  
  private Palette palette;
  private static EditorLevel level;
  private static Toolbar toolbar;
  
  public static final Dimension TILE_MODSPACE = new Dimension(20, 16);
  public static final Dimension MODSPACE = new Dimension(320, 256);
  private static MultiSelection selection = new MultiSelection();
  
  private static final Image TEST_IMAGE = new Image(224, 0, 32, 16,
      Draw.MISCELLANY);
  
  public Editor()
  {
    palette = new Palette();
    level = new EditorLevel();
    toolbar = new Toolbar(level, palette);
       
  }
  
  @Override
  public void getInput() {
    
  }

  @Override
  public void update() {
    //TODO Debug
    if (KeyHandler.keyClick(Key.ENTER))
    {
      Main.adjustScreenSize(false);
      GameHandler.setState(States.TITLE);
    }
    
    if (KeyHandler.keyClick(Key.G))
    {
      toggleGrid();
    }
    
    if (KeyHandler.keyClick(Key.DEL) && hasSelections())
    {
      deleteAllItemsWithinSelection();
      clearSelection();
    }
    
    if (toolbar.isEnabled())
    {
      toolbar.update();
    }
    palette.update();
    
    AttributeWindow.update();
        
    if (KeyHandler.keyClick(Key.C))
    {
      Tile.nextColor();
    }
    
    if (KeyHandler.keyClick(Key.D))
    {
      for (Linkable link: level.getLinkableObjects())
      {
        System.out.println(link.getID());
      }
    }
    
  }

  @Override
  public void render() {
    Draw.fill(Graphics.GREY_BACKGROUND, 0, 0, WIDTH, HEIGHT);
    
    if (gridOn)
    {
      drawGrid();
    }
    
    Draw.draw(Graphics.EDITOR_FILE_BUTTONS, 0, MAP_HT*16);
    

    if (onButtons())
    {
      Draw.draw(Graphics.BUTTON_HOVER_SQUARE, MouseHandler.getTx()*16, MAP_HT*16,
          0.5f);
    }
    
    palette.render();
    level.render();
    toolbar.render();
    
    AttributeWindow.render();
    
    selection.render();    
  }

  @Override
  public void gainFocus() {
    
  }
  
  private void drawGrid()
  {
    for (int i = 0; i < MAP_HT; i++)
    {
      for (int j = 0; j < MAP_WIDTH; j++)
      {
        Draw.draw(Graphics.GRID, j*16, i*16, 1);
      }
    }
  }
  
  /**
   * 
   * @return
   */
  private static boolean onButtons()
  {
    return (MouseHandler.getTy()==MAP_HT && MouseHandler.getTx() < 4);

  }
  
  
  public static void toggleGrid()
  {
    gridOn = !gridOn;
  }
  
  
  public static MultiSelection getSelection()
  {
    return selection;
  }
  
  public static void clearSelection() 
  {
    selection.clear();
  }
  
  public static boolean hasSelections()
  {
    return !selection.isEmpty();
  }
  
  public static boolean mouseInMap()
  {
    return MouseHandler.getTx() < MAP_WIDTH && 
        MouseHandler.getTy() < MAP_HT;
  }
  
  private void deleteAllItemsWithinSelection()
  {
    ArrayList<TileObject> eraseThese = level.allObjectsWithin(selection);
    
    for (TileObject erase: eraseThese)
    {
      level.removeObject(erase);
    }
  }
  
  public static EditorLevel level()
  {
    return level;
  }
  
  public static void setToolsEnabled(boolean enabled)
  {
    toolbar.setEnabled(enabled);
  }
  
  

}
