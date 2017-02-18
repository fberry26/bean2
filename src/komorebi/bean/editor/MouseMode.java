package komorebi.bean.editor;

public abstract class MouseMode {

  public abstract void render();
  
  protected EditorLevel level;
  private int mx, my;
  private int prevMx, prevMy;
  
  public MouseMode(EditorLevel level)
  {
    this.level = level;
  }
  
  public void update()
  {
    prevMx = mx;
    prevMy = my;
    
    mx = Editor.getMouseTx();
    my = Editor.getMouseTy();
  }
  
  protected boolean isMouseInMap()
  {
    return (Editor.getMouseTx() < Editor.MAP_WIDTH && 
        Editor.getMouseTy() < Editor.MAP_HT);
  }
  
  protected boolean mouseSame()
  {
    return (prevMx == mx) && (prevMy == my);
  }
}
