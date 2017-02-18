package komorebi.bean.editor;

import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Treadmill extends HorizontalMultiTileObject {

  public Treadmill()
  {

  }

  public Treadmill(int tx, int ty)
  {
    this.tx = tx;
    this.ty = ty;

    length = 2;
  }

  @Override
  public MultiTileObject build(int tx, int ty) {
    return new Treadmill(tx, ty);
  }

  @Override
  public void render() {    
    Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_L][0], tx*16, ty*16);

    for (int j = tx + 1; j < tx + length - 1; j++)
    {
      Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_C][0], 
          j*16, ty*16);
    }

    Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_R][0], 
        (tx+length-1)*16, ty*16);

  }

}
