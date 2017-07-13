package komorebi.bean.editor.tools;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Tool;
import komorebi.bean.editor.objects.ExtendableObject;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.tools.clickanddrag.Dragger;
import komorebi.bean.editor.tools.clickanddrag.Grabber;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class ClickAndDragTool extends Tool {
  
  private Grabber grabber;
  private Dragger dragger;

  public ClickAndDragTool(EditorLevel level) {
    super(level);
    
    grabber = new Grabber(level);
    dragger = new Dragger(level);
  }

  @Override
  public void update() {
    super.update();
    
    if (KeyHandler.keyClick(Key.LBUTTON))
    {      
      if (isHoveringOverAnyGrabArrows())
      {
        handleGrabs();
      } else 
      {
        handleDrags();
      }
    }
   
    if (grabber.isActive())
    {
      grabber.update();
    } else if (dragger.isActive())
    {
      dragger.update();
    }

    deselectUponMouseRelease();
  }
  
  private void handleGrabs()
  {
    ExtendableObject hoveringOver = 
        getExtendableObjectBeingHoveredOver();
        
    grabber.anchor(MouseHandler.getTx(), MouseHandler.getTy());
    grabber.startGrabbing(hoveringOver, 
        hoveringOver.getGrabberBeingHoveredOver()); 
  }
  
  private void handleDrags()
  {
    if (Editor.hasSelections() && Editor.getSelection().contains(
        MouseHandler.getTileLocation()))
    {
      Editor.getSelection().lockObjects();
      
      dragger.anchor(MouseHandler.getTx(), MouseHandler.getTy());
      dragger.startDragging(Editor.getSelection());
    } else if (isHoveringOverAnyObject())
    {
      dragger.anchor(MouseHandler.getTx(), MouseHandler.getTy());
      dragger.startDragging(getTileObjectBeingHoveredOver());
    } 
  }



  private void deselectUponMouseRelease()
  {
    if (!KeyHandler.keyDown(Key.LBUTTON))
    {
      if (grabber.isActive())
      {     
        grabber.release();
      }
      
      if (dragger.isActive())
      {
        dragger.release();
      }
    }
  }

  @Override
  public void render() {
    for (ExtendableObject obj: level.getExtendableObjects())
      obj.showArrows();
  }

  private boolean isHoveringOverAnyGrabArrows()
  {
    for (ExtendableObject obj: level.getExtendableObjects())
    {
      if (obj.userHoveringOverAGrabber())
        return true;
    }
    
    return false;
  }
 

  private TileObject getTileObjectBeingHoveredOver()
  {
    for (TileObject obj: level.getAllObjects())
    {
      if (obj.isBeingHoveredOver())
        return obj;
    }

    return null;
  }
  
  private ExtendableObject getExtendableObjectBeingHoveredOver()
  {
    for (ExtendableObject obj: level.getExtendableObjects())
    {
      if (obj.isBeingHoveredOver())
        return obj;
    }
    
    throw new RuntimeException("Pre-condition unmet: no extendable"
        + " object being hovered over");
  }

  
  private boolean isHoveringOverAnyObject()
  {
    for (TileObject obj: level.getAllObjects())
    {
      if (obj.isBeingHoveredOver())
        return true;
    }
    
    return false;
  }



  
}
