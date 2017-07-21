package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Point;

import komorebi.bean.editor.objects.utils.Shape;

public interface Selection {
  public boolean contains(Shape item);
  public boolean contains(Point point);
  public boolean intersects(Shape rect);
  public void lockObjects();
  public void unlockObjects();
}
