package komorebi.bean.editor.objects;

import java.awt.Point;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;

public class OnePerLevelObject extends SingleTileObject {

  public OnePerLevelObject(PaletteItem origin, Tile tile, ModRectangle area) {
    super(origin, tile, area);
  }
  
  public static class Bean extends OnePerLevelObject {

    public Bean(Point location) {
      super(PaletteItem.BEAN, Tile.BEAN, 
          new ModRectangle(location.x, location.y, 1, 1));
    }
  }
  
  public static class Flag extends OnePerLevelObject {

    public Flag(Point location) {
      super(PaletteItem.FLAG, Tile.FLAG, 
          new ModRectangle(location.x, location.y, 1, 1));
    }
    
  }

}
