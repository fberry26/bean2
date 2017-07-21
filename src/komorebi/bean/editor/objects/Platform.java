package komorebi.bean.editor.objects;

import java.awt.Rectangle;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModPath;
import komorebi.bean.editor.objects.utils.ModPathRectangle;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.tools.clickanddrag.HorizontalGrabArrow;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public class Platform extends ExtendableObject {

  private static final Image PATH_RED_FILL = new Image(100, 32, 1, 1,
      Draw.MISCELLANY);
  private static final Image ARROW_HEAD = new Image(174, 17, 16, 16,
      Draw.MISCELLANY);
  private static final Image ARROW_STRAIGHT = new Image(158, 17, 16, 16,
      Draw.MISCELLANY);
  private static final Image ARROW_TURN = new Image(142, 17, 16, 16,
      Draw.MISCELLANY);
  
  private int offset;
  
  private ModRectangle platformArea;
  
  public Platform(PaletteItem origin)
  {
    super(origin, null, null, null);
  }

  private Platform(PaletteItem origin, ModRectangle area)
  {
    super(origin, new ModPath(
        ModPathRectangle.convertToPathRectangle(area)), 
        HorizontalGrabArrow.createRightArrow(area),
        HorizontalGrabArrow.createLeftArrow(area));
    
    this.platformArea = area;
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

    
    for (ModRectangle component: (ModRectangle[]) area.getComponentRectangles())
    {
      for (Rectangle rect: component.getComponentRectangles())
      {
        Draw.fill(PATH_RED_FILL, rect.x*16, rect.y*16, 
            rect.width*16, rect.height*16);
      }
    }
    
  }

  @Override
  public void extendForward(int dx, int dy) {
    ((ModPath) area).addToPath(dx, dy);
    
  }

  @Override
  public void extendBackward(int dx, int dy) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean canExtendForward(int dx, int dy) {
    return dx != 0 ^ dy != 0;
  }

  @Override
  public boolean canExtendBackward(int dx, int dy) {
    return false;
  }

}
