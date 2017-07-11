package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.Grabber.GrabberDirection;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class HorizontalGrabArrow extends GrabArrow {

  private HorizontalGrabArrow(GrabberDirection direction,
      ModRectangle location)
  {
    super(direction, location);
  }
  
  public static HorizontalGrabArrow createLeftArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16, objLoc.y*16+5, 
        7, 9, Editor.MODSPACE);
    
    return new HorizontalGrabArrow(GrabberDirection.LEFT,
        arrowLoc);
  }
  
  public static HorizontalGrabArrow createRightArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle((int) (objLoc.getMaxX()*16-8), 
        objLoc.y*16+5, 7, 9, Editor.MODSPACE);
    return new HorizontalGrabArrow(GrabberDirection.RIGHT,
        arrowLoc);
  }

  @Override
  public void render() {
    boolean flipImage = direction == GrabberDirection.LEFT;
    
    Draw.draw(Graphics.EXPAND_ARROW, area.x, area.y, 0, flipImage);
    
  }
  
}
