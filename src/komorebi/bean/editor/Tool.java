package komorebi.bean.editor;

import komorebi.bean.engine.MouseHandler;

public abstract class Tool {

  public abstract void render();
  
  protected EditorLevel level;
  private int mx, my;
  private int prevMx, prevMy;
  
  public Tool(EditorLevel level)
  {
    this.level = level;
  }
  
  public void update()
  {
    prevMx = mx;
    prevMy = my;
    
    mx = MouseHandler.getTx();
    my = MouseHandler.getTy();
  }
  
  protected boolean isMouseInMap()
  {
    return (MouseHandler.getTx() < Editor.MAP_WIDTH && 
        MouseHandler.getTy() < Editor.MAP_HT);
  }
  
  protected boolean mouseSame()
  {
    return (prevMx == mx) && (prevMy == my);
  }
  
  protected boolean mouseInBounds()
  {
    if (Editor.hasSelections())
      return Editor.getSelection().contains(MouseHandler.getTileLocation());
    
    return Editor.MAP.contains(MouseHandler.getTileLocation());
  }
}
