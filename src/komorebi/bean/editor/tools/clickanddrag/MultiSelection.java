package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MultiSelection implements Draggable, Selection {

  private ArrayList<RectangularSelection> rectangles;
  
  public MultiSelection()
  {
    rectangles = new ArrayList<RectangularSelection>();
  }
  
  public void addSelection(RectangularSelection rect)
  {
    rectangles.add(rect);
  }
  
  public void clear()
  {
    rectangles.clear();
  }
  
  public void render()
  {
    for (RectangularSelection rect: rectangles)
    {
      rect.render();
    }
  }
  
  public boolean isEmpty()
  {
    return rectangles.isEmpty();
  }
  
  public boolean contains(Rectangle item)
  {
    for (RectangularSelection rect: rectangles)
    {
      if (rect.contains(item))
        return true;
    }
    
    return false;
  }
  
  public boolean intersects(Rectangle item)
  {
    for (RectangularSelection rect: rectangles)
    {
      if (rect.intersects(item))
        return true;
    }
    
    return false;
  }
  
  public boolean contains(Point point)
  {
    for (RectangularSelection rect: rectangles)
    {
      if (rect.contains(point))
        return true;
    }
    
    return false;
  }

  @Override
  public void moveBy(int dx, int dy) {
    for (RectangularSelection rect: rectangles)
    {
      rect.moveBy(dx, dy);
    }
    
  }

  @Override
  public boolean canBeMovedBy(int dx, int dy) {
    for (RectangularSelection rect: rectangles)
    {
      if (!rect.canBeMovedBy(dx, dy))
        return false;
    }
    
    return true;
  }
  
  public void lockObjects()
  {
    for (RectangularSelection rect: rectangles)
    {
      rect.lockObjects();
    }
  }
  
  public void unlockObjects()
  {
    eraseUnder();
    
    for (RectangularSelection rect: rectangles)
    {
      rect.unlockObjects();
    }
  }
  
  private void eraseUnder()
  {
    for (RectangularSelection rect: rectangles)
    {
      rect.eraseUnder();
    }
  }
}
