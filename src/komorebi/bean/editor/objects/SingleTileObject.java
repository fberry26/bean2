package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;

public class SingleTileObject extends TileObject {

  private Tile tile;
  
  private static final SingleTileObject FACTORY = 
      new SingleTileObject();

  public static SingleTileObject factory()
  {
    return FACTORY;
  }
  
  private SingleTileObject()
  {
    super(null, null);
  }
  
  public SingleTileObject(PaletteItem origin, Tile tile, 
      ModRectangle area)
  {
    super(origin, area);
    
    this.tile = tile;
  }

  public void render()
  {
    tile.drawTile(area.x()*16, area.y()*16);
  }
  
  public Tile getTile()
  {
    return tile;
  }

  public TileObject build(PaletteItem origin, ModRectangle area) {
    Tile tileOf = origin.getTile(0);
    
    return new SingleTileObject(origin, tileOf, area);
  }

}
