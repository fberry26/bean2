package komorebi.bean.title;

import komorebi.bean.engine.GameHandler;
import komorebi.bean.engine.GameHandler.State;
import komorebi.bean.engine.GameHandler.States;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.graphics.Transformation;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.text.TextHandler;

public class AboutUs implements State {

  private TextHandler text;
  
  public AboutUs()
  {
    text = new TextHandler();
    text.write("Anthony Bonacci", 15, 200);
    text.write("Jason Chan", 15, 180);
    text.write("Andrew Faulkenberry", 15, 160);
    text.write("Aaron Roy", 15, 140);
    
    text.write("GFX and Levels", 195, 200);
    text.write("Levels", 195, 180);
    text.write("Code and Music", 195, 160);
    text.write("Code and Levels", 195, 140);
    
    text.write("Return", 245, 10);
  }
  
  @Override
  public void getInput() {
    // TODO Auto-generated method stub

  }

  @Override
  public void update() {
    if (KeyHandler.keyClick(Key.ENTER))
    {
      GameHandler.setState(States.TITLE);
    }

  }

  @Override
  public void render() {
    Draw.draw(Graphics.BLANK_TITLE_SCREEN, 0, 0);
    Draw.draw(Graphics.ARROW, 232, 11, 1, 
        Transformation.ROTATE_CLOCKWISE);
    
    text.render();

  }

  @Override
  public void gainFocus() {
    // TODO Auto-generated method stub

  }

}
