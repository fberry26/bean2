package komorebi.bean.editor.objects;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.HorizontalGrabArrow;

public abstract class HorizontalExtendableObject extends SingleAxisExtendableObject {  
    
  public HorizontalExtendableObject(PaletteItem origin, ModRectangle area)
  {
    super(origin, area, area==null?null:HorizontalGrabArrow.createRightArrow(area),
        area==null?null:HorizontalGrabArrow.createLeftArrow(area));
    horizontal = true;
  }
  
  @Override
  public void addToLength(int add)
  {
    super.addToLength(add);
    
    ((ModRectangle) area).changeSize(add, 0);
  }
  
  public void extendBackward(int dx, int dy)
  {
    addToLength(dx);
    moveBy(-dx, 0);
    forwardArrow.move(dx, 0);
  }
  
  public void extendForward(int dx, int dy)
  {
    addToLength(dx);
    
    forwardArrow.move(dx, 0);
  }
  
  public int maxLength()
  {
    return Editor.TILE_MODSPACE.width;
  }
  
  public boolean canExtendForward(int dx, int dy)
  {    
    ModRectangle newLocation = AreaUtilities.grow(((ModRectangle) area), 
        0, dx, 0, 0);

    return (dx != 0 && 
        canBeExtendedBy(dx) &&
        !Editor.level().willOverlapOtherObjectExcludeSelf(newLocation, 
            this));
  }
  
  public boolean canExtendBackward(int dx, int dy)
  {    
    
    ModRectangle newLocation = AreaUtilities.grow(((ModRectangle) area), 
        dx, 0, 0, 0);

    return (dx != 0 && 
        canBeExtendedBy(dx) 
        && !Editor.level().willOverlapOtherObjectExcludeSelf(
            newLocation, this));
  }
  
}
