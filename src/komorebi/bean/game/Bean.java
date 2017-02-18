package komorebi.bean.game;

public class Bean {
  
  private static Color color;
  
  public enum Color
  {
    RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK;
    
  }
  
  public static void setColor(Color color)
  {
    Bean.color = color;
  }
  
  public static Color getColor()
  {
    return color;
  }

}
