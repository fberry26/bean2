package komorebi.bean.editor.tools.clickanddrag;

public interface Draggable {

  public void moveBy(int dx, int dy);
  public boolean canBeMovedBy(int dx, int dy);
}
