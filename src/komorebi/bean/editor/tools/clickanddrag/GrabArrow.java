package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;

public abstract class GrabArrow {
  
  protected ModRectangle area;
  private boolean forward;
  
  protected GrabArrow(ModRectangle area, boolean forward)
  {
    this.area = area;
    this.forward = forward;
  }
  
  public boolean isForward()
  {
    return forward;
  }
  
  public boolean isUserHoveringOver()
  {
    return area.contains(MouseHandler.getLocation());
  }
  
  public void move(int dx, int dy)
  {
    area.changeLocation(dx*16, dy*16);
  }
  
  public abstract void render();
}
