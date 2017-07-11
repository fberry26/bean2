package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Point;
import java.awt.Rectangle;

public interface Selection {
  public boolean contains(Rectangle item);
  public boolean contains(Point point);
  public boolean intersects(Rectangle rect);
  public void lockObjects();
  public void unlockObjects();
}
