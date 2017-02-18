package komorebi.bean.editor;

import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Piston extends VerticalMultiTileObject{
  
  public Piston()
  {
    
  }
  
  public Piston(int tx, int ty)
  {
    this.tx = tx;
    this.ty = ty;
    
    length = 2;
  }

  @Override
  public MultiTileObject build(int tx, int ty) {
    return new Piston(tx, ty);
  }

  @Override
  public void render() {
    
    for (int i = ty; i > ty - length + 1; i--)
    {
      Draw.draw(Graphics.PISTON[Graphics.PISTON_MID][Tile.getColor()], 
          tx*16, i*16);
    }
    
    Draw.draw(Graphics.PISTON[Graphics.PISTON_END][Tile.getColor()], 
        tx*16, (ty-length+1)*16);
    
  }
  
}
