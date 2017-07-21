package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;

public abstract class SingleAxisExtendableObject extends ExtendableObject {
  
  public SingleAxisExtendableObject(PaletteItem origin, ModRectangle area,
      GrabArrow forward, GrabArrow backward) {
    super(origin, area, forward, backward);
  }
  
  public abstract int minLength();
  public abstract int maxLength();
  
  public void addToLength(int add)
  {
    this.length += add;
  }
  
  public int getLength()
  {
    return length;
  }
  
  public boolean canBeExtendedBy(int extendBy)
  {
    return length + extendBy >= minLength() &&
        length + extendBy <= maxLength();
  }
}
