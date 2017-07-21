package komorebi.bean.editor.objects;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.VerticalGrabArrow;

public abstract class VerticalExtendableObject extends SingleAxisExtendableObject {

  public VerticalExtendableObject(PaletteItem origin, ModRectangle area)
  {
    super(origin, area, area==null?null:VerticalGrabArrow.createUpArrow(area),
        area==null?null:VerticalGrabArrow.createDownArrow(area));
    horizontal = false;
  }
  
  @Override
  public void addToLength(int add)
  {
    super.addToLength(add);

    ((ModRectangle) area).changeSize(0, add);
  }

  public void extendForward(int dx, int dy)
  {
    addToLength(dy);
    forwardArrow.move(0, dy);
  }

  public void extendBackward(int dx, int dy)
  {
    addToLength(dy);
    moveBy(0, -dy);
    forwardArrow.move(0, dy);

  }
  
  public boolean canExtendForward(int dx, int dy)
  {
    ModRectangle newLocation = AreaUtilities.grow((ModRectangle) area, 
        0, 0, dy, 0);

    return (dy != 0 && 
        canBeExtendedBy(dy)
        && !Editor.level().willOverlapOtherObjectExcludeSelf(newLocation, 
            this));
  }
  
  public boolean canExtendBackward(int dx, int dy)
  {
        
    ModRectangle newLocation = AreaUtilities.grow((ModRectangle) area, 
        0, 0, 0, dy);


    return (dy != 0 && 
        canBeExtendedBy(dy)
        && !Editor.level().willOverlapOtherObjectExcludeSelf(newLocation, 
            this));
  }
  
  public int maxLength()
  {
    return Editor.TILE_MODSPACE.height;
  }

}
