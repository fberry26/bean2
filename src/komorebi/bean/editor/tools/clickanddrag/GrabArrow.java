package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.Grabber.GrabberDirection;
import komorebi.bean.engine.MouseHandler;

public abstract class GrabArrow {
  
  protected ModRectangle area;
  protected GrabberDirection direction;
  
  protected GrabArrow(GrabberDirection direction, ModRectangle area)
  {
    this.direction = direction;
    this.area = area;
  }
  
  public boolean isUserHoveringOver()
  {
    return area.contains(MouseHandler.getLocation());
  }
  
  public GrabberDirection direction()
  {
    return direction;
  }
  
  public void move(int dx, int dy)
  {
    area.changeLocation(dx*16, dy*16);
  }
  
  public abstract void render();
}
