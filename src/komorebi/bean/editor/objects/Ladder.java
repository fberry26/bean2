package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Ladder extends VerticalExtendableObject {
  
  public Ladder(PaletteItem origin)
  {
    super(origin, null);
  }
  
  private Ladder(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    
    length = 1;
  }
  
  @Override
  public int minLength() {
    return 1;
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Ladder(origin, area);
  }

  @Override
  public void render() {

    for (int i = 0; i < length; i++)
    {
      Draw.draw(Graphics.LADDER[Tile.getColor()], modX(area.x)*16, 
          modY(area.y + i)*16);
    }

  }

}
