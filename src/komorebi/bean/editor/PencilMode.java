package komorebi.bean.editor;

import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.game.Tile;

import static komorebi.bean.editor.Editor.getMouseTx;
import static komorebi.bean.editor.Editor.getMouseTy;

public class PencilMode extends MouseMode {

  private Palette palette;

  public PencilMode(EditorLevel level, Palette palette) {
    super(level);
    this.palette = palette;
  }

  @Override
  public void update() {
    super.update();
    
    PaletteItem palItem = palette.getCurrentItem();

    if (isMouseInMap() && 
        (KeyHandler.keyClick(Key.LBUTTON) || 
            (KeyHandler.keyDown(Key.LBUTTON) && 
        !mouseSame())) && !willOverlapOtherObject(palItem))
    {
      if (palItem.isMultiTileObject())
      {
        placeMultiTileObject();
      } else
      {
        placeTile();
      }
    }
  }

  private void placeTile()
  {
    PaletteItem palItem = palette.getCurrentItem();

    level.getTiles()[getMouseTy()][getMouseTx()] = 
        palItem.getTile(0);
  }

  private void placeMultiTileObject()
  {
    PaletteItem palItem = palette.getCurrentItem();
    
    int tx = getMouseTx(), ty = getMouseTy();

    MultiTileObject obj = palItem.buildObject(tx, ty);

    if ((palItem.isGroupedHorizontally() && 
        tx + obj.length <= Editor.MAP_WIDTH)
        || (!palItem.isGroupedHorizontally() &&
            ty - obj.length >= -1))
    {
      level.addMultiTileObject(obj);
    }
    
    if (palItem.isGroupedHorizontally())
      clearInHorizontalDirectionForNTiles(palItem.getNumberOfTiles(),
          tx, ty);
    else
      clearInVerticalDirectionForNTiles(palItem.getNumberOfTiles(),
          tx, ty);
    

  }    
  
  private boolean willOverlapOtherObject(PaletteItem palItem)
  {    
    for (MultiTileObject obj: level.getMultiTileObjects())
    {      
      if (obj.wouldIntersect(Editor.getMouseTx(), Editor.getMouseTy(),
          palItem.isGroupedHorizontally()?palItem.getNumberOfTiles():1,
          palItem.isGroupedHorizontally()?1:palItem.getNumberOfTiles()))
        return true;
    }
    
    return false;
  }
  
  private void clearInHorizontalDirectionForNTiles(int n, int tx, 
      int ty)
  {
    int maxx = Math.min(tx + n, Editor.MAP_WIDTH);
    
    for (int j = tx; j < maxx; j++)
    {
      level.getTiles()[ty][j] = Tile.BLANK;
    }
  }
  
  private void clearInVerticalDirectionForNTiles(int n, int tx, 
      int ty)
  {
    int miny = Math.max(ty - n, 0);
    
    for (int i = ty; i > miny; i--)
    {
      level.getTiles()[i][tx] = Tile.BLANK;
    }
  }

  @Override
  public void render() {
    // TODO Auto-generated method stub
    
  }
  

}
