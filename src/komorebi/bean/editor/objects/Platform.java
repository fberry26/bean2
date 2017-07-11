package komorebi.bean.editor.objects;

import java.awt.Rectangle;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class Platform extends HorizontalExtendableObject {

  private static final Image PATH_RED_FILL = new Image(100, 32, 1, 1,
      Draw.MISCELLANY);
  private int offset;

  public Platform(PaletteItem origin)
  {
    super(origin, null);
  }

  private Platform(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    horizontal = true;

    length = 2;
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Platform(origin, area);
  }
  
  @Override
  public void extendLeft(int add)
  {
    super.extendLeft(add);
    
    offset += add;
  }

  @Override
  public void render() {    
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_L][Tile.getColor()], 
        modX(area.x+offset)*16+6, modY(area.y)*16);
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_R][Tile.getColor()], 
        modX(area.x+offset+1)*16, modY(area.y)*16);

    
    for (Rectangle component: area.getComponentRectangles())
    {
      Draw.fill(PATH_RED_FILL, component.x*16, component.y*16, 
          component.width*16, component.height*16);
    }
  }

  @Override
  public void moveBy(int dx, int dy)
  {
    int originalX = MouseHandler.getTx() - dx;

    if (dx != 0 && overPlatform(originalX) && length > minLength())
    {
      int newOffset = offset + dx;

      if (newOffset >= 0 && newOffset < length - 1)
      {
        offset += dx;
        super.moveBy(0, dy);
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
  public int minLength() {
    return 2;
  }

  private boolean overPlatform(int x)
  {
    return x == modX(area.x + offset) || x == modX(area.x + offset + 1);
  }

}
