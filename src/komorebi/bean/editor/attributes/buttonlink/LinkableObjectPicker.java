package komorebi.bean.editor.attributes.buttonlink;

import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public abstract class LinkableObjectPicker {
  
  private boolean justStarted;
  
  public LinkableObjectPicker()
  {
    justStarted = true;
  }
  
  public void update()
  {
   
    if (KeyHandler.keyClick(Key.LBUTTON) && !justStarted)
    { 
      Linkable linkable = getLinkableBeingHoveredOver();
      
      if (linkable == null)
        cancel();
      else
        complete(linkable);
    }
    
    if (justStarted)
      justStarted = false;
  }
  
  private Linkable getLinkableBeingHoveredOver()
  {
    for (Linkable linkable: Editor.level().getLinkableObjects())
    {
      if (linkable.containsPoint(MouseHandler.getTx(), 
          MouseHandler.getTy()))
      {
        return linkable;
      }
    }
    
    return null;
  }
  
  public void render()
  {
    for (Linkable linkable: Editor.level().getLinkableObjects())
    {
      for (Rectangle rect: linkable.getArea().getComponentRectangles())
      {
        Draw.fill(Graphics.GREEN_PALETTE_SELECTION, rect.x*16, rect.y*16,
            rect.width*16, rect.height*16);
      }
    }
  }
  
  public abstract void complete(Linkable link);
  public abstract void cancel();
}
