package komorebi.bean.engine;

import komorebi.bean.editor.Editor;
import komorebi.bean.game.Game;
import komorebi.bean.title.AboutUs;
import komorebi.bean.title.ChangeBean;
import komorebi.bean.title.Title;

public class GameHandler {
  
  public static void initialize()
  {
    game = new Game();
    edit = new Editor();
    title = new Title();
    aboutUs = new AboutUs();
    changeBean = new ChangeBean();
    
    state = title;
  }
  
  public static enum States
  {
    TITLE, GAME, CHANGE_BEAN, ABOUT_US, EDIT;
  }
  
  public static interface State
  {
    void getInput();
    void update();
    void render();
    
    void gainFocus();
  }
  
  private static State state;
  private static States stateType;
  
  private static Game game;
  private static Editor edit;
  private static Title title;
  private static AboutUs aboutUs;
  private static ChangeBean changeBean; 

  public static void getInput()
  {
    KeyHandler.getInput();
    state.getInput();
  }
  
  public static void update()
  {
    state.update();
  }
  
  public static void render()
  {
    state.render();
  }
  
  public static void setState(States newType)
  {
    stateType = newType;
    
    switch (newType)
    {
      case EDIT:
        state = edit;
        break;
      case GAME:
        state = game;
        break;
      case TITLE:
        state = title;
        break;
      case ABOUT_US:
        state = aboutUs;
        break;
      case CHANGE_BEAN:
        state = changeBean;
        break;
      default:
        break;
    }
    
    state.gainFocus();
  }
  
  public static States getState()
  {
    return stateType;
  }
}
