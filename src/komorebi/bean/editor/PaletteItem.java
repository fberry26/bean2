package komorebi.bean.editor;

import java.awt.Point;

import komorebi.bean.editor.objects.Button;
import komorebi.bean.editor.objects.Gate;
import komorebi.bean.editor.objects.HorizontalPlatform;
import komorebi.bean.editor.objects.HorizontalSpikeStrip;
import komorebi.bean.editor.objects.Ladder;
import komorebi.bean.editor.objects.OnePerLevelObject;
import komorebi.bean.editor.objects.OnePerLevelObject.Bean;
import komorebi.bean.editor.objects.OnePerLevelObject.Flag;
import komorebi.bean.editor.objects.Piston;
import komorebi.bean.editor.objects.SingleTileObject;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.objects.Treadmill;
import komorebi.bean.editor.objects.VerticalPlatform;
import komorebi.bean.editor.objects.VerticalSpikeStrip;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;

public enum PaletteItem {
  
  BLOCK(SingleTileObject.factory(), Tile.BLOCK), 
  BEAN(SingleTileObject.factory(), Tile.BEAN), 
  FLAG(SingleTileObject.factory(), Tile.FLAG),
  BUTTON(new Button(null, null), Tile.BUTTON),
  
  SPIKE_UP(Orientation.HORIZONTAL, 
      new HorizontalSpikeStrip(null, false), Tile.SPIKE_ALONE_UP),
  SPIKE_RIGHT(Orientation.VERTICAL, new VerticalSpikeStrip(null, false),
      Tile.SPIKE_ALONE_RIGHT), 
  SPIKE_DOWN(Orientation.HORIZONTAL, new HorizontalSpikeStrip(null, true),
      Tile.SPIKE_ALONE_DOWN),
  SPIKE_LEFT(Orientation.VERTICAL, new VerticalSpikeStrip(null, true),
      Tile.SPIKE_ALONE_LEFT), 
  
  PLATFORM_HORIZ(Orientation.HORIZONTAL, new HorizontalPlatform(null), Tile.PLATFORM_LEFT_HORIZ, 
      Tile.PLATFORM_RIGHT_HORIZ),
  PLATFORM_VERT(Orientation.HORIZONTAL, new VerticalPlatform(null), Tile.PLATFORM_LEFT_VERT, 
      Tile.PLATFORM_RIGHT_VERT),
  
  PISTON(Orientation.VERTICAL, new Piston(null), 
      Tile.PISTON_MID, Tile.PISTON_END),
  GATE_VERT(new Gate(), Tile.GATE_VERT),
  TREADMILL(Orientation.HORIZONTAL, new Treadmill(null), 
      Tile.TREADMILL_L, Tile.TREADMILL_R),

  GATE_HORIZ(new Gate(), Tile.GATE_HORIZ),
  TURRET_LEFT(SingleTileObject.factory(), Tile.TURRET_LEFT), 
  TURRET_RIGHT(SingleTileObject.factory(), Tile.TURRET_RIGHT),
  
  LADDER(Orientation.VERTICAL, new Ladder(null), Tile.LADDER);
  Tile[] tiles;
  boolean groupHorizontally;
  
  private TileObject factory;
  private boolean enabled = true;

  private enum Orientation
  {
    VERTICAL, HORIZONTAL;
  }
  
  private PaletteItem(Tile...tiles)
  {
    this(Orientation.HORIZONTAL, null, tiles);
  }
  
  private PaletteItem(TileObject factory, Tile...tiles)
  {
    this(Orientation.HORIZONTAL, factory, tiles);
  }
  
  private PaletteItem(Orientation orientation, TileObject factory,
      Tile...tiles)
  {
    this.tiles = tiles;
    this.factory = factory;
    
    groupHorizontally = (orientation == Orientation.HORIZONTAL);
  }
  
  public boolean contains(Tile contained)
  {
    for (Tile tile: tiles)
      if (tile == contained)
        return true;
    return false;
  }
  
  public boolean isGroupedHorizontally()
  {
    return groupHorizontally;
  }
  
  public int getNumberOfTiles()
  {
    return tiles.length;
  }
  
  public boolean isMultiTileObject()
  {
    return factory != null && factory != SingleTileObject.factory();
  }
  
  public Tile getTile(int i)
  {
    return tiles[i];
  }
  
  public boolean onlyOnePermittedPerLevel()
  {
    return this == FLAG || this == BEAN;
  }
  
  private static Tile[][] palette = initializePalette();
  public static Tile[][] formatAsPalette()
  {
    return palette;
  }
  
  private static Tile[][] initializePalette()
  {
    Tile[][] palette = new Tile[6][4];
    
    int row = 0, col = 0;
    
    for (int i = 0; i < values().length; i++)
    {
      PaletteItem palItem = values()[i];
      for (int j = 0; j < palItem.getNumberOfTiles(); j++)
      {
        if (palItem.isGroupedHorizontally())
          palette[row][col + j] = palItem.getTile(j);
        else
          palette[row + j][col] = palItem.getTile(j);
      }
      
      while (palette[row][col] != null)
      {
        col++;
        if (col >= 4)
        {
          row++;
          col = 0;
        }        
      }
    }
    
    return palette;
  }
  
  public static PaletteItem getItemContainingTile(Tile tile)
  {
    for (PaletteItem pItem: values())
    {
      if (pItem.contains(tile))
        return pItem;
    }
    
    return null;
  }
  
  public static Class<? extends OnePerLevelObject> getOnePerLevelClass(
      PaletteItem pal)
    throws RuntimeException
  {
    switch (pal)
    {
      case BEAN:
        return Bean.class;
      case FLAG:
        return Flag.class;
      default:
        throw new RuntimeException(pal + " is not limited to one per level");
    }
      
  }
  
  public static Point topLeftOf(PaletteItem pItem)
  {
    for (int i = 0; i < palette.length; i++)
    {
      for (int j = 0; j < palette[i].length; j++)
      {
        if (pItem.contains(palette[i][j]))
        {
          return new Point(j*16, (4-i)*16);
        }
      }
    }
    
    return new Point(0, 0);
  }
  
  public TileObject buildObject(ModRectangle area)
  {
    return factory.build(this, area);
  }
  
  public ModRectangle createRectangleAt(int tx, int ty)
  {
    
    int x = tx;
    int y = isGroupedHorizontally()?ty:ty-getNumberOfTiles()+1;
    
    return new ModRectangle(x, y, getWidth(), getHeight());
  }
  
  public ModRectangle createRectangleAt(Point point)
  {
    return createRectangleAt(point.x, point.y);
  }
  
  public int getWidth()
  {
    return isGroupedHorizontally()?getNumberOfTiles():1;
  }
  
  public int getHeight()
  {
    return isGroupedHorizontally()?1:getNumberOfTiles();
  }
  
  public static boolean isPaletteItemEnabled(Tile tile)
  {    
    PaletteItem item = getItemContainingTile(tile);
    return item.isEnabled();
  }
  
  public void setEnabled(boolean bool)
  {
    enabled = bool;
  }
  
  public boolean isEnabled()
  {
    return enabled;
  }
  
  public static void setAreOnePerLevelObjectsEnabled(boolean bool)
  {
    for (PaletteItem pal: values())
    {
      if (pal.onlyOnePermittedPerLevel())
      {
        pal.setEnabled(bool);
      }
    }
  }
  
}
