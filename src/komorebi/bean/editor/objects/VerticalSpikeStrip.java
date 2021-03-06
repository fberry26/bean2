package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Transformation;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class VerticalSpikeStrip extends VerticalExtendableObject {

 private boolean pointsLeft;
  
  public VerticalSpikeStrip(PaletteItem origin, boolean pointsLeft)
  {
    super(origin, null);
    this.pointsLeft = pointsLeft;
  }
  
  public VerticalSpikeStrip(PaletteItem origin, ModRectangle area, 
      boolean pointsLeft)
  {
    super(origin, area);
    this.pointsLeft = pointsLeft;
    

    length = 1;
  }
  
  public int minLength()
  {
    return 1;
  }
  
  public TileObject build(PaletteItem origin, ModRectangle area) {    
    return new VerticalSpikeStrip(origin, area, pointsLeft);
  }

  @Override
  public void render() {    
    if (length == 1)
    {
      Draw.draw(Graphics.SPIKES[Graphics.SPIKE_ALONE][Tile.getColor()],
          area.x()*16, area.y()*16, 
          pointsLeft?Transformation.ROTATE_COUNTERCLOCKWISE:
            Transformation.ROTATE_CLOCKWISE);
    } else
    {
      if (pointsLeft)
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_R][Tile.getColor()],
            area.x()*16, area.y()*16, Transformation.ROTATE_COUNTERCLOCKWISE);
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_L][Tile.getColor()], 
            (area.x())*16, (area.y()-length+1)*16, 
            Transformation.ROTATE_COUNTERCLOCKWISE);
      } else
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_L][Tile.getColor()],
            area.x()*16, area.y()*16, Transformation.ROTATE_CLOCKWISE);
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_R][Tile.getColor()],
            (area.x())*16, (area.y()-length+1)*16, 
            Transformation.ROTATE_CLOCKWISE);
      }
      
      for (int i = 1; i < length - 1; i++)
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_C][Tile.getColor()],
            (area.x())*16, (area.y()-i)*16, 
            pointsLeft?Transformation.ROTATE_COUNTERCLOCKWISE:
              Transformation.ROTATE_CLOCKWISE);
      }
    }
    
  }
}
