package komorebi.bean.editor.tools;

import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;
import komorebi.bean.graphics.Transformation;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class VerticalGrabArrow extends GrabArrow {

  private VerticalGrabArrow(ModRectangle area, boolean forward) {
    super(area, forward);
  }

  @Override
  public void render() {
    Transformation rotation = (isForward())?Transformation.ROTATE_COUNTERCLOCKWISE:
      Transformation.ROTATE_CLOCKWISE;
    
    Draw.draw(Graphics.EXPAND_ARROW, area.x, area.y, rotation);
  }
  
  public static VerticalGrabArrow createUpArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 +
        center(objLoc.width*16, 9), 
        (int) (objLoc.getMaxY()*16-8), 9, 7,
        Editor.MODSPACE);
    
    return new VerticalGrabArrow(arrowLoc, true);
  }

  public static VerticalGrabArrow createDownArrow(Rectangle objLoc)
  {
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 +
        center(objLoc.width*16, 9), 
        (int) (objLoc.y*16), 9, 7, Editor.MODSPACE);
    
    return new VerticalGrabArrow(arrowLoc, false);
  }
  
  private static int center(int largerWidth, int smallerWidth)
  {
    return (largerWidth - smallerWidth) / 2 + 1;
  }
}
