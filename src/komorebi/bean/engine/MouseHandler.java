package komorebi.bean.engine;

import java.awt.Point;

import org.lwjgl.input.Mouse;

public class MouseHandler {

  private static Point tileLocation = new Point();
  private static Point absLocation = new Point();
  
  public static void getInput()
  {
    tileLocation.setLocation(tileX(), tileY());
    absLocation.setLocation(absX(), absY());
  }
  
  private static float absX()
  {
    return (float) Mouse.getX() / Main.scale;
  }
  
  private static float absY()
  {
    return (float) Mouse.getY() / Main.scale;

  }
  
  private static int tileX()
  {
    return (int) ((float) Mouse.getX()/Main.scale) / 16;
  }
  
  private static int tileY()
  {
    return (int) ((float) Mouse.getY()/Main.scale) / 16;
  }
  
  public static Point getTileLocation()
  {
    return tileLocation;
  }
  
  public static int getTx()
  {
    return tileLocation.x;
  }
  
  public static int getTy()
  {
    return tileLocation.y;
  }
  
  public static Point getLocation()
  {
    return absLocation;
  }
  
  public static float getX()
  {
    return (float) absLocation.getX();
  }
  
  public static float getY()
  {
    return (float) absLocation.getY();
  }
  
  
}
