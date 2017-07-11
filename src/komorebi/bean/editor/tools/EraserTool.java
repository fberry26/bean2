package komorebi.bean.editor.tools;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Tool;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class EraserTool extends Tool {

  public EraserTool(EditorLevel level) {
    super(level);
  }

  @Override
  public void update() {
    super.update();
    erase();
  }

  private void erase()
  {
    if (KeyHandler.keyDown(Key.LBUTTON) && mouseInBounds())
    {
      TileObject eraseThis = null;
      
      for (TileObject obj: level.getAllObjects())
      {
        if (obj.containsPoint(MouseHandler.getTx(), MouseHandler.getTy()))
        {
          eraseThis = obj;
          break;
        }
      }
      if (eraseThis != null)
        level.removeObject(eraseThis);
    }
  }
  
  public void render() {
    
  }

}
