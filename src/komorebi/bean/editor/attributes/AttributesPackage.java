package komorebi.bean.editor.attributes;

import java.util.ArrayList;

public class AttributesPackage {

  private ArrayList<Attribute> attributes;
  
  public AttributesPackage()
  {
    attributes = new ArrayList<Attribute>();
  }
  
  public void addAttribute(Attribute attribute)
  {
    attributes.add(attribute);
  }
  
  public void update()
  {
    for (Attribute att: attributes)
    {
      att.update();
    }
  }
  
  public void render()
  {
    for (Attribute att: attributes)
    {
      att.render();
    }
  }
  
}
