package komorebi.bean.graphics.mod.loop;

public abstract class LoopProcedure {

  public abstract void execute(LoopSystem loop);
  
  public static abstract class LoopProcedureWithIntArgument
  {
    public abstract void execute(LoopSystem loop,
        int arg);
  }
}
