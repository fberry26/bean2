package komorebi.bean.editor.objects.platform;

import komorebi.bean.editor.objects.utils.Vector;

public enum PathDirection
{
  LEFT(new Vector(-1, 0)), 
  UP(new Vector(0, 1)), DOWN(new Vector(0, -1)), 
  RIGHT(new Vector(1, 0)), ANY(new Vector(0, 0));

  private static int OPPOSITES = 3;
  
  private Vector vector;
  
  private PathDirection(Vector vector)
  {
    this.vector = vector;
  }
  
  public Vector getVector()
  {
    return vector;
  }

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

    throw new RuntimeException("Pre-condition unmet: neither dx nor dy"
        + " is non-zero");
  }
  
  public PathDirection getOpposite()
  {
    if (this == ANY)
      throw new RuntimeException(this + " has no opposite");
    
    return values()[OPPOSITES-this.ordinal()];
  }

  public boolean isOpposite(PathDirection dir)
  {
    return this.ordinal() + dir.ordinal() == OPPOSITES;
  }


  public boolean matches(PathDirection dir)
  {
    return this == dir;
  }

  public boolean isVertical()
  {
    return this == UP || this == DOWN;
  }
}