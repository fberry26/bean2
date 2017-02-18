package komorebi.bean.editor;

public class ClickAndDragMode extends MouseMode {

  public ClickAndDragMode(EditorLevel level) {
    super(level);
  }

  @Override
  public void update() {
    super.update();

  }

  @Override
  public void render() {
    for (MultiTileObject obj: level.getMultiTileObjects())
      obj.showNodes();
    
  }
  
  

  
}
