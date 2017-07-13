package komorebi.bean.editor.attributes;

import java.awt.Point;

import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.text.BeanFont;
import komorebi.bean.text.Font;

public class AttributeWindow {

  public static final Point TOP_LEFT = new Point(321, 128);

  private static AttributesPackage attributes;
  public static final Font TEXT_FONT = new BeanFont(0.5f);
  
  public static void setCurrentObjectBeingEdited(TileObject obj)
  {
    if (obj instanceof Attributable)
    {
      attributes = ((Attributable) obj).getAttributes();
    } else
    {
      attributes = null;
    }
  }
  
  public static void update()
  {
    if (attributes != null)
    {
      attributes.update();
    }
  }
  
  public static void render()
  {
    if (attributes != null)
    {
      attributes.render();
    }
  }

}
