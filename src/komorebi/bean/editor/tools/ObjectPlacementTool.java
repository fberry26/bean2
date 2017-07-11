package komorebi.bean.editor.tools;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Palette;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.Tool;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.MouseHandler;

public class ObjectPlacementTool extends Tool {

  protected Palette palette;
  
  public ObjectPlacementTool(EditorLevel level, Palette palette) {
    super(level);
    
    this.palette = palette;
  }

  @Override
  public void render() {
    
  }
  
  protected boolean willPlacementOfItemBeValid(ModRectangle locationOfItem)
  {
    boolean withinBounds;

    if (Editor.hasSelections())
    {       
      withinBounds = Editor.getSelection().contains(locationOfItem);
    } else
    {     
      withinBounds = Editor.MAP.contains(MouseHandler.getTileLocation());
    }
    
    return withinBounds && !level.willOverlapOtherObject(locationOfItem);
  }
  
  protected void placeItem(PaletteItem palItem, ModRectangle area)
  {
    level.addObject(palItem.buildObject(area));
  }

}
