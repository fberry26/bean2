package komorebi.bean.editor.save;

public class IDCreator {

  private static char char1 = 'a', char2 = 'a';
  
  public static String next()
  {
    String ret = char1 + "" + char2;
    
    increment();
    
    return ret;
  }
  
  private static void increment()
  {
    char2++;
    
    if (char2 > 'z')
    {
      char2 = 'a';
      char1++;
    }
  }
  
}
