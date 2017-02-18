package komorebi.bean.editor;

import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public abstract class HorizontalMultiTileObject extends MultiTileObject {
  @Override
  public boolean containsPoint(int x, int y) {
    return (y == ty && x >= tx && x < tx+length);
  }
  
  public void showNodes()
  {
    Draw.draw(Graphics.EXPAND_ARROW, tx*16, ty*16+5, 0, true);
    Draw.draw(Graphics.EXPAND_ARROW, (tx+length-1)*16+10, ty*16+5);
  }
}
