package komorebi.bean.editor.attributes.choice;

import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;

public class RadioButtonGroup {

  private RadioButton[] buttons;
  
  public RadioButtonGroup(RadioButton[] buttons)
  {
    this.buttons = buttons;
  }
  
  public void update()
  {
    if (KeyHandler.keyClick(Key.LBUTTON))
    {
      for (RadioButton button: buttons)
      {
        if (button.containsMouse())
        {
          activate(button);
        }
      }
    }
  }
  
  private void activate(RadioButton activate)
  {
    for (RadioButton button: buttons)
    {
      if (button != activate)
        button.setChecked(false);
      else
        button.setChecked(true);
    }
  }
  
  public void render()
  {
    for (RadioButton button: buttons)
    {
      button.render();
    }
  }
  

}
