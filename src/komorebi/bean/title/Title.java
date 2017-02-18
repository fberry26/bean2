package komorebi.bean.title;

import komorebi.bean.engine.GameHandler;
import komorebi.bean.engine.GameHandler.State;
import komorebi.bean.engine.GameHandler.States;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.Main;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class Title implements State {
    
  private static final byte START = 0, LEVEL_EDITOR = 1,
      CHANGE_BEAN = 2, ABOUT_US = 3;
  private byte arrowAt;
  
  public Title()
  {
    arrowAt = START;
  }
 
  @Override
  public void getInput() {
    // TODO Auto-generated method stub   
  }

  @Override
  public void update() {
    
    if (KeyHandler.keyDownStaggered(Key.LEFT, 12) || 
        KeyHandler.keyDownStaggered(Key.UP, 12))
    {
      arrowAt--;
      
      if (arrowAt < START)
        arrowAt = ABOUT_US;
    }
    
    if (KeyHandler.keyDownStaggered(Key.RIGHT, 12) || 
        KeyHandler.keyDownStaggered(Key.DOWN, 12))
    {
      arrowAt++;
      
      if (arrowAt > ABOUT_US)
        arrowAt = START;
    }
    
    if (KeyHandler.keyClick(Key.ENTER))
    {
      switch (arrowAt)
      {
        case LEVEL_EDITOR:
          Main.adjustScreenSize(true);
          GameHandler.setState(States.EDIT);
          break;
        case ABOUT_US:
          GameHandler.setState(States.ABOUT_US);
          break;
        case CHANGE_BEAN:
          GameHandler.setState(States.CHANGE_BEAN);
          break;
        default: break;
      }
      
    }
    
  }

  @Override
  public void render() {
    Draw.draw(Graphics.TITLE_SCREEN, 0, 0);
        
    switch (arrowAt)
    {
      case START: 
        Draw.draw(Graphics.ARROW, 20, 51, 1, Draw.ROTATE_CLOCKWISE);
        break;
      case LEVEL_EDITOR:
        Draw.draw(Graphics.ARROW, 20, 19, 1, Draw.ROTATE_CLOCKWISE);
        break;
      case CHANGE_BEAN:
        Draw.draw(Graphics.ARROW, 180, 51, 1, Draw.ROTATE_CLOCKWISE);
        break;
      case ABOUT_US:
        Draw.draw(Graphics.ARROW, 180, 19, 1, Draw.ROTATE_CLOCKWISE);
        break;
      default: break;
    }
    
  }

  @Override
  public void gainFocus() {
    arrowAt = START;
    
  }

}
