package komorebi.bean.editor;

import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public abstract class VerticalMultiTileObject extends MultiTileObject {
  public boolean containsPoint(int x, int y)
  {
    return (x == tx && y <= ty && y > ty-length);
  }
  
  public void showNodes()
  {
    Draw.draw(Graphics.EXPAND_ARROW, tx*16+5, ty*16+10, 
        Draw.ROTATE_COUNTERCLOCKWISE, false);
    Draw.draw(Graphics.EXPAND_ARROW, tx*16+5, (ty-length+1)*16,
        Draw.ROTATE_CLOCKWISE, false);
  }
}
