package komorebi.bean.editor.tools;

import java.awt.Point;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.EditorLevel;
import komorebi.bean.editor.Tool;
import komorebi.bean.editor.attributes.AttributeWindow;
import komorebi.bean.editor.objects.TileObject;
import komorebi.bean.editor.tools.clickanddrag.RectangularSelection;
import komorebi.bean.engine.Key;
import komorebi.bean.engine.KeyHandler;
import komorebi.bean.engine.MouseHandler;

public class SelectTool extends Tool {
  
  private static final Point SELECT_BUTTON_LOCATION = 
      new Point(18, 16);
  
  private TileObject selectedObj;
  
  public SelectTool(EditorLevel level)
  {
    super(level);
  }
  
  private RectangularSelection selection;

  private boolean readyToSelect = false,
      isSelecting = false, hasSelection = false;

  private int anchX, anchY;

  private int minX, minY, maxX, maxY;
  
  public boolean hasSelection()
  {
    return hasSelection;
  }

  public void render() {
    
  }
  
  @Override
  public void update()
  {
    super.update();
    
    int tx = MouseHandler.getTx();
    int ty = MouseHandler.getTy();
    
    if (KeyHandler.keyClick(Key.LBUTTON))
    {
      if (!KeyHandler.controlDown() && !mouseOverSelectToolButton())
        Editor.clearSelection();
      
      if (Editor.mouseInMap())
      {
        readyToSelect = true;
        isSelecting = false;
        hasSelection = false;

        anchX = minX = maxX = tx;
        anchY = minY = maxY = ty;
      }
      
    }
    


    if (readyToSelect && (tx != minX || ty != minY))
    {
      createNewRectangularSelection();
    }

    if (isSelecting)
    {
      int inBoundsX = keepInBounds(tx, 0, Editor.MAP_WIDTH - 1);
      int inBoundsY = keepInBounds(ty, 0, Editor.MAP_HT - 1);

      updateSelection(inBoundsX, inBoundsY);
    }

    if (KeyHandler.keyRelease(Key.LBUTTON))
    {
      if (readyToSelect && !isSelecting)
      {
        uponClickWithoutDrag();
      } else
      {
        selectedObj = null;
      }
      
      isSelecting = false;
      readyToSelect = false;
    }
  }
  
  private void createNewRectangularSelection()
  {
    isSelecting = true;
    hasSelection = true;
    readyToSelect = false;
    
    selection = new RectangularSelection();
    Editor.getSelection().addSelection(selection);
  }
  
  private int keepInBounds(int num, int lower, int upper)
  {
    return Math.min(Math.max(num, lower), upper);
  }

  private void updateSelection(int x, int y)
  {
    minX = Math.min(x, anchX);
    minY = Math.min(y, anchY);

    maxX = Math.max(x, anchX);
    maxY = Math.max(y, anchY);
    
    selection.setBounds(minX, minY, maxX, maxY);
  }
  
  private boolean mouseOverSelectToolButton()
  {
    return MouseHandler.getTileLocation().equals(SELECT_BUTTON_LOCATION);
  }
  
  private void uponClickWithoutDrag()
  {
    if (Editor.level().locationOccupied(MouseHandler.getTileLocation()))
    {
      selectedObj = Editor.level().getFirstObjectFoundAt(
          MouseHandler.getTileLocation());
    } else
    {
      selectedObj = null;
    }
    
    AttributeWindow.setCurrentObjectBeingEdited(selectedObj);
  }
  
  
}
