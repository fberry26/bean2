package komorebi.bean.editor.objects.utils;

import java.awt.Point;
import java.awt.Rectangle;

public interface Shape {

  public void setLocation(int x, int y);
  public void changeLocation(int dx, int dy);
  public Rectangle[] getComponentRectangles();
  public boolean intersects(Shape rect);
  public boolean contains(Point point);
  
  public int x();
  public int y();
  
  public Shape duplicate();
}
