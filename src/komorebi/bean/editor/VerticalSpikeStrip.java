package komorebi.bean.editor;

import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class VerticalSpikeStrip extends VerticalMultiTileObject {

 private boolean pointsLeft;
  
  public VerticalSpikeStrip(boolean pointsLeft)
  {
    this.pointsLeft = pointsLeft;
  }
  
  public VerticalSpikeStrip(boolean pointsLeft, int tx, int ty)
  {
    this.pointsLeft = pointsLeft;

    this.tx = tx;
    this.ty = ty;

    length = 1;
  }
  
  public MultiTileObject build(int tx, int ty) {
    System.out.println("Factory has pointsLeft = " + pointsLeft);
    
    return new VerticalSpikeStrip(pointsLeft, tx, ty);
  }

  @Override
  public void render() {    
    if (length == 1)
    {
      Draw.draw(Graphics.SPIKES[Graphics.SPIKE_ALONE][Tile.getColor()],
          tx*16, ty*16, 
          pointsLeft?Draw.ROTATE_COUNTERCLOCKWISE:Draw.ROTATE_CLOCKWISE, 
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
