package komorebi.bean.graphics.mod.loop;

public class OuterLoop extends Loop {

  private InnerLoop innerLoop;
  
  public OuterLoop(LoopSystem loop, 
      LoopDirection direction, InnerLoop innerLoop)
  {
    super(loop, direction);
    this.innerLoop = innerLoop;
  }

  @Override
  public void action() {
    innerLoop.execute();
    
  }

  @Override
  public int length() {
    return loop.currentRectangle().height;
  }
}
