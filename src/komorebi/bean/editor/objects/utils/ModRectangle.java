package komorebi.bean.editor.objects.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import komorebi.bean.editor.Editor;

public class ModRectangle extends Rectangle implements Shape {

  private static final long serialVersionUID = 1L;
  public Dimension modSpace;
  
  private Rectangle[] components;
   
  public ModRectangle(int x, int y, int width, int height, Dimension
      modSpace)
  {
    super(x % modSpace.width, y % modSpace.height, width, height);
    this.modSpace = modSpace;
    
    components = modSplit();
  }
  
  public ModRectangle(int x, int y, int width, int height)
  {
    this(x, y, width, height, Editor.TILE_MODSPACE);
  }
  
  public void setLocation(int x, int y)
  {
    this.x = mod(x, modSpace.width);
    this.y = mod(y, modSpace.height);
    
    components = modSplit();
  }
  
  public void changeLocation(int dx, int dy)
  {
    setLocation(x + dx, y + dy);
  }
  
  public void setSize(int width, int height)
  {
    this.width = width;
    this.height = height;
    
    components = modSplit();
  }
  
  public void changeSize(int dWidth, int dHeight)
  {
    setSize(width + dWidth, height + dHeight);
  }
  
  private Rectangle[] modSplit()
  {
    int x1 = mod(this.x, modSpace.width);
    int x2 = mod((this.x + this.width), modSpace.width);
    
    int y1 = mod(this.y, modSpace.height);
    int y2 = mod((this.y + this.height), modSpace.height);
    
    int numRects = 1;
    
    if (x2 <= x1 && x2 != 0)
      numRects *= 2;
    if (y2 <= y1 && y2 != 0)
      numRects *= 2;

    Rectangle[] rects = new Rectangle[numRects];
    
    int currentRect = 0;
    
    rects[currentRect] = rectWithCorners(new Point(x1, y1),
          new Point(Math.min(x1 + this.width, modSpace.width),
              Math.min(y1 + this.height, modSpace.height)));
        
    if (x2 <= x1 && x2 != 0)
    {
      currentRect++;
      
      rects[currentRect] = rectWithCorners(new Point(0, y1),
          new Point(x2, Math.min(this.y + this.height, modSpace.height)));
            
      if (y2 <= y1 && y2 != 0)
      {
        
        rects[3] = rectWithCorners(new Point(0, 0),
            new Point(x2, y2));
        
      }
    }
    
    if (y2 <= y1 && y2 != 0)
    {
      currentRect++;
      
      rects[currentRect] = rectWithCorners(new Point(x1, 0),
          new Point(Math.min(this.x + this.width, modSpace.width),
              y2));
    }
    
    return rects;
    
  }
  
  public Rectangle[] getComponentRectangles()
  {
    return components;
  }
  
  public boolean intersects(Shape shape)
  {
    for (Rectangle r1: shape.getComponentRectangles())
    {
      for (Rectangle r2: components)
      {
        if (r1.intersects(r2))
          return true;
      }
    }
    
    return false;
  }
  
  public boolean contains(Point point)
  {
    for (Rectangle r: components)
    {
      if (r.contains(point))
        return true;
    }
    
    return false;
  }
  
  public Point getTopRightPoint()
  {
    return new Point((x+width)%modSpace.width,
        (y+height)%modSpace.height);
  }
  
  public boolean intersects(Rectangle rect)
  {
    for (Rectangle r: components)
    {
      if (r.intersects(rect))
        return true;
    }
    
    return false;
  }
  
  public String toString()
  {
    String ret = "";
    
    for (Rectangle r: getComponentRectangles())
    {
      ret += r.toString() + "\n";
    }
    
    return ret;
  }
  
  private static Rectangle rectWithCorners(Point bottomLeft, Point topRight)
  {
    int width = topRight.x - bottomLeft.x;
    int height = topRight.y - bottomLeft.y;
    return new Rectangle(bottomLeft.x, bottomLeft.y, width, height);
  }
  
  private static int mod(int num, int mod)
  {
    while (num < 0)
      num += mod;
    
    while (num >= mod)
      num -= mod;
    
    return num;
  }

  @Override
  public Shape duplicate() {
    return new ModRectangle(this.x, this.y, this.width, this.height,
        this.modSpace);
  }
  
  public int x()
  {
    return x;
  }
  
  public int y()
  {
    return y;
  }
  

  
}
