package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.objects.ExtendableObject;
import komorebi.bean.editor.objects.HorizontalExtendableObject;
import komorebi.bean.editor.objects.VerticalExtendableObject;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;

public class Grabber extends ClickAndDragAction {

  public enum GrabberDirection
  {
    UP, DOWN, LEFT, RIGHT;
  }

  public Grabber(EditorLevel level) {
    super(level);
  }

  private ExtendableObject grabObj;
  private GrabberDirection grabDir;

  public void startGrabbing(ExtendableObject obj, GrabArrow grab)
  {
    grabObj = obj;
    grabDir = grab.direction();
    
    activate();
  }
 
  @Override
  public void update() {
    switch (grabDir)
    {
      case UP:
        extendUp((VerticalExtendableObject) grabObj);
        break;
      case DOWN:
        extendDown((VerticalExtendableObject) grabObj);
        break;
      case LEFT:
        extendLeft((HorizontalExtendableObject) grabObj);
        break;
      case RIGHT:
        extendRight((HorizontalExtendableObject) grabObj);
        break;
    }

  }

  private void extendLeft(HorizontalExtendableObject horizontalObj)
  {
    int currX = MouseHandler.getTx();
    
    int extendBy = x - currX;
    
    ModRectangle currentLocation = horizontalObj.getArea();
    ModRectangle newLocation = AreaUtilities.grow(currentLocation, 
        extendBy, 0, 0, 0);

    if (extendBy != 0 && 
        horizontalObj.canBeExtendedBy(extendBy) 
        && !level.willOverlapOtherObjectExcludeSelf(
            newLocation, horizontalObj))
    {
      horizontalObj.extendLeft(extendBy);
      x = currX;
    }

  }

  private void extendRight(HorizontalExtendableObject horizontalObj)
  { 
    int currX = MouseHandler.getTx();
    int extendBy = currX-x;
    
    ModRectangle currentLocation = horizontalObj.getArea();
    ModRectangle newLocation = AreaUtilities.grow(currentLocation, 
        0, extendBy, 0, 0);
    
    if (extendBy != 0 && 
        horizontalObj.canBeExtendedBy(extendBy) &&
        !level.willOverlapOtherObjectExcludeSelf(newLocation, 
            horizontalObj))
    {
      horizontalObj.extendRight(extendBy);
      x = currX;
    }


  }

  private void extendUp(VerticalExtendableObject verticalObj)
  {
    int currY = MouseHandler.getTy();

    int extendBy = currY - y;
    
    ModRectangle currentLocation = verticalObj.getArea();
    ModRectangle newLocation = AreaUtilities.grow(currentLocation, 
        0, 0, extendBy, 0);

    if (extendBy != 0 && 
        verticalObj.canBeExtendedBy(extendBy)
        && !level.willOverlapOtherObjectExcludeSelf(newLocation, 
            verticalObj))
    {
      verticalObj.extendUp(extendBy);
      y = currY;
    }
  }

  private void extendDown(VerticalExtendableObject verticalObj)
  {
    int currY = MouseHandler.getTy();
    
    int extendBy = y - currY;
    
    ModRectangle currentLocation = verticalObj.getArea();
    ModRectangle newLocation = AreaUtilities.grow(currentLocation, 
        0, 0, 0, extendBy);


    if (extendBy != 0 && 
        verticalObj.canBeExtendedBy(extendBy)
        && !level.willOverlapOtherObjectExcludeSelf(newLocation, 
            verticalObj))
    {
      verticalObj.extendDown(extendBy);
      y = currY;
    }

  }

}
