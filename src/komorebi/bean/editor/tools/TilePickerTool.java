package komorebi.bean.editor.tools;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Tool;
import komorebi.bean.editor.Palette;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class TilePickerTool extends Tool {

  private Palette palette;
  
  public TilePickerTool(EditorLevel level, Palette palette) {
    super(level);
    this.palette = palette;
  }

  @Override
  public void render() {
    if (KeyHandler.keyClick(Key.LBUTTON) && mouseInBounds())
    {      
      for (TileObject obj: level.getAllObjects())
      {
        if (obj.containsPoint(MouseHandler.getTx(), MouseHandler.getTy()))
        {
          palette.setCurrentItem(obj.getPaletteItem());
          
        }
      }
     
    }

  }

}
