package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.EditorLevel;

public abstract class ClickAndDragAction {

  protected int x, y;
  protected EditorLevel level;
  
  protected boolean active;
  
  public ClickAndDragAction(EditorLevel level)
  {
    this.level = level;
  }
  
  public void anchor(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public boolean isActive()
  {
    return active;
  }
  
  protected void activate()
  {
    active = true;
  }
  
  public void release()
  {
    active = false;
  }
  
  public abstract void update();
}
