package komorebi.bean.editor.objects;

import java.awt.Rectangle;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class VerticalPlatform extends VerticalExtendableObject {

  private static final Image PATH_RED_FILL = new Image(100, 32, 1, 1,
      Draw.MISCELLANY);
  private int offset;

  public VerticalPlatform(PaletteItem origin)
  {
    super(origin, null);
  }

  private VerticalPlatform(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    horizontal = false;

    length = 1;
  }

  @Override
  public void extendDown(int add)
  {
    if (-add <= offset)
    {
      super.extendDown(add);
      offset += add;
    }
  }
  
  public void extendUp(int add)
  {
    if (area.height + add > offset)
    {
      super.extendUp(add);
    }
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new VerticalPlatform(origin, area);
  }

  @Override
  public void render() {    
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_L][Tile.getColor()], 
        modX(area.x)*16+6, modY(area.y+offset)*16);
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_R][Tile.getColor()], 
        modX(area.x+1)*16, modY(area.y+offset)*16);

    for (Rectangle component: area.getComponentRectangles())
    {
      Draw.fill(PATH_RED_FILL, component.x*16, component.y*16, 
          component.width*16, component.height*16);
    }
  }

  @Override
  public int minLength() {
    return 1;
  }
  
  @Override
  public void moveBy(int dx, int dy)
  {
    int originalY = MouseHandler.getTy() - dy;

    if (dy != 0 && overPlatform(originalY) && length > minLength())
    {
      int newOffset = offset + dy;

      if (newOffset >= 0 && newOffset < length - 1)
      {
        offset += dy;
        super.moveBy(dx, 0);
      } else
      {
        super.moveBy(dx, dy);
      }

    } else
    {
      super.moveBy(dx, dy);
    }
  }
  
  @Override
  public boolean canBeMovedBy(int dx, int dy)
  {
    int originalY = MouseHandler.getTy() - dy;

    if (dy != 0 && overPlatform(originalY) && length > minLength())
    {
      int newOffset = offset + dy;

      if (newOffset >= 0 && newOffset < length - 1)
      {
        return dx == 0;
      } 
    }
    
    return super.canBeMovedBy(dx, dy);
  }
  
  private boolean overPlatform(int y)
  {
    return y == modY(area.y + offset);
  }
}
