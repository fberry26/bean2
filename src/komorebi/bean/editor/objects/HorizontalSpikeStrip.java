package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;

public class HorizontalSpikeStrip extends HorizontalExtendableObject {

  private boolean upsideDown;
  
  public HorizontalSpikeStrip(PaletteItem origin,
      boolean upsideDown)
  {
    super(origin, null);
    this.upsideDown = upsideDown;
    

  }
  
  public HorizontalSpikeStrip(PaletteItem origin, ModRectangle area, boolean upsideDown)
  {
    super(origin, area);
    
    this.upsideDown = upsideDown;

    length = 1;
  }
  
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new HorizontalSpikeStrip(origin, area, upsideDown);
  }

  @Override
  public void render() {    
    if (length == 1)
    {
      Draw.draw(Graphics.SPIKES[Graphics.SPIKE_ALONE][Tile.getColor()],
          area.x()*16, area.y()*16, upsideDown?Draw.ROTATE_180:Draw.ROTATE_NONE, 
              false);
    } else
    {
      if (upsideDown)
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_R][Tile.getColor()],
            modX(area.x())*16, modY(area.y())*16, Draw.ROTATE_180, false);
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_L][Tile.getColor()], 
            modX(area.x()+length-1)*16, modY(area.y())*16, Draw.ROTATE_180, false);
      } else
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_L][Tile.getColor()],
            modX(area.x())*16, modY(area.y())*16);
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_R][Tile.getColor()],
            modX(area.x()+length-1)*16, modY(area.y())*16);
      }
      
      for (int i = 1; i < length - 1; i++)
      {
        Draw.draw(Graphics.SPIKES[Graphics.SPIKE_C][Tile.getColor()],
            modX(area.x()+i)*16, modY(area.y())*16, 
            upsideDown?Draw.ROTATE_180:Draw.ROTATE_NONE, false);
      }
      
      
    }
  }
  
  public int minLength()
  {
    return 1;
  }
 
}
