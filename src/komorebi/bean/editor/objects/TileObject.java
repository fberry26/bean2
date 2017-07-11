package komorebi.bean.editor.objects;

import java.awt.Point;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.Draggable;
import komorebi.bean.engine.MouseHandler;

public abstract class TileObject implements Draggable {

  private PaletteItem origin;
  protected ModRectangle area;
  
  public TileObject(PaletteItem origin, ModRectangle area)
  {
    this.origin = origin;
    this.area = area;
  }
  
  public abstract void render();

  public abstract TileObject build(PaletteItem origin, ModRectangle area);
  
  public boolean wouldIntersect(ModRectangle rect) {

    return area.intersects(rect);
  }
  
  public boolean containsPoint(Point point)
  {
    return area.contains(point);
  }
  
  public boolean containsPoint(int x, int y)
  {
    return containsPoint(new Point(x, y));
  }
  
  public void moveBy(int dx, int dy)
  {
    area.changeLocation(dx, dy);
  }
  
  public boolean canBeMovedBy(int dx, int dy)
  {
    ModRectangle newLocation = AreaUtilities.translate(area, 
        dx, dy);
    
    return willPlacementOfItemBeValid(newLocation);
  }
  
  private boolean willPlacementOfItemBeValid(ModRectangle locationOfItem)
  { 
    return //Editor.MAP.contains(locationOfItem) && 
        !Editor.level().willOverlapOtherObjectExcludeSelf(
            locationOfItem, this);
  }
  
  public void moveTo(int tx, int ty)
  {
    area.setLocation(tx, ty);
  }
  
  public int getTx()
  {
    return area.x;
  }
  
  public int getTy()
  {
    return area.y;
  }
  
  protected static int modX(int num)
  {
    return mod(num, Editor.TILE_MODSPACE.width);
  }
  
  protected static int modY(int num)
  {
    return mod(num, Editor.TILE_MODSPACE.height);
  }
  
  private static int mod(int num, int mod)
  {
    while (num < 0)
      num += mod;
    
    while (num >= mod)
      num -= mod;
    
    return num;
  }
  
  public boolean isBeingHoveredOver()
  {
    return containsPoint(MouseHandler.getTx(), MouseHandler.getTy());
  }
  
  public PaletteItem getPaletteItem()
  {
    return origin;
  }
  
  public ModRectangle getArea()
  {
    return area;
  }
}
