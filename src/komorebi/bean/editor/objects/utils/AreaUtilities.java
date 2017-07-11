package komorebi.bean.editor.objects.utils;

import java.awt.Rectangle;

public class AreaUtilities {
  public static ModRectangle translate(Rectangle rect, int dx, int dy)
  {
    return new ModRectangle(rect.x + dx, rect.y + dy, rect.width, rect.height);
  }
  
  public static ModRectangle grow(Rectangle rect,
      int left, int right, int up, int down)
  {
    return new ModRectangle(rect.x - left, rect.y - down,
        rect.width + left + right, rect.height + up + down);
  }
}
