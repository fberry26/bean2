package komorebi.bean.editor;

import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class HorizontalSpikeStrip extends HorizontalMultiTileObject {

  private boolean upsideDown;
  
  public HorizontalSpikeStrip(boolean upsideDown)
  {
    this.upsideDown = upsideDown;
  }
  
  public HorizontalSpikeStrip(boolean upsideDown, int tx, int ty)
  {
    this.tx = tx;
    this.ty = ty;
    
    this.upsideDown = upsideDown;

    length = 1;
  }
  
  public MultiTileObject build(int tx, int ty) {
    return new HorizontalSpikeStrip(upsideDown, tx, ty);
  }

  @Override
  public void render() {    
    if (length == 1)
    {
      Draw.draw(Graphics.SPIKES[Graphics.SPIKE_ALONE][Tile.getColor()],
          tx*16, ty*16, upsideDown?Draw.ROTATE_180:Draw.ROTATE_NONE, 
              false);
    } else
    {
    }
  }
 
  @Override
  public boolean containsPoint(int x, int y) {
    return (y == ty && x >= tx && x < tx+length);
  }
  
}
