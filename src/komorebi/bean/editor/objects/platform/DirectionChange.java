package komorebi.bean.editor.objects.platform;

public class DirectionChange {

  private PathDirection from, to;
  
  public DirectionChange(PathDirection from, PathDirection to)
  {
    this.from = from;
    this.to = to;
  }
  
  public PathDirection getFrom()
  {
    return from;
  }
  
  public PathDirection getTo()
  {
    return to;
  }
  
  public boolean equals(DirectionChange obj)
  {
    return obj.getFrom() == from && obj.getTo() == to;
  }
}
