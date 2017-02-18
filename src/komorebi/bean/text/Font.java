package komorebi.bean.text;

public abstract class Font {

  public int scale;
  
  public Font(int scale)
  {
    this.scale = scale;
  }
  
  //TODO Fix class hierarchy
  public abstract int getTexX(char c);
  public abstract int getTexY(char c);
  public abstract int getTexSy(char c);
  public abstract int getLength(char c);
  public abstract int getTexture();
  
  public int getFontPoint()
  {
    return scale;
  }
  
  public int getScale()
  {
    return scale; 
  }
}
