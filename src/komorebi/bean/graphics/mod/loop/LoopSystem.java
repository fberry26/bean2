package komorebi.bean.graphics.mod.loop;

import java.awt.Rectangle;

import komorebi.bean.graphics.Image;

public class LoopSystem {

  public int minX, minY, maxX, maxY,
    fromX, fromY, toX, toY;
  public Rectangle[] splitBy;
  private Image[] partialImages;
  private int currRect;
  private int texture;
      
  public LoopSystem(Image image, Rectangle[] splitBy,
      LoopDirection innerDir, LoopDirection outerDir)
  {
    this.splitBy = splitBy;
    partialImages = new Image[splitBy.length];
    
    minX = image.getTexX();
    minY = image.getTexY();
    maxX = minX + image.getWidth();
    maxY = minY + image.getHeight();
    
    texture = image.getTexture();
    
    InnerLoop innerLoop = new InnerLoop(this, innerDir);
    OuterLoop outerLoop = new OuterLoop(this, outerDir,
        innerLoop);
    
    outerLoop.execute();
  }
  
  public void assign()
  {
    partialImages[currRect] = new Image(fromX, fromY,
        toX - fromX, toY - fromY, texture);
    
    currRect++;
  }
  
  public Rectangle currentRectangle()
  {
    return splitBy[currRect];
  }
  
  public Image[] getImages()
  {
    return partialImages;
  }
}
