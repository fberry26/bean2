package komorebi.bean.editor.objects;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.editor.attributes.Linkable;
import komorebi.bean.editor.objects.utils.ModRectangle;
import komorebi.bean.game.Tile;

public class Gate extends SingleTileObject implements Linkable {

  public Gate(boolean horizontal, ModRectangle area) {

    super(horizontal?PaletteItem.GATE_HORIZ:PaletteItem.GATE_VERT, 
        horizontal?Tile.GATE_HORIZ:Tile.GATE_VERT, area);
  }

  @Override
  public String getName() {
    return "Gate";
  }

  @Override
  public TileObject build(PaletteItem origin, ModRectangle area) {
    return new Gate(origin == PaletteItem.GATE_HORIZ, area);
  }
  
  public Gate()
  {
    this(false, null);
  }

}
