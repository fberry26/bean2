package komorebi.bean.editor.attributes.buttonlink;

import komorebi.bean.editor.objects.utils.ModRectangle;

public interface Linkable {

  public String getName();
  public ModRectangle getArea();
  public boolean containsPoint(int x, int y);
  public String getID();
}
