package komorebi.bean.title;

import komorebi.bean.engine.GameHandler;
import komorebi.bean.engine.GameHandler.State;
import komorebi.bean.engine.GameHandler.States;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.game.Bean;
import komorebi.bean.game.Bean.Color;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.text.BeanFont;
import komorebi.bean.text.TextHandler;

public class ChangeBean implements State {

  private TextHandler text;
  private byte arrowAt;
  
  private static final byte RED_BEAN = 0, PINK_BEAN = 6;
  
  public ChangeBean()
  {
    text = new TextHandler();
    text.write("Pick your bean", 50, 220, new BeanFont(2));
    text.write("Click enter to return", 72, 35);
    arrowAt = RED_BEAN;
  }
  
  @Override
  public void getInput() {
    
  }

  @Override
  public void update() {
    if (KeyHandler.keyDownStaggered(Key.RIGHT, 12))
    {
      arrowAt++;
      
      if (arrowAt > PINK_BEAN)
      {
        arrowAt = RED_BEAN;
      }
    }
    
    if (KeyHandler.keyDownStaggered(Key.LEFT, 12))
    {
      arrowAt--;
      
      if (arrowAt < RED_BEAN)
      {
        arrowAt = PINK_BEAN;
      }
    }
    
    if (KeyHandler.keyClick(Key.ENTER))
    {
      Bean.setColor(Color.values()[arrowAt]);
      GameHandler.setState(States.TITLE);
    }

  }

  @Override
  public void render() {
    Draw.draw(Graphics.BLANK_TITLE_SCREEN, 0, 0);
    
    text.render();
    
    for (int i = 0; i <= PINK_BEAN; i++)
    {
      Draw.draw(Graphics.BEAN[i], 8+i*46, 120, 2);
    }
    
    Draw.draw(Graphics.ARROW, 9+(int) arrowAt*46, 90, 2);
  }

  @Override
  public void gainFocus() {
    // TODO Auto-generated method stub

  }

}
