package komorebi.bean.graphics.mod.loop;

public abstract class Loop {
  
  public abstract void action();
  public abstract int length();
  
  protected LoopSystem loop;
  private LoopDirection direction;
  
  public Loop(LoopSystem loop, 
      LoopDirection direction)
  {
    this.loop = loop;
    this.direction = direction;
  }
  
  public void execute()
  {    
    preLoop();
    
    while (condition())
    {
      preAction();
      action();
      postAction();
    }
  }
  
  public void preLoop()
  {
    direction.getPreLoop().execute(loop);
  }
  
  public boolean condition()
  {
    return direction.getCondition().evaluate(loop);
  }
 
  public void preAction()
  {
    direction.getPreAction().execute(loop, length());
  }
  
  public void postAction()
  {
    direction.getPostAction().execute(loop);
  }  
  
}
