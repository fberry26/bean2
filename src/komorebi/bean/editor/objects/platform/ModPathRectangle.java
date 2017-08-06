package komorebi.bean.editor.objects.platform;

import java.awt.Dimension;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.utils.Corner;
import komorebi.bean.editor.objects.utils.Corner.CornerLocation;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.objects.utils.Shape;

public class ModPathRectangle extends ModRectangle {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int NUM_CORNERS = 4;
  
  private Corner[] corners;
  private PathDirection direction;
  
  private static int minWidth = 2, minHeight = 1;

  public ModPathRectangle(int x, int y, int width, int height, 
      PathDirection direction) {
    this(x, y, width, height, Editor.TILE_MODSPACE, direction);
    
  }
  
  public ModPathRectangle(int x, int y, int width, int height,
      Dimension modSpace, PathDirection direction)
  {
    super(x, y, width, height, modSpace);
    
    corners = determineCorners();
    this.direction = direction;
  }
  
  private Corner[] determineCorners()
  {
    Corner[] corners = new Corner[NUM_CORNERS];
    
    corners[0] = new Corner(x, y, CornerLocation.BOTTOM_LEFT);
    corners[1] = new Corner(x+width, y, CornerLocation.BOTTOM_RIGHT);
    corners[2] = new Corner(x, y+height, CornerLocation.TOP_LEFT);
    corners[3] = new Corner(x+width, y+height, CornerLocation.TOP_RIGHT);
    
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
  
  public void extend()
  {
    switch (direction)
    {
      case DOWN:
        extendDown(1);
        break;
      case LEFT:
        extendLeft(1);
        break;
      case RIGHT:
        extendRight(1);
        break;
      case UP:
        extendUp(1);
        break;
      default:
        break;
      
    }
   
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
  
  public boolean contract()
  {    
    switch (direction.getOpposite())
    {
      case DOWN:
        return contractDown(1);
      case UP:
        return contractUp(1);
      case LEFT:
        return contractLeft(1);
      case RIGHT:
        return contractRight(1);
      default:
        break;
    }
    
    return true;
  }
  
  private boolean contractDown(int amount)
  {
    if (height - amount < minHeight)
    {
      return false;
    }
    
    this.changeSize(0, -amount);
    
    return true;
  }
  
  private boolean contractUp(int amount)
  {
    if (height - amount < minHeight)
    {
      return false;
    }
    
    this.changeSize(0, -amount);
    this.changeLocation(0, amount);
    
    return true;
  }
  
  private boolean contractLeft(int amount)
  {
    if (width - amount < minWidth)
    {
      return false;
    }
    
    this.changeSize(-amount, 0);
    
    return true;
  }
  
  private boolean contractRight(int amount)
  {
    if (width - amount < minWidth)
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
  
  public void setDirection(PathDirection dir)
  {
    if (this.direction != PathDirection.ANY)
      throw new RuntimeException("Pre-condition unmet: direction already"
          + " set to " + direction);
    
    this.direction = dir;
  }
  
  public static ModPathRectangle convertToPathRectangle(ModRectangle convert)
  {
    return new ModPathRectangle(convert.x,
        convert.y, convert.width, convert.height, convert.modSpace,
        PathDirection.ANY);
  }
  
  public static ModPathRectangle fromCorner(Corner corner, 
      int width, int height, PathDirection dir)
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
    
    return new ModPathRectangle(x, y, width, height, dir);
    
  }
  
  public Shape duplicate()
  {
    return new ModPathRectangle(x, y, width, height, modSpace, 
        direction);
  }
  
  public boolean equals(Object obj)
  {
    return this == obj;
  }
}
