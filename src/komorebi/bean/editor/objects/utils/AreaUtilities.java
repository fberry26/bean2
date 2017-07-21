package komorebi.bean.editor.objects.utils;

import java.awt.Rectangle;

public class AreaUtilities {
  public static Shape translate(Shape shape, int dx, int dy)
  {
    Shape duplicate = shape.duplicate();
    duplicate.changeLocation(dx, dy);
    
    return duplicate;
  }
  
  public static Rectangle translateRectangle(Rectangle rect, int dx, int dy)
  {
    return new Rectangle(rect.x + dx, rect.y + dy, rect.width, rect.height);
  }
  
  public static ModRectangle grow(Rectangle rect,
      int left, int right, int up, int down)
  {
    return new ModRectangle(rect.x - left, rect.y - down,
        rect.width + left + right, rect.height + up + down);
  }
}
