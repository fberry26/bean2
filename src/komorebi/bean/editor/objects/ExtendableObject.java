package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;

public abstract class ExtendableObject extends MultiTileObject {
  
  public ExtendableObject(PaletteItem origin, ModRectangle area) {
    super(origin, area);
  }
  public abstract void showArrows();
  public abstract int minLength();
  public abstract int maxLength();
  
  public abstract boolean userHoveringOverAGrabber();
  public abstract GrabArrow getGrabberBeingHoveredOver();
  
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
