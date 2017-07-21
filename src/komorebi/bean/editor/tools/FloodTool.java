package komorebi.bean.editor.tools;

import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Palette;
import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.AreaUtilities;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class FloodTool extends ObjectPlacementTool {
  
  public FloodTool(EditorLevel level, Palette palette) {
    super(level, palette);
  }
  
  @Override
  public void update()
  {
    super.update();
    
    if (KeyHandler.keyClick(Key.U))
    {
      PaletteItem.setAreOnePerLevelObjectsEnabled(false);
    }
    
    if (KeyHandler.keyClick(Key.I))
    {
      PaletteItem.setAreOnePerLevelObjectsEnabled(true);

    }
    
    if (KeyHandler.keyClick(Key.LBUTTON) && mouseInBounds())
    {      
      PaletteItem palItem = palette.getCurrentItem();
      ModRectangle locationOfItem = palItem.createRectangleAt(MouseHandler.getTileLocation());
      floodFill(palette.getCurrentItem(), locationOfItem);
    }
  }
  
  @Override
  public void render() {
    
  }
  
  public void floodFill(PaletteItem item, ModRectangle location)
  {    
    if (willPlacementOfItemBeValid(location))
    {
      placeItem(item, location);
      
      int width = item.getWidth();
      int height = item.getHeight();
      
      ModRectangle above = (ModRectangle) AreaUtilities.translate(location, 0, height);
      ModRectangle below = (ModRectangle) AreaUtilities.translate(location, 0, -height);
      ModRectangle toLeft = (ModRectangle) AreaUtilities.translate(location, -width, 0);
      ModRectangle toRight = (ModRectangle) AreaUtilities.translate(location, width, 0);
      
      floodFill(item, above);
      floodFill(item, below);
      floodFill(item, toLeft);
      floodFill(item, toRight);
      
    } 
  }
  

  
}
