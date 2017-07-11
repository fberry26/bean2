package komorebi.bean.editor.attributes;

import java.awt.Point;

public abstract class Attribute {
  
  protected Point nextAttribute;
  
  public abstract void update();
  public abstract void render();
  
  public Point nextAttributeStartsAt()
  {
    return nextAttribute;
  }
  
}
