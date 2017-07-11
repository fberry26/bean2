package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.graphics.SelectionAnimation;

public class RectangularSelection implements Draggable, Selection {

  private Rectangle area;
  private SelectionAnimation selection;
  
  private ArrayList<TileObject> lockedObjects;

  public RectangularSelection()
  {
    this(0, 0, 0, 0);
  }

  public RectangularSelection(int minx, int miny, int maxx, int maxy)
  {
    area = new Rectangle(minx, miny, maxx-minx+1, maxy-miny+1);
    selection = new SelectionAnimation();

  }

  public void setBounds(int minx, int miny, int maxx, int maxy)
  {
    area.setBounds(minx, miny, maxx-minx+1, maxy-miny+1);
    selection.setBounds(minx, miny, maxx, maxy);
  }

  public void render()
  {
    selection.render();
  }

  public boolean contains(Rectangle rect)
  {    
    return area.contains(rect);
  }

  public boolean contains(Point point)
  {
    return area.contains(point);
  }

  public boolean intersects(Rectangle rect)
  {
    return area.intersects(rect);
  }

  public void moveBy(int dx, int dy)
  {
    ArrayList<TileObject> objsWithin;
    
    if (lockedObjects == null)
    {
      objsWithin = Editor.level().allObjectsWithin(this);
    } else
    {
      objsWithin = lockedObjects;
    }

    for (TileObject obj: objsWithin)
      obj.moveBy(dx, dy);
    
    area.translate(dx, dy);
    selection.move(dx, dy);
  }

  @Override
  public boolean canBeMovedBy(int dx, int dy) {
    Rectangle newLoc = AreaUtilities.translate(area, dx, dy);
    
    return Editor.MAP.contains(newLoc);

  }
  
  public void lockObjects()
  {
    lockedObjects = Editor.level().allObjectsWithin(this);
  }
  
  public void unlockObjects()
  {
    lockedObjects = null;
  }
  
  public void eraseUnder()
  {    
    for (TileObject obj: Editor.level().allObjectsWithinExcluding(this, 
        lockedObjects))
    {
      Editor.level().removeObject(obj);
    }
  }
}
