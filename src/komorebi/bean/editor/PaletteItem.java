package komorebi.bean.editor;

import java.awt.Point;

import komorebi.bean.game.Tile;

public enum PaletteItem {
  
  BLOCK(Tile.BLOCK), BEAN(Tile.BEAN), FLAG(Tile.FLAG),
  BUTTON(Tile.BUTTON), 
  SPIKE_UP(Orientation.HORIZONTAL, 
      new HorizontalSpikeStrip(false), Tile.SPIKE_ALONE_UP),
  SPIKE_RIGHT(Orientation.VERTICAL, new VerticalSpikeStrip(false),
      Tile.SPIKE_ALONE_RIGHT), 
  SPIKE_DOWN(Orientation.HORIZONTAL, new HorizontalSpikeStrip(true),
      Tile.SPIKE_ALONE_DOWN),
  SPIKE_LEFT(Orientation.VERTICAL, new VerticalSpikeStrip(true),
      Tile.SPIKE_ALONE_LEFT), 
  TURRET_LEFT(Tile.TURRET_LEFT), 
  TURRET_RIGHT(Tile.TURRET_RIGHT),
  LADDER(Tile.LADDER), GATE_HORIZ(Tile.GATE_HORIZ),
  PISTON(Orientation.VERTICAL, new Piston(), Tile.PISTON_MID, Tile.PISTON_END),
  TREADMILL(Orientation.HORIZONTAL, new Treadmill(), 
      Tile.TREADMILL_L, Tile.TREADMILL_R),
  GATE_VERT(Tile.GATE_VERT), 
  PLATFORM(Tile.PLATFORM);
  ;
  
  Tile[] tiles;
  boolean groupHorizontally;
  
  private MultiTileObject factory;

  private enum Orientation
  {
    VERTICAL, HORIZONTAL;
  }
  
  private PaletteItem(Tile...tiles)
  {
    this(Orientation.HORIZONTAL, null, tiles);
  }
  
  private PaletteItem(Orientation orientation, MultiTileObject factory,
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
    return tiles.length != 1 || this == SPIKE_RIGHT ||
        this == SPIKE_LEFT || this == SPIKE_UP ||
        this == SPIKE_DOWN;
  }
  
  public Tile getTile(int i)
  {
    return tiles[i];
  }
  
  private static Tile[][] palette = initializePalette();
  public static Tile[][] formatAsPalette()
  {
    return palette;
  }
  
  private static Tile[][] initializePalette()
  {
    Tile[][] palette = new Tile[5][4];
    
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
  
  public MultiTileObject buildObject(int tx, int ty)
  {
    return factory.build(tx, ty);
  }
}
