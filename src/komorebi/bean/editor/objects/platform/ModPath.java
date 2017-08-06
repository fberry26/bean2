package komorebi.bean.editor.objects.platform;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import komorebi.bean.editor.objects.utils.Corner;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.objects.utils.Shape;
import komorebi.bean.editor.objects.utils.Vector;
import komorebi.bean.editor.objects.utils.Corner.CornerLocation;

public class ModPath implements Shape {

  private ArrayList<ModPathRectangle> path;
  private ArrayList<PathDirection> movements;
  private Dimension originalSize;
  
  private static final Dimension SIZE = new Dimension(2, 1);

  public ModPath(ModPathRectangle originalLocation)
  {
    path = new ArrayList<ModPathRectangle>();
    movements = new ArrayList<PathDirection>();

    path.add(originalLocation);
    
    originalSize = new Dimension(originalLocation.width, 
        originalLocation.height);

  }

  public ModPath(Dimension originalSize, 
      ArrayList<ModPathRectangle> path, 
      ArrayList<PathDirection> movements)
  {
    this.originalSize = originalSize;
    this.path = duplicateArrayList(path);
    this.movements = movements;

  }

  private ArrayList<ModPathRectangle> duplicateArrayList(
      ArrayList<ModPathRectangle> original)
  {
    ArrayList<ModPathRectangle> duplicate = new ArrayList<ModPathRectangle>();

    for (ModRectangle rect: original)
    {
      duplicate.add((ModPathRectangle) rect.duplicate());
    }

    return duplicate;
  }
  
  public void addToPath(PathDirection isMovingIn, int dist,
      boolean beginning)
  {
    ModPathRectangle lastRect;
    PathDirection wasMovingIn;
    
    for (int i = 0; i < dist; i++)
    {
      lastRect = beginning?getFirstRectangle():getLastRectangle();
      wasMovingIn = lastRect.getDirection();
      
      if (wasMovingIn.matches(isMovingIn))
      {
        lastRect.extend();
        
        addTo(isMovingIn, beginning);
      } else if (wasMovingIn.isOpposite(isMovingIn)) 
      {         
        boolean keep = lastRect.contract();
        removeFrom(beginning);

        if (!keep)
        {
          delete(lastRect);
        }
      } else 
      { 
        
        int addAt = beginning?0:path.size();

        path.add(addAt, newRectangle2(wasMovingIn, isMovingIn, 
            lastRect));
        addTo(isMovingIn, beginning);
      }
    }
    
    for (PathDirection mvmt: movements)
    {
      System.out.print(mvmt + " ");
    }
    
    System.out.println("\n****");
  }
  
  private void addTo(PathDirection add, boolean beginning)
  {
    int addAt = beginning?0:movements.size();
    add = beginning?add.getOpposite():add;
    
    movements.add(addAt, add);
  }
  
  private void removeFrom(boolean beginning)
  {
    int removeAt = beginning?0:movements.size()-1;
    
    movements.remove(removeAt);
  }

  /**
   * Pre-condition: one (but not both) of dx and dy is non-zero
   * @param dx The distance between the addition and the last added point,
   *    in the x direction
   * @param dy The distance between the addition and the last added point,
   *    in the y direction
   */
  public void addToEnd(int dx, int dy)
  {
    PathDirection dir = PathDirection.getDirectionOf(dx, dy);
    int dist = dir.isVertical()?Math.abs(dy):Math.abs(dx);
    
    addToPath(dir, dist, false);
  }

  public void addToBeginning(int dx, int dy)
  {
    PathDirection dir = PathDirection.getDirectionOf(dx, dy);
    int dist = dir.isVertical()?Math.abs(dy):Math.abs(dx);
    
    addToPath(dir, dist, true);
  }

  public ModPathRectangle getAdditionToPath(int dx, int dy, boolean beginning)
  {
    ModPathRectangle current = getCurrentRectangle(beginning);

    PathDirection isMovingIn = PathDirection.getDirectionOf(dx, dy);
    PathDirection wasMovingIn = current.getDirection();

    ModPathRectangle added = newRectangle(wasMovingIn, isMovingIn, 
        dx, dy, current);

    return added;
  }

