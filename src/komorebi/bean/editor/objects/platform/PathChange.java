package komorebi.bean.editor.objects.platform;

public class PathChange {

  private boolean beginning;
  private int distance;
  private PathDirection direction;
  
  public PathChange(int dx, int dy, boolean beginning)
  {
    this.beginning = beginning;
    
    this.direction = PathDirection.getDirectionOf(dx, dy);
    this.distance = Math.abs(direction.isVertical()?dy:dx);
  }
}
