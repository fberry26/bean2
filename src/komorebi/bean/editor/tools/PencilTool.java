package komorebi.bean.editor.tools;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Palette;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.OnePerLevelObject;
import komorebi.bean.editor.objects.OnePerLevelObject.Bean;
import komorebi.bean.editor.objects.OnePerLevelObject.Flag;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class PencilTool extends ObjectPlacementTool {

  public PencilTool(EditorLevel level, Palette palette) {
    super(level, palette);
  }

  @Override
  public void update() {
    super.update();

    PaletteItem palItem = palette.getCurrentItem();
    ModRectangle locationIfPlaced = palItem.createRectangleAt(
        MouseHandler.getTileLocation());
    
    if ((KeyHandler.keyClick(Key.LBUTTON) || 
        (KeyHandler.keyDown(Key.LBUTTON) && !mouseSame()))
        && willPlacementOfItemBeValid(locationIfPlaced))
    { 
      placeItem(locationIfPlaced);
    }
  }
  
  private void placeItem(ModRectangle location)
  {
    PaletteItem palItem = palette.getCurrentItem();

    if (palItem.onlyOnePermittedPerLevel())
    {
      placeOnePerLevelObject(palItem);
    } else
    {
      super.placeItem(palItem, location);
    }

  }
  
  private void placeOnePerLevelObject(PaletteItem palItem)
  {
    Class<? extends OnePerLevelObject> objType = 
        PaletteItem.getOnePerLevelClass(palItem);
    if (level.alreadyHas(objType))
    {
      OnePerLevelObject moveThis = level.getOnePerLevelObject(objType);
      moveThis.moveTo(MouseHandler.getTx(), MouseHandler.getTy());
    } else
    {
      addOnePerLevelObject(objType);
    }
  }

  private void addOnePerLevelObject(Class<? extends OnePerLevelObject> objType)
  {
    if (objType.isAssignableFrom(Bean.class))
    {
      level.addObject(new Bean(MouseHandler.getTileLocation()));
    } else if (objType.isAssignableFrom(Flag.class))
    {
      level.addObject(new Flag(MouseHandler.getTileLocation()));
    }
  }


  @Override
  public void render() {
    // TODO Auto-generated method stub

  }


}
