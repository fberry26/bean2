package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.Shape;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;

public abstract class ExtendableObject extends MultiTileObject {

  protected GrabArrow forwardArrow;
  protected GrabArrow backwardArrow;
  
  public ExtendableObject(PaletteItem origin, Shape area,
      GrabArrow forward, GrabArrow backward) {
    super(origin, area);
    
    forwardArrow = forward;
    backwardArrow = backward;
  }
  
  public abstract void extendForward(int dx, int dy);
  public abstract void extendBackward(int dx, int dy);
  
  public abstract boolean canExtendForward(int dx, int dy);
  public abstract boolean canExtendBackward(int dx, int dy);
  
  public boolean isUserHoveringOverAGrabber()
  {
    return isUserHoveringOverForwardGrabber() || 
        isUserHoveringOverBackwardGrabber();
  }
  
  public boolean isUserHoveringOverForwardGrabber()
  {
    return forwardArrow.isUserHoveringOver();
  }
  
  public boolean isUserHoveringOverBackwardGrabber()
  {
    return backwardArrow.isUserHoveringOver();
  }
  
  public void showArrows()
  {
    forwardArrow.render();
    backwardArrow.render();
  }
  
  public void moveBy(int dx, int dy)
  {
    super.moveBy(dx, dy);
    forwardArrow.move(dx, dy);
    backwardArrow.move(dx, dy);
  }

}
