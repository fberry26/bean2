package komorebi.bean.graphics;

public class Image {

  private int texx, texy, width, height, texture;
  
  /**
   * Constructs an object representing a portion of a texture
   * 
   * @param texx The x of the top left corner of the image on the texture
   * @param texy The y of the top left corner of the image on the texture
   * @param width The width of the image on the texture
   * @param height The height of the image on the texture
   * @param texture The texture from which the image should be grabbed
   */
  public Image(int texx, int texy, int width, int height, int texture)
  {
    this.texx = texx;
    this.texy = texy;
    this.width = width;
    this.height = height;
    this.texture = texture;
  }
  
  public int getTexX()
  {
    return texx;
  }
  
  public int getTexY()
  {
    return texy;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getTexture()
  {
    return texture;
  }
  
  public String toString()
  {
    return texx + ", " + texy + ", " + width + ", " + height;
  }
}
