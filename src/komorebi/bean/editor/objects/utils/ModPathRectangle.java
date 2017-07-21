package komorebi.bean.editor.objects.utils;

import java.awt.Dimension;

import komorebi.bean.editor.objects.utils.Corner.CornerLocation;
import komorebi.bean.editor.objects.utils.ModPath.PathDirection;

public class ModPathRectangle extends ModRectangle {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int NUM_CORNERS = 4;
  
  private Corner[] corners;
  private PathDirection direction;

  public ModPathRectangle(int x, int y, int width, int height) {
    super(x, y, width, height);
    
    corners = determineCorners();
    this.direction = PathDirection.ANY;
  }
  
  public ModPathRectangle(int x, int y, int width, int height,
      Dimension modSpace)
  {
    super(x, y, width, height, modSpace);
    
    corners = determineCorners();
    this.direction = PathDirection.ANY;
  }
  
  private Corner[] determineCorners()
  {
    Corner[] corners = new Corner[NUM_CORNERS];
    
    corners[0] = new Corner(x, y, CornerLocation.BOTTOM_LEFT);
    corners[1] = new Corner(x+width, y, CornerLocation.BOTTOM_RIGHT);
    corners[2] = new Corner(x, y+height, CornerLocation.TOP_LEFT);
    corners[3] = new Corner(x+width, y+height, CornerLocation.TOP_RIGHT);
    
    for (Corner c: corners)
    {
      System.out.println(c.x + " " + c.y + " " + c.getCorner());
    }
    
    return corners;
  }
  
  public Corner getCorner(CornerLocation loc)
  {
    for (Corner corner: corners)
    {
      if (corner.getCorner() == loc)
      {
        return corner;
      }
    }
    
    throw new RuntimeException("No corner with corner location " + loc);
  }
  
  public void extend(PathDirection dir, int dx, int dy)
  {
    switch (dir)
    {
      case DOWN:
        extendDown(-dy);
        break;
      case LEFT:
        extendLeft(-dx);
        break;
      case RIGHT:
        extendRight(dx);
        break;
      case UP:
        extendUp(dy);
        break;
      default:
        break;
      
    }
   
  }
  
  public void setDirection(PathDirection direction)
  {
    if (this.direction != PathDirection.ANY)
      throw new RuntimeException("Pre-condition unmet: path direction "
          + "has already been set to " + direction);
    
    this.direction = direction;
  }
  
  public PathDirection getDirection()
  {
    return direction;
  }
  
  private void extendLeft(int amount)
  {
    this.changeSize(amount, 0);
    this.changeLocation(-amount, 0);
  }
  
  private void extendRight(int amount)
  {
    this.changeSize(amount, 0);
  }
  
  private void extendUp(int amount)
  {
    this.changeSize(0, amount);
  }
  
  private void extendDown(int amount)
  {
    this.changeSize(0, amount);
    this.changeLocation(0, -amount);
  }
  
  public boolean contract(PathDirection dir, int dx, int dy)
  {
    switch (dir)
    {
      case DOWN:
        return contractDown(-dy);
      case UP:
        return contractUp(dy);
      case LEFT:
        return contractLeft(-dx);
      case RIGHT:
        return contractRight(dx);
      default:
        break;
    }
    
    return true;
  }
  
  private boolean contractDown(int amount)
  {
    if (amount >= height)
    {
      return false;
    }
    
    this.changeSize(0, -amount);
    
    return true;
  }
  
  private boolean contractUp(int amount)
  {
    if (amount >= height)
    {
      return false;
    }
    
    this.changeSize(0, -amount);
    this.changeLocation(0, amount);
    
    return true;
  }
  
  private boolean contractLeft(int amount)
  {
    if (amount >= width)
    {
      return false;
    }
    
    this.changeSize(-amount, 0);
    
    return true;
  }
  
  private boolean contractRight(int amount)
  {
    if (amount >= width)
    {
      return false;
    }
    
    this.changeSize(-amount, 0);
    this.changeLocation(amount, 0);
    
    return true;
  }
  
  @Override
  public void changeLocation(int dx, int dy)
  {
    super.changeLocation(dx, dy);
    
    for (Corner c: corners)
    {
      c.translate(dx, dy);
    }
  }
  
  @Override
  public void changeSize(int dWidth, int dHeight)
  {
    super.changeSize(dWidth, dHeight);
    
    for (Corner c: corners)
    {
      CornerLocation location = c.getCorner();
      
      if (location.getHorizontal() == PathDirection.RIGHT)
      {
        c.translate(dWidth, 0);
      }
      
      if (location.getVertical() == PathDirection.UP)
      {
        c.translate(0, dHeight);
      }
    }
  }
  
  public static ModPathRectangle convertToPathRectangle(ModRectangle convert)
  {
    return new ModPathRectangle(convert.x,
        convert.y, convert.width, convert.height, convert.modSpace);
  }
  
  public static ModPathRectangle fromCorner(Corner corner, int width, int height)
  {
    int x = corner.x, y = corner.y;
    CornerLocation loc = corner.getCorner();
    
    if (loc.getHorizontal() == PathDirection.RIGHT)
    {
      x -= width;
    }
    
    if (loc.getVertical() == PathDirection.UP)
    {
      y -= height;
    }
    
    return new ModPathRectangle(x, y, width, height);
    
  }
  
  public Shape duplicate()
  {
    return new ModPathRectangle(x, y, width, height, modSpace);
  }
}
