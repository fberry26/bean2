package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.attributes.Attributable;
import komorebi.bean.editor.attributes.AttributeWindow;
import komorebi.bean.editor.attributes.AttributesPackage;
import komorebi.bean.editor.attributes.buttonlink.ButtonLinkAttribute;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;

public class Button extends SingleTileObject implements Attributable{

  private AttributesPackage attributes;
  
  public Button(PaletteItem pal, ModRectangle area) {
    super(pal, Tile.BUTTON, area);
    
    createAttributes();
  }
  
  private void createAttributes()
  {
    attributes = new AttributesPackage();
    
    attributes.addAttribute(new ButtonLinkAttribute(AttributeWindow.TOP_LEFT));
  }
  
  public TileObject build(PaletteItem origin, ModRectangle area)
  {
    return new Button(origin, area);
  }

  @Override
  public AttributesPackage getAttributes() {
    return attributes;
  }

}
