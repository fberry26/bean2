package komorebi.bean.editor;

import java.awt.Point;

import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Palette {
    
  private static final int PAL_BOTTOM = Editor.MAP_HT - 5, 
      PAL_TOP = Editor.MAP_HT - 1,
      PAL_LEFT = Editor.MAP_WIDTH;
  
  private PaletteItem currPalItem;
  private Tile[][] palette;
  
  public Palette()
  {
    currPalItem = PaletteItem.BLOCK;
    palette = PaletteItem.formatAsPalette();
  }
  
  public void update()
  {
    if (KeyHandler.keyClick(Key.LBUTTON) && mouseInPalette() &&
        isCurrentTileActive())
    {
      currPalItem = PaletteItem.getItemContainingTile(currentTile());
    }
  }
  
  public void render()
  {
    for (int i = 0; i < palette.length; i++)
    {
      for (int j = 0; j < palette[i].length; j++)
      {         
        if (isActiveTile(palette[i][j]))
        {  
          palette[i][j].drawTile(16*(Editor.MAP_WIDTH + j), 
              16*(Editor.MAP_HT - 1 - i));
        }
      }
    }
    
    Point selectedTopLeft = PaletteItem.topLeftOf(currPalItem);
    selectedTopLeft.x += PAL_LEFT*16;
    selectedTopLeft.y += PAL_BOTTOM*16;
    
    if (currPalItem.isGroupedHorizontally())
    {
      Draw.fill(Graphics.GREEN_PALETTE_SELECTION, selectedTopLeft.x, 
          selectedTopLeft.y, 16*currPalItem.getNumberOfTiles(), 16);
    } else
    {
      Draw.fill(Graphics.GREEN_PALETTE_SELECTION, selectedTopLeft.x, 
          selectedTopLeft.y - (currPalItem.getNumberOfTiles()-1)*16, 
          16, 16*currPalItem.getNumberOfTiles());
    }
  }
  
  private boolean isActiveTile(Tile tile)
  { 
    return tile != null && tile != Tile.BLANK && 
        PaletteItem.isPaletteItemEnabled(tile);
  }
  
  private boolean isCurrentTileActive()
  {
    return PaletteItem.isPaletteItemEnabled(currentTile());
  }
  
  private boolean mouseInPalette()
  {
    return MouseHandler.getTx() >= PAL_LEFT && 
        MouseHandler.getTy() >= PAL_BOTTOM && 
        MouseHandler.getTy() <= PAL_TOP && 
        !(MouseHandler.getTx()==PAL_LEFT+2 &&
          MouseHandler.getTy()==PAL_BOTTOM);
  }
  
  private Tile currentTile()
  {
    return palette[4-(MouseHandler.getTy()-(Editor.MAP_HT-5))]
        [MouseHandler.getTx()-(Editor.MAP_WIDTH)];
  }
  
  public PaletteItem getCurrentItem()
  {
    return currPalItem;
  }
  
  public void setCurrentItem(PaletteItem pal)
  {
    currPalItem = pal;
  }
  
}
