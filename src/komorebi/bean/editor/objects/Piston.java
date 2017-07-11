package komorebi.bean.editor.objects;

import java.awt.Rectangle;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.attributes.Attributable;
import komorebi.bean.editor.attributes.AttributeFactory;
import komorebi.bean.editor.attributes.AttributeWindow;
import komorebi.bean.editor.attributes.AttributesPackage;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Piston extends VerticalExtendableObject implements
  Attributable {
  
  
  private AttributesPackage attributes;
  
  public Piston(PaletteItem origin)
  {
    super(origin, null);
  }
  
  public Piston(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);
    
    length = 2;
    
    createAttributes();
  }
  
  private void createAttributes()
  {
    attributes = new AttributesPackage();
    attributes.addAttribute(AttributeFactory.createFastSlowAttribute(
        AttributeWindow.TOP_LEFT));
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Piston(origin, area);
  }
  
  public int minLength()
  {
    return 2;
  }

  @Override
  public void render() {
        
    Draw.draw(Graphics.PISTON[Graphics.PISTON_END][Tile.getColor()], 
        modX(area.x)*16, modY(area.y)*16);
    
    for (int i = 1; i < length; i++)
    {
      Draw.draw(Graphics.PISTON[Graphics.PISTON_MID][Tile.getColor()], 
          modX(area.x)*16, modY(area.y+i)*16);
    }
    
    for (Rectangle comp: area.getComponentRectangles())
    {
      Draw.fill(Graphics.BLUE_SELECTED_FUNCTION, comp.x*16,
          comp.y*16, comp.width*16, comp.height*16);
    }    
  }
  
  public AttributesPackage getAttributes() {
    return attributes;
  }
  
}
