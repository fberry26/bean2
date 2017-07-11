package komorebi.bean.editor.objects;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;
import komorebi.bean.editor.tools.clickanddrag.HorizontalGrabArrow;

public abstract class HorizontalExtendableObject extends ExtendableObject {  
  
  private HorizontalGrabArrow leftArrow, rightArrow;
  
  public HorizontalExtendableObject(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    horizontal = true;
    
    if (area != null)
    {
      leftArrow = HorizontalGrabArrow.createLeftArrow(area);
      rightArrow = HorizontalGrabArrow.createRightArrow(area);
    }
  }
  
  @Override
  public void moveBy(int dx, int dy)
  {
    super.moveBy(dx, dy);
    leftArrow.move(dx, dy);
    rightArrow.move(dx, dy);
  }
  
  public void showArrows()
  {
    leftArrow.render();
    rightArrow.render();
  }
  
  public boolean userHoveringOverLeftGrabber()
  {
    return leftArrow.isUserHoveringOver();
  }
  
  public boolean userHoveringOverRightGrabber()
  {
    return rightArrow.isUserHoveringOver();
  }
  
  public boolean userHoveringOverAGrabber()
  {
    return userHoveringOverLeftGrabber() || userHoveringOverRightGrabber();
  }
  
  public GrabArrow getGrabberBeingHoveredOver()
  {
    if (userHoveringOverLeftGrabber())
      return leftArrow;
    
    if (userHoveringOverRightGrabber())
      return rightArrow;
    
    throw new RuntimeException("Pre-condition unmet: no grabber being"
        + " hovered over");
  }
  
  @Override
  public void addToLength(int add)
  {
    super.addToLength(add);
    
    area.changeSize(add, 0);
  }
  
  public void extendLeft(int extendBy)
  {
    addToLength(extendBy);
    moveBy(-extendBy, 0);
    rightArrow.move(extendBy, 0);
  }
  
  public void extendRight(int extendBy)
  {
    addToLength(extendBy);
    
    rightArrow.move(extendBy, 0);
  }
  
  public int maxLength()
  {
    return Editor.TILE_MODSPACE.width;
  }
  
}
