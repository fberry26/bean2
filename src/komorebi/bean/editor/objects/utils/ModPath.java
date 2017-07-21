package komorebi.bean.editor.objects.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import komorebi.bean.editor.objects.utils.Corner.CornerLocation;

public class ModPath implements Shape {

  private ArrayList<ModPathRectangle> path;
  private Dimension originalSize;
  
  public static enum PathDirection
  {
    LEFT, UP, DOWN, RIGHT, ANY;
    
    private static int OPPOSITES = 3;
    
    /**
     * Pre-condition: one and only one of dx and dy is non-zero
     * @param dx Movement in the x direction
     * @param dy Movement in the y direction
     * @return The enum value representing the movement
     */
    public static PathDirection getDirectionOf(int dx, int dy)
    {
      if (dx > 0)
        return RIGHT;
      
      if (dx < 0)
        return LEFT;
      
      if (dy > 0)
        return UP;
      
      if (dy < 0)
        return DOWN;
      
      throw new RuntimeException("Pre-condition unmet: neither dx or dy"
          + " is non-zero");
    }
    
    public boolean isOpposite(PathDirection dir)
    {
      return this.ordinal() + dir.ordinal() == OPPOSITES;
    }
    
    
    public boolean matches(PathDirection dir)
    {
      return this == ANY || this == dir;
    }
    
    public boolean isVertical()
    {
      return this == UP || this == DOWN;
    }
  }
  
  public ModPath(ModPathRectangle originalLocation)
  {
    path = new ArrayList<ModPathRectangle>();
    path.add(originalLocation);
    
    originalSize = new Dimension(originalLocation.width, 
        originalLocation.height);
    
  }
  
  public ModPath(Dimension originalSize, ArrayList<ModPathRectangle> path)
  {
    this.originalSize = originalSize;
    this.path = duplicateArrayList(path);
        
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
  
  /**
   * Pre-condition: one (but not both) of dx and dy is non-zero
   * @param dx The distance between the addition and the last added point,
   *    in the x direction
   * @param dy The distance between the addition and the last added point,
   *    in the y direction
   */
  public void addToPath(int dx, int dy)
  {
    PathDirection isMovingIn = PathDirection.getDirectionOf(dx, dy);
    PathDirection wasMovingIn = getLastRectangle().getDirection();
    if (wasMovingIn.matches(isMovingIn))
    {
      getLastRectangle().extend(isMovingIn, dx, dy);
      
      if (wasMovingIn == PathDirection.ANY)
      {
        getLastRectangle().setDirection(isMovingIn);
      }
    } else if (wasMovingIn.isOpposite(isMovingIn)) 
    { 
      boolean keep = getLastRectangle().contract(isMovingIn, dx, dy);
      
      if (!keep)
      {
        deleteLastRectangle();
      }
    } else 
    {
      path.add(newRectangle(wasMovingIn, isMovingIn, dx, dy));
    }
  }
  
  private ModPathRectangle newRectangle(PathDirection oldDir, PathDirection newDir,
      int dx, int dy)
  {
    CornerLocation cornerLoc = CornerLocation.cornerOf(oldDir, newDir);
    Corner corner = getLastRectangle().getCorner(cornerLoc);
    
    System.out.println(corner.x + " " + corner.y);
    
    Corner newCorner = corner.translateAsNew(dx, dy);
    
    return ModPathRectangle.fromCorner(newCorner, originalSize.width, 
        originalSize.height);
   
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
  
  private void deleteLastRectangle()
  {
    path.remove(path.size() - 1);
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
    return new ModPath(originalSize, path);  
  }
}
