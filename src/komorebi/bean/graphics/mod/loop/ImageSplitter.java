package komorebi.bean.graphics.mod.loop;

import java.awt.Dimension;
import java.awt.Rectangle;

import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.graphics.Image;
import komorebi.bean.graphics.Transformation;
import komorebi.bean.graphics.Transformation.TransformationSeries;

public class ImageSplitter {

  private Image[] images;
  private Rectangle[] rectangles;
  
  public ImageSplitter(Image image, float x, float y,
      Dimension modSpace,
      Transformation... transformations)
  {
    ModRectangle rect;
    
    TransformationSeries series = 
        Transformation.consolidate(transformations);
    LoopDirectionInterpreter interpreter =
        new LoopDirectionInterpreter(series);
    
    if (series.getAngle() % 2 == 0)
    {
      rect = new ModRectangle((int) x, (int) y, 
          (int) (image.getWidth()), (int) 
          (image.getHeight()), modSpace);
    } else
    {
      rect = new ModRectangle((int) x, (int) y, 
          (int) (image.getHeight()), (int) 
          (image.getWidth()), modSpace);
    }
    
    LoopSystem loopSystem = new LoopSystem(image,
        rect.getComponentRectangles(),
        interpreter.getInner(), interpreter.getOuter());
    
    images = loopSystem.getImages();
    rectangles = rect.getComponentRectangles();
  }
  
  public Image[] getImages()
  {
    return images;
  }
  
  public Rectangle[] getRectangles()
  {
    return rectangles;
  }
  
  private static class LoopDirectionInterpreter
  {
    private LoopDirection outer;
    private LoopDirection inner;
    
    public LoopDirectionInterpreter(TransformationSeries
        series)
    {
      switch (series.getAngle())
      {
        case 0:
          outer = LoopDirection.Y_BACKWARD;
          inner = LoopDirection.X_FORWARD;
          break;
        case 1:
          outer = LoopDirection.X_FORWARD;
          inner = LoopDirection.Y_FORWARD;
          break;
        case 2:
          outer = LoopDirection.Y_FORWARD;
          inner = LoopDirection.X_BACKWARD;
          break;
        case 3:
          outer = LoopDirection.X_BACKWARD;
          inner = LoopDirection.Y_BACKWARD;
          break;
      }
      
      if (series.flipX())
      {
        if (outer.isAlongXAxis())
          outer = outer.reverse();
        else
          inner = inner.reverse();
      }
      
      if (series.flipY())
      {
        if (!outer.isAlongXAxis())
          outer = outer.reverse();
        else
          inner = inner.reverse();
      }
    }
    
    public LoopDirection getInner()
    {
      return inner;
    }
    
    public LoopDirection getOuter()
    {
      return outer;
    }
  }
}
