package komorebi.bean.editor.tools.clickanddrag;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.engine.MouseHandler;

public class Dragger extends ClickAndDragAction {

  private Draggable drag;

  public Dragger(EditorLevel level) {
    super(level);
  }

  public void startDragging(Draggable drag)
  {
    this.drag = drag;

    activate();
  }

  public void update()
  {
    int currX = MouseHandler.getTx();
    int currY = MouseHandler.getTy();
    
    int dx = currX - x, dy = currY - y;

    if (dx != 0 || dy != 0)
    {      
      if (drag.canBeMovedBy(dx, dy))
      {
        drag.moveBy(dx, dy);
        x = currX;
        y = currY;
      }
    }
  }
  
  public void release()
  {
    super.release();
    
    if (drag instanceof Selection)
    {
      ((Selection) drag).unlockObjects();
    }
  }
}
