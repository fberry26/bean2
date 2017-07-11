package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;

public abstract class MultiTileObject extends TileObject {
  
  public MultiTileObject(PaletteItem origin, ModRectangle area) {
    super(origin, area);
  }
  
  public int length;
  protected boolean horizontal;
  
  public boolean isHorizontal()
  {
    return horizontal;
  }

  public int getLength()
  {
    return length;
  }
  
  public int getRightmostTileX()
  {
    return horizontal?area.x+length-1:area.x; 
  }
  
  public boolean containsMouse()
  {
    return containsPoint(MouseHandler.getTx(), MouseHandler.getTy());
  }
  

}
