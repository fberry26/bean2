package komorebi.bean.editor;

public abstract class MultiTileObject {
  protected int tx, ty;
  protected int length;
  
  public abstract MultiTileObject build(int tx, int ty);
  public abstract void render();
  public abstract void showNodes();
  public abstract boolean containsPoint(int x, int y);


  public boolean wouldIntersect(int mx, int my, int sx, int sy) {
    
    for (int j = mx; j < mx + sx; j++)
    {
      for (int i = my; i > my - sy; i--)
      {
        if (containsPoint(j, i))
          return true;
      }
    }
    return false;
  }
  
  public boolean isBeingHoveredOver()
  {
    return containsPoint(Editor.getMouseTx(), Editor.getMouseTy());
  }
}
