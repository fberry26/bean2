package komorebi.bean.editor.objects;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.VerticalGrabArrow;
import komorebi.bean.editor.tools.clickanddrag.GrabArrow;

public abstract class VerticalExtendableObject extends ExtendableObject {

  private VerticalGrabArrow upArrow, downArrow;

  public VerticalExtendableObject(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    horizontal = false;

    if (area != null)
    {
      upArrow = VerticalGrabArrow.createUpArrow(area);
      downArrow = VerticalGrabArrow.createDownArrow(area);
    }
  }

  public void showArrows()
  {
    upArrow.render();
    downArrow.render();
  }

  public boolean userHoveringOverUpperGrabber()
  {
    return upArrow.isUserHoveringOver();
  }

  public boolean userHoveringOverLowerGrabber()
  {
    return downArrow.isUserHoveringOver();

  }

  @Override
  public void addToLength(int add)
  {
    super.addToLength(add);

    area.changeSize(0, add);
  }

  public void extendUp(int extendBy)
  {
    addToLength(extendBy);
    upArrow.move(0, extendBy);
  }

  public void extendDown(int extendBy)
  {
    addToLength(extendBy);
    moveBy(0, -extendBy);
    upArrow.move(0, extendBy);

  }
  
  @Override
  public void moveBy(int dx, int dy)
  {
    super.moveBy(dx, dy);
    upArrow.move(dx, dy);
    downArrow.move(dx, dy);
  }

  @Override
  public boolean userHoveringOverAGrabber()
  {
    return userHoveringOverUpperGrabber() || userHoveringOverLowerGrabber();
  }

  public GrabArrow getGrabberBeingHoveredOver()
  {
    if (userHoveringOverUpperGrabber())
      return upArrow;

    if (userHoveringOverLowerGrabber())
      return downArrow;

    throw new RuntimeException("Pre-condition unmet: no grabber being"
        + " hovered over");
  }
  
  public int maxLength()
  {
    return Editor.TILE_MODSPACE.height;
  }

}