  public boolean doesMotionContradictArrowDirection(int dx, int dy, boolean beginning)
  {
    ModPathRectangle current = getCurrentRectangle(beginning);

    PathDirection isMovingIn = PathDirection.getDirectionOf(dx, dy);
    PathDirection wasMovingIn = current.getDirection();

    PathDirection checkAgainst;
    
    if (movements.size() == 0)
    {
      checkAgainst = PathDirection.ANY;
    } else if (beginning)
    {
      checkAgainst = movements.get(0).getOpposite();
    } else
    {
      checkAgainst = movements.get(movements.size() - 1);
    }
        
    return wasMovingIn == PathDirection.ANY && 
        ((isMovingIn == PathDirection.LEFT && !beginning)
            || (isMovingIn == PathDirection.RIGHT && beginning)
        || (isMovingIn.isOpposite(checkAgainst)));
  }

  private ModPathRectangle getCurrentRectangle(boolean beginning)
  {
    if (beginning)
    {
      return getFirstRectangle();
    } else
    {
      return getLastRectangle();
    }
  }

  private ModPathRectangle getFirstRectangle()
  {
    return path.get(0);
  }
  
  private ModPathRectangle newRectangle2(PathDirection oldDir, PathDirection
      newDir, ModPathRectangle origin)
  {
    Vector vector = newDir.getVector();
    
    CornerLocation cornerLoc = CornerLocation.cornerOf(oldDir, newDir);
    Corner corner = origin.getCorner(cornerLoc);
    Corner newCorner = corner.translateAsNew(vector.getDx(), vector.getDy());
    
    return ModPathRectangle.fromCorner(newCorner, SIZE.width, 
        SIZE.height, newDir);
  }

  private ModPathRectangle newRectangle(PathDirection oldDir, PathDirection newDir,
      int dx, int dy, ModPathRectangle origin)
  { 
    CornerLocation cornerLoc = CornerLocation.cornerOf(oldDir, newDir);
    Corner corner = origin.getCorner(cornerLoc);
    Corner newCorner = corner.translateAsNew(dx, dy);
    
    int width = Math.max(Math.abs(dx), originalSize.width);
    int height = Math.max(Math.abs(dy), originalSize.height);

    return ModPathRectangle.fromCorner(newCorner, width, 
        height, PathDirection.getDirectionOf(dx, dy));

  }

  public String toString()
  {
    String ret = "";

    for (ModRectangle rect: path)
    {
      ret += rect.toString() + "\n";
    }

    return ret;
  }

  private ModPathRectangle getLastRectangle()
  {
    return path.get(path.size() - 1);
  }

  private void delete(ModPathRectangle r)
  {
    path.remove(r);
  }


  @Override
  public void setLocation(int x, int y) {
    changeLocation(x - path.get(0).x, y - path.get(0).y);
  }

  @Override
  public void changeLocation(int dx, int dy) {
    for (ModRectangle rect: path)
    {
      rect.changeLocation(dx, dy);
    }
  }

  @Override
  public Rectangle[] getComponentRectangles() {
    return path.toArray(new ModRectangle[path.size()]);
  }

  @Override
  public boolean intersects(Shape shape) {
    for (Rectangle r1: shape.getComponentRectangles())
    {
      for (Rectangle r2: path)
      {
        if (r1.intersects(r2))
          return true;
      }
    }

    return false;
  }

  @Override
  public boolean contains(Point point) {
    for (ModRectangle rect: path)
    {
      if (rect.contains(point))
        return true;
    }

    return false;
  }

  public int x()
  {
    return path.get(0).x;
  }

  public int y()
  {
    return path.get(0).y;
  }

  @Override
  public Shape duplicate() {
    return new ModPath(originalSize, path, movements);  
  }
  
  public ArrayList<PathDirection> getDirections()
  {
    return movements;
  }
  
  public PathDirection getFirstDirection()
  {
    if (movements.size() == 0)
      return PathDirection.RIGHT;
    
    return movements.get(0);
  }
  
  public PathDirection getLastDirection()
  {
    if (movements.size() == 0)
      return PathDirection.RIGHT;
    
    return movements.get(movements.size()-1);
  }
}
