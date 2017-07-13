package komorebi.bean.editor.attributes;

import java.awt.Rectangle;

import komorebi.bean.graphics.Image;
import komorebi.bean.text.TextHandler;

public class Link {
  
  private Linkable linked;
  protected ClickButton button;
  
  private TextHandler text;
    
  protected Link(Linkable linked, String description, int x, int y,
      ClickButton button)
  {
    setLinkedObject(linked);
    text = new TextHandler();
    
    text.write(description, x, y, AttributeWindow.TEXT_FONT);
    this.button = button;
  }
  
  public boolean isEmpty()
  {
    return linked == null;
  }
  
  public void setLinkedObject(Linkable linked)
  {
    this.linked = linked;
  }
  
  public void update()
  {
    button.update();
  }
  
  public void render()
  {
    text.render();
    button.render();
  }
  
  public void switchImageOnButtonTo(Image image)
  {
    button.setImage(image);
  }
  
  public Rectangle getButtonArea()
  {
    return button.getArea();
  }
}
