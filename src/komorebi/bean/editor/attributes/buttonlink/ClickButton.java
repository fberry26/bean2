package komorebi.bean.editor.attributes.buttonlink;

import java.awt.Rectangle;

import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Image;

public abstract class ClickButton {

  private Rectangle area;
  private Image image;

  public ClickButton(Rectangle area, Image image)
  {
    this.area = area;
    this.image = image;
  }

  public void update()
  {
    if (area.contains(MouseHandler.getLocation()) && 
        KeyHandler.keyClick(Key.LBUTTON)
        )
    {
      uponClick();
    }
  }
  
  public void setImage(Image image)
  {
    this.image = image;
  }

  public void render()
  {
    Draw.draw(image, area.x, area.y);
  }
  
  public Rectangle getArea()
  {
    return area;
  }

  public abstract void uponClick();
}
