package komorebi.bean.editor.tools;

import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;
import komorebi.bean.editor.tools.clickanddrag.Grabber.GrabberDirection;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class VerticalGrabArrow extends GrabArrow {

  private VerticalGrabArrow(GrabberDirection direction, ModRectangle area) {
    super(direction, area);
  }

  @Override
  public void render() {
    int rotation = (direction == GrabberDirection.UP)?Draw.ROTATE_COUNTERCLOCKWISE:
      Draw.ROTATE_CLOCKWISE;
    
    Draw.draw(Graphics.EXPAND_ARROW, area.x, area.y, rotation, false);
  }
  
  public static VerticalGrabArrow createUpArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 + 5, 
        (int) (objLoc.getMaxY()*16-8), 9, 7,
        Editor.MODSPACE);
    
    return new VerticalGrabArrow(GrabberDirection.UP, arrowLoc);
  }

  public static VerticalGrabArrow createDownArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 + 5, 
        (int) (objLoc.y*16), 9, 7, Editor.MODSPACE);
    
    return new VerticalGrabArrow(GrabberDirection.DOWN, arrowLoc);
  }
}
