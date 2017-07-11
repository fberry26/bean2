package komorebi.bean.text;

public abstract class Font {

  public float scale;
  
  public Font(float scale)
  {
    this.scale = scale;
  }
  
  //TODO Fix class hierarchy
  public abstract int getTexX(char c);
  public abstract int getTexY(char c);
  public abstract int getTexSy(char c);
  public abstract int getLength(char c);
  public abstract int getTexture();
  
  public float getFontPoint()
  {
    return scale;
  }
  
  public float getScale()
  {
    return scale; 
  }
}
