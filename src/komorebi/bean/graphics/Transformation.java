package komorebi.bean.graphics;

public enum Transformation {
  ROTATE_COUNTERCLOCKWISE(1), ROTATE_180(2), ROTATE_CLOCKWISE(3),
  REFLECT_ACROSS_Y(0), REFLECT_ACROSS_X(0);
  
  private int angle;
  
  private Transformation(int angle)
  {
    this.angle = angle;
  }
  
  public int getAngle()
  {
    return angle;
  }
  
  public static TransformationSeries consolidate(Transformation...transformations)
  {
    return new TransformationSeries(transformations);
  }
  
  public static class TransformationSeries {

    private int angle;
    private boolean flipX, flipY;
    
    private TransformationSeries(Transformation...transformations)
    {      
      for (Transformation transformation: transformations)
      {
        angle += transformation.getAngle();
        
        if (transformation == Transformation.REFLECT_ACROSS_Y)
        {
          if (angle % 2 == 0)
          {
            flipX = !flipX;
          } else
          {
            flipY = !flipY;

          }
        } else if (transformation == Transformation.REFLECT_ACROSS_X)
        {
          if (angle % 2 == 0)
          {
            flipY = !flipY;
          } else
          {
            flipX = !flipX;
          }
        }
      }

      angle %= 4;
    }
    
    public int getAngle()
    {
      return angle;
    }
    
    public boolean flipX()
    {
      return flipX;
    }
    
    public boolean flipY()
    {
      return flipY;
    }

  }
}
