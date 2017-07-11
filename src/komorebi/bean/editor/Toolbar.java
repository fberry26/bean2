package komorebi.bean.editor;

import java.awt.Rectangle;

import komorebi.bean.editor.tools.TilePickerTool;
import komorebi.bean.editor.tools.EraserTool;
import komorebi.bean.editor.tools.FloodTool;
import komorebi.bean.editor.tools.ClickAndDragTool;
import komorebi.bean.editor.tools.PencilTool;
import komorebi.bean.editor.tools.SelectTool;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class Toolbar {

  private int currentTool;
  private Tool[] tools = new Tool[6];
  
  private Palette palette;
  
  private static final Rectangle TOOLBAR_AREA = 
      new Rectangle(Editor.MAP_WIDTH-6, Editor.MAP_HT, 6, 1);
  
  private static final Image EDITOR_TOOLS = new Image(112, 0, 96, 16,
      Draw.MISCELLANY);
  
  public Toolbar(EditorLevel level, Palette palette)
  {
    this.palette = palette;
    
    tools[0] = new PencilTool(level, palette);
    tools[1] = new EraserTool(level);
    tools[2] = new FloodTool(level, palette);
    tools[3] = new TilePickerTool(level, palette);
    tools[4] = new SelectTool(level);
    tools[5] = new ClickAndDragTool(level);
  }
  
  public void update()
  {
    if (KeyHandler.controlDown())
      if (KeyHandler.keyClick(Key.RIGHT))
        nextMouseMode();
      else if (KeyHandler.keyClick(Key.LEFT))
        previousMouseMode();
    
    if (userClickedOnToolbar())
    {
      activateMouseModeBeingHoveredOver();
    }
    
    currentTool().update();
  }
  
  public void render()
  {
    Draw.draw(EDITOR_TOOLS, TOOLBAR_AREA.x*16, TOOLBAR_AREA.y*16);
    Draw.fill(Graphics.BLUE_SELECTED_FUNCTION, 16*(TOOLBAR_AREA.x + currentTool), 
        TOOLBAR_AREA.y*16, 16, 16);
    
    currentTool().render();
  }
  
  private Tool currentTool()
  {
    return tools[currentTool];
  }
  
  private void nextMouseMode()
  {
    currentTool++;
    if (currentTool >= tools.length)
    {
      currentTool = 0;
    }
    
    uponEnteringNewTool();
  }
  
  private void previousMouseMode()
  {
    currentTool--;
    if (currentTool < 0)
    {
      currentTool = tools.length - 1;
    }
    
    uponEnteringNewTool();
  }
  
  private void activateMouseModeBeingHoveredOver()
  {
    currentTool = MouseHandler.getTx() - TOOLBAR_AREA.x;
    uponEnteringNewTool();
  }
  
  private boolean userClickedOnToolbar()
  {
    return KeyHandler.keyClick(Key.LBUTTON) && 
        TOOLBAR_AREA.contains(MouseHandler.getTileLocation());
  }
  
  private void uponEnteringNewTool()
  {
    if (tools[currentTool] instanceof FloodTool)
    {
      PaletteItem.setAreOnePerLevelObjectsEnabled(false);
      
      if (palette.getCurrentItem().onlyOnePermittedPerLevel())
      {
        palette.setCurrentItem(PaletteItem.BLOCK);
      }
    } else
    {
      PaletteItem.setAreOnePerLevelObjectsEnabled(true);
    }
  }
}
