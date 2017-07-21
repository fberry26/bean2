package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.objects.ExtendableObject;
import komorebi.bean.engine.MouseHandler;

public class Grabber extends ClickAndDragAction {

  public Grabber(EditorLevel level) {
    super(level);
  }

  private ExtendableObject grabObj;
  private boolean grabbingForward;

  public void startGrabbing(ExtendableObject obj, boolean forward)
  {
    grabObj = obj;
    grabbingForward = forward;
    
    activate();
  }
 
  @Override
  public void update() {

    int dx = MouseHandler.getTx() - x,
        dy = MouseHandler.getTy() - y;
    
    if (dx != 0 || dy != 0)
    {      
      if (grabbingForward && grabObj.canExtendForward(dx, dy))
      {
        grabObj.extendForward(dx, dy);
        
        x+=dx;
        y+=dy;
      } else if (!grabbingForward && grabObj.canExtendBackward(-dx, -dy))
      {
        grabObj.extendBackward(-dx, -dy);
        
        x+=dx;
        y+=dy;
      }
    }
  }
}
