package komorebi.bean.editor.objects;

import java.awt.Point;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.attributes.Attributable;
import komorebi.bean.editor.attributes.AttributeFactory;
import komorebi.bean.editor.attributes.AttributeWindow;
import komorebi.bean.editor.attributes.AttributesPackage;
import komorebi.bean.editor.attributes.ChoiceAttribute;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Treadmill extends HorizontalExtendableObject 
  implements Attributable {
  
  private AttributesPackage attributes;

  public Treadmill(PaletteItem origin)
  {
    super(origin, null);
  }

  public Treadmill(PaletteItem origin, ModRectangle area)
  {
    super(origin, area);

    length = 2;
    createAttributes();
  }
  
  private void createAttributes()
  {
    attributes = new AttributesPackage();
    
    Point begin = AttributeWindow.TOP_LEFT;
    
    ChoiceAttribute attribute = 
        AttributeFactory.createFastSlowAttribute(begin);
    attributes.addAttribute(attribute);
    begin = attribute.nextAttributeStartsAt();
    
    attribute = AttributeFactory.createLeftRightAttribute(begin);
    attributes.addAttribute(attribute);
    
    
  }
  
  public int minLength()
  {
    return 2;
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Treadmill(origin, area);
  }

  @Override
  public void render() {    
    Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_L][0], modX(area.x)*16, 
        modY(area.y)*16);

    for (int j = area.x + 1; j < area.x + length - 1; j++)
    {
      Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_C][0], 
          modX(j)*16, modY(area.y)*16);
    }

    Draw.draw(Graphics.TREADMILL[Graphics.TREADMILL_R][0], 
        modX(area.x+length-1)*16, modY(area.y)*16);
        
  }

  @Override
  public AttributesPackage getAttributes() {
    return attributes;
  }

}
