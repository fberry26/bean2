package komorebi.bean.editor;

import java.util.ArrayList;

import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;

public class EditorLevel {

  private Tile[][] tiles;
  private ArrayList<MultiTileObject> multiTileObjects;
  
  
  public EditorLevel()
  {
    tiles = new Tile[Editor.MAP_HT][Editor.MAP_WIDTH];
    multiTileObjects = new ArrayList<MultiTileObject>();
    
    for (int i = 0; i < tiles.length; i++)
    {
      for (int j = 0; j <tiles[i].length; j++)
      {
        tiles[i][j] = Tile.BLANK;
      }
    }
  }
  
  public void render()
  {
    for (int i = 0; i < tiles.length; i++)
    {
      for (int j = 0; j < tiles[i].length; j++)
      {
        if (tiles[i][j] != Tile.BLANK)
          Draw.draw(tiles[i][j].getImage(), j*16, i*16);
      }
    }
    
    for (MultiTileObject obj: multiTileObjects)
    {
      obj.render();
    }
  }
  
  public Tile[][] getTiles()
  {
    return tiles;
  }
  
  public void addMultiTileObject(MultiTileObject obj)
  {
    multiTileObjects.add(obj);
  }
  
  public ArrayList<MultiTileObject> getMultiTileObjects()
  {
    return multiTileObjects;
  }
}
