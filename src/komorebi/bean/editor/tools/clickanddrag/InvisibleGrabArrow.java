package komorebi.bean.editor.tools.clickanddrag;

import java.awt.Dimension;
import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.platform.PathDirection;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.editor.objects.utils.Vector;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class InvisibleGrabArrow extends GrabArrow {
  
  private static enum Alignment
  {
    LEFT(PathDirection.LEFT, new Vector(0, 4),
        new Dimension(7, 9)),
    RIGHT(PathDirection.RIGHT, new Vector(25, 4),
        new Dimension(7, 9)),
    UP(PathDirection.UP, new Vector(12 ,9), 
        new Dimension(9, 7)),
    DOWN(PathDirection.DOWN, new Vector(12, 0), 
        new Dimension(9, 7));
    
    private PathDirection direction;
    private Vector offset;
    private Dimension size;
    
    private Alignment(PathDirection direction, Vector offset,
        Dimension size)
    {
      this.direction = direction;
      this.offset = offset;
      this.size = size;
    }
    
    public Vector getOffsets()
    {
      return offset;
    }
    
    public Dimension getSize()
    {
      return size;
    }
    
    public PathDirection getDirection()
    {
      return direction;
    }
    
    public static Alignment getAlignment(PathDirection dir)
    {
      for (Alignment alignment: values())
      {
        if (alignment.getDirection() == dir)
        {
          return alignment;
        }
      }
      
      throw new RuntimeException("No alignment corresponding to " + dir);
    }
    
    public static Vector realign(Alignment oldAlign, Alignment newAlign)
    {      
      Vector oldOff = oldAlign.getOffsets();
      Vector newOff = newAlign.getOffsets();
      
      return new Vector(newOff.getDx() - oldOff.getDx(),
          newOff.getDy() - oldOff.getDy());
      
    }
    
  }
  
  private Alignment alignment;
  
  public static InvisibleGrabArrow createForwardArrow(Rectangle objLoc)
  {
    Vector offsets = Alignment.RIGHT.getOffsets();
    
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 + 
        offsets.getDx(), objLoc.y*16 + offsets.getDy(), 
        7, 9, Editor.MODSPACE);
    
    return new InvisibleGrabArrow(arrowLoc, Alignment.RIGHT,
        true);
  }
  
  public static InvisibleGrabArrow createBackwardArrow(Rectangle objLoc)
  {
    Vector offsets = Alignment.LEFT.getOffsets();
    
    ModRectangle arrowLoc = new ModRectangle(objLoc.x*16 + 
        offsets.getDx(), objLoc.y*16 + offsets.getDy(), 
        7, 9, Editor.MODSPACE);
    
    return new InvisibleGrabArrow(arrowLoc, Alignment.LEFT,
        false);
  }
  
  protected InvisibleGrabArrow(ModRectangle area, Alignment alignment,
      boolean forward) {
    super(area, forward);
    
    this.alignment = alignment;
  }
  
  public void realign(PathDirection dir)
  {  
    Alignment newAlignment = Alignment.getAlignment(dir);
    
    Vector moveBy = Alignment.realign(alignment, newAlignment);
    
    area.translate(moveBy.getDx(), moveBy.getDy());
    
    this.alignment = newAlignment;
    area.setSize(newAlignment.getSize());
    
  }

  @Override
  public void render() {
    Draw.fill(Graphics.BLUE_SELECTED_FUNCTION, area.x, area.y,
        area.width, area.height);
  }
}
