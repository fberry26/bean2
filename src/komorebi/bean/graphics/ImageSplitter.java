package komorebi.bean.graphics;

import java.awt.Rectangle;

public class ImageSplitter {

  private Image[] partialImages;

  public ImageSplitter(Image image, Rectangle[] splitBy, int rot,
      boolean flip)
  {
    partialImages = new Image[splitBy.length];
    
    if (rot == 0)
    {
      noRotation(image, splitBy);
    }
  }

  private void noRotation(Image image, Rectangle[] splitBy)
  {    
    int maxTexX = image.getTexX() + image.getWidth();
    int maxTexY = image.getTexY() + image.getHeight();

    int fromX = image.getTexX(),
        fromY = maxTexY - splitBy[0].height;

    int toX = fromX + splitBy[0].width,
        toY = maxTexY;
    
    int currRect = 0;
    
    while (toY > image.getTexY())
    {             
      fromY = toY - splitBy[currRect].height;
      fromX = image.getTexX();
      
      while (fromX < maxTexX)
      {
        toX = fromX + splitBy[currRect].width;
                
        partialImages[currRect] = new Image(fromX, fromY,
            toX - fromX, toY - fromY, image.getTexture());
        
        fromX = toX;
        currRect++;
        
      }
      
      toY = fromY;

    }
  }
 
  public Image[] getImages()
  {
    return partialImages;
  }
}
