package komorebi.bean.graphics.mod.loop;

public class InnerLoop extends Loop {

  public InnerLoop(LoopSystem loop, 
      LoopDirection direction)
  {
    super(loop, direction);
  }
  
  public void action() {
    loop.assign();
  }

  @Override
  public int length() {
    return loop.currentRectangle().width;
  }

}
