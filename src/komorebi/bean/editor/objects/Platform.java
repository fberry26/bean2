package komorebi.bean.editor.objects;

import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.platform.ModPath;
import komorebi.bean.editor.objects.platform.ModPathRectangle;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.InvisibleGrabArrow;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;
import komorebi.bean.graphics.PlatformPath;

public class Platform extends ExtendableObject {

  private static final Image PATH_RED_FILL = new Image(100, 32, 1, 1,
      Draw.MISCELLANY);
  
  private int offset;
  
  private ModRectangle platformArea;
  private PlatformPath pathDrawing;
  
  public Platform(PaletteItem origin)
  {
    super(origin, null, null, null);
  }

  private Platform(PaletteItem origin, ModRectangle area)
  {
    super(origin, new ModPath(
        ModPathRectangle.convertToPathRectangle(area)), 
        InvisibleGrabArrow.createForwardArrow(area),
        InvisibleGrabArrow.createBackwardArrow(area));
    
    this.platformArea = area;
    pathDrawing = new PlatformPath((ModPath) this.area);
    
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Platform(origin, area);
  }

  @Override
  public void render() {    
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_L][Tile.getColor()], 
        modX(platformArea.x()+offset)*16+6, modY(platformArea.y())*16);
    Draw.draw(Graphics.PLATFORM[Graphics.PLATFORM_R][Tile.getColor()], 
        modX(platformArea.x()+offset+1)*16, modY(platformArea.y())*16);

    for (ModRectangle component: 
      (ModRectangle[]) area.getComponentRectangles())
    {
      for (Rectangle rect: component.getComponentRectangles())
      {
        Draw.fill(PATH_RED_FILL, rect.x*16, rect.y*16, 
            rect.width*16, rect.height*16);
      }
    }
    
    pathDrawing.render();
    
  }

  @Override
  public void extendForward(int dx, int dy) {
    ((ModPath) area).addToEnd(dx, dy);
    forwardArrow.move(dx, dy);
    ((InvisibleGrabArrow) forwardArrow).realign(
        ((ModPath) area).getLastDirection());
    
  }

  @Override
  public void extendBackward(int dx, int dy) {
    ((ModPath) area).addToBeginning(-dx, -dy);    
    backwardArrow.move(-dx, -dy);
    ((InvisibleGrabArrow) backwardArrow).realign(
        ((ModPath) area).getFirstDirection().getOpposite());
  }

  @Override
  public boolean canExtendForward(int dx, int dy) {
    
    ModPathRectangle rect = 
        ((ModPath) area).getAdditionToPath(dx, dy, false);
    
    return (dx != 0 ^ dy != 0) &&
        !Editor.level().willOverlapOtherObjectExcludeSelf(rect, this)
        && !((ModPath) area).doesMotionContradictArrowDirection(dx, dy, 
            false);
  }

  @Override
  public boolean canExtendBackward(int dx, int dy) {
    dx = -dx;
    dy = -dy;
    
    ModPathRectangle rect = 
        ((ModPath) area).getAdditionToPath(dx, dy, true);
    
    return (dx != 0 ^ dy != 0) &&
        !Editor.level().willOverlapOtherObjectExcludeSelf(rect, this)
        && !((ModPath) area).doesMotionContradictArrowDirection(dx, dy, true);
  }

}
