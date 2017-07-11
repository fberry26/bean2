package komorebi.bean.editor.attributes;

import java.awt.Rectangle;

import komorebi.bean.engine.MouseHandler;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Image;

public class RadioButton {

  protected static final Image RADIO_BUTTON_OFF = new Image(
      80, 32, 10, 8, Draw.MISCELLANY);
  protected static final Image RADIO_BUTTON_ON = new Image(
      90, 32, 10, 8, Draw.MISCELLANY);
  
  private Rectangle area;
  private boolean checked;
  
  private static final int WIDTH = 10, HEIGHT = 8;
  
  public RadioButton(int x, int y, boolean checked)
  {
    area = new Rectangle(x, y, WIDTH, HEIGHT);
    setChecked(checked);
  }
  
  public void setChecked(boolean checked)
  {
    this.checked = checked;
  }
  
  public void render()
  {
    if (checked)
      renderOn();
    else
      renderOff();
  }
 
  private void renderOn()
  {
    Draw.draw(RADIO_BUTTON_ON, area.x, area.y);
  }
  
  private void renderOff()
  {
    Draw.draw(RADIO_BUTTON_OFF, area.x, area.y);
  }
  
  public void setLocation(int x, int y)
  {
    
    area.setLocation(x, y);
  }
  
  public boolean containsMouse()
  {
    return area.contains(MouseHandler.getLocation());
  }
  
  public boolean isChecked()
  {
    return checked;
  }
  
  
  
}
