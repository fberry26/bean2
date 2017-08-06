package komorebi.bean.editor.objects.utils;

import java.awt.Point;

import komorebi.bean.editor.objects.platform.PathDirection;

public class Corner extends Point {

  public static enum CornerLocation
  {
    BOTTOM_LEFT(PathDirection.DOWN, PathDirection.LEFT), 
    BOTTOM_RIGHT(PathDirection.DOWN, PathDirection.RIGHT), 
    TOP_LEFT(PathDirection.UP, PathDirection.LEFT), 
    TOP_RIGHT(PathDirection.UP, PathDirection.RIGHT);
    
    private PathDirection vert, horiz;
    
    private CornerLocation(PathDirection vert, PathDirection horiz)
    {
      this.vert = vert;
      this.horiz = horiz;
    }
    
    public PathDirection getVertical()
    {
      return vert;
    }
    
    public PathDirection getHorizontal()
    {
      return horiz;
    }
    
    public static CornerLocation cornerOf(PathDirection dir1, PathDirection dir2)
    {
      PathDirection vert, horiz;
     
      if (dir1 == dir2 || dir1.isOpposite(dir2))
      {
        if (dir1.isVertical())
        {
          dir2 = PathDirection.LEFT;
        } else 
        {
          dir1 = PathDirection.DOWN;
        }
      }
      
      if (dir1 == PathDirection.ANY)
      {
        if (dir2.isVertical())
        {
          dir1 = PathDirection.LEFT;
        } else
        {
          dir1 = PathDirection.DOWN;
        }
      }
      
      if (dir1.isVertical())
      {
        vert = dir1;
        horiz = dir2;
      } else
      {
        vert = dir2;
        horiz = dir1;
      }
      
      for (CornerLocation corner: values())
      {
        if (corner.getVertical() == vert && 
            corner.getHorizontal() == horiz)
        {
          return corner;
        }
      }
      
      throw new RuntimeException("Pre-condition not met: " + dir1 + " and " +
       dir2 + " occur along the same axis");
    }
  }
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private CornerLocation corner;
  
  public Corner(int x, int y, CornerLocation corner)
  {
    super(x, y);
    
    this.corner = corner;
  }
  
  public Corner translateAsNew(int dx, int dy)
  {
    return new Corner(x + dx, y + dy, corner);
  }
  
  public CornerLocation getCorner()
  {
    return corner;
  }

}
