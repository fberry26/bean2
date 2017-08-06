package komorebi.bean.graphics;

public class Frame {

  private Image image;
  private Transformation[] alterations;
  
  public Frame(Image image, Transformation...alterations)
  {
    this.image = image;
    this.alterations = alterations;
  }
  
  public Image getImage()
  {
    return image;
  }
  
  public Transformation[] getAlterations()
  {
    return alterations;
  }
}
