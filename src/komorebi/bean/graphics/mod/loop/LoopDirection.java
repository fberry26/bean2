package komorebi.bean.graphics.mod.loop;

import komorebi.bean.graphics.mod.loop.LoopProcedure.LoopProcedureWithIntArgument;

public enum LoopDirection {

  Y_FORWARD(
      new LoopProcedure() 
      {
        public void execute(LoopSystem loop) {
          loop.fromY = loop.minY;
        }
      },
      new LoopCondition()
      {
        public boolean evaluate(LoopSystem loop) {
          return loop.fromY < loop.maxY;
        }
      },
      new LoopProcedureWithIntArgument() 
      {
        public void execute(LoopSystem loop
            , int length) {
          loop.toY = loop.fromY + length;
        }
      },
      new LoopProcedure() 
      {
        public void execute(LoopSystem loop) {
          loop.fromY = loop.toY;
        }
      }
  ),
  
  X_FORWARD(
      new LoopProcedure() 
      {
        public void execute(LoopSystem loop) {
          loop.fromX = loop.minX;
        }
      },
      new LoopCondition()
      {
        public boolean evaluate(LoopSystem loop) {
          return loop.fromX < loop.maxX;
        }
      },
      new LoopProcedureWithIntArgument() 
      {
        public void execute(LoopSystem loop
            , int length) {
          loop.toX = loop.fromX + length;
        }
      },
      new LoopProcedure() 
      {
        public void execute(LoopSystem loop) {
          loop.fromX = loop.toX;
        }
      }
  ),
  X_BACKWARD(
      new LoopProcedure()
      {
        public void execute(LoopSystem loop) {
          loop.toX = loop.maxX;          
        }
      },
      new LoopCondition()
      {
        public boolean evaluate(LoopSystem loop)
        {
          return loop.toX > loop.minX;
        }
      },
      new LoopProcedureWithIntArgument()
      {
        public void execute(LoopSystem loop,
            int length)
        {
          loop.fromX = loop.toX - length;
        }
      },
      new LoopProcedure()
      {
        public void execute(LoopSystem loop)
        {
          loop.toX = loop.fromX;
        }
      }
  ),
  Y_BACKWARD(
      new LoopProcedure()
      {
        public void execute(LoopSystem loop) {
          loop.toY = loop.maxY;          
        }
      },
      new LoopCondition()
      {
        public boolean evaluate(LoopSystem loop)
        {
          return loop.toY > loop.minY;
        }
      },
      new LoopProcedureWithIntArgument()
      {
        public void execute(LoopSystem loop,
            int length)
        {
          loop.fromY = loop.toY - length;
        }
      },
      new LoopProcedure()
      {
        public void execute(LoopSystem loop)
        {
          loop.toY = loop.fromY;
        }
      }
  );

  private LoopProcedure preLoop, postAction;
  private LoopProcedureWithIntArgument preAction;
  private LoopCondition condition;

  private LoopDirection(LoopProcedure preLoop, 
      LoopCondition condition, 
      LoopProcedureWithIntArgument preAction,
      LoopProcedure postAction)
  {
    this.preLoop = preLoop;
    this.condition = condition;
    this.preAction = preAction;
    this.postAction = postAction;
  }
  
  public LoopProcedure getPreLoop()
  {
    return preLoop;
  }
  
  public LoopCondition getCondition()
  {
    return condition;
  }
  
  public LoopProcedureWithIntArgument getPreAction()
  {
    return preAction;
  }
  
  public LoopProcedure getPostAction()
  {
    return postAction;
  }
  
  public LoopDirection reverse()
  {
    return values()[values().length - 1 - this.ordinal()];
  }
  
  public boolean isAlongXAxis()
  {
    return this == X_FORWARD || this == 
        X_BACKWARD;
  }
}
