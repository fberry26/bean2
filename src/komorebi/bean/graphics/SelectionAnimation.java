package komorebi.bean.graphics;

public class SelectionAnimation {

  private int minX, minY, maxX, maxY;

  private static final int SPEED = 8;

  private Image row16 = new Image(0, 48, 16, 1, Draw.MISCELLANY);
  private Image row14MoreBlack = new Image(15, 49, 1, 14, Draw.MISCELLANY);
  private Image row14MoreWhite = new Image(16, 49, 1, 14, Draw.MISCELLANY);

  private Animation blackFirstHoriz = new Animation(2, SPEED);
  private Animation whiteFirstHoriz = new Animation(2, SPEED);

  private Animation blackFirstShortVert = new Animation(2, SPEED);
  private Animation whiteFirstShortVert = new Animation(2, SPEED);

  private Animation blackFirstLongVert = new Animation(2, SPEED);
  private Animation whiteFirstLongVert = new Animation(2, SPEED);

  public SelectionAnimation()
  {
    blackFirstHoriz.add(row16);
    blackFirstHoriz.add(row16, true);

    whiteFirstHoriz.add(row16, true);
    whiteFirstHoriz.add(row16);

    blackFirstShortVert.add(row14MoreBlack);
    blackFirstShortVert.add(row14MoreWhite);

    whiteFirstShortVert.add(row14MoreWhite);
    whiteFirstShortVert.add(row14MoreBlack);

    blackFirstLongVert.add(row16, Draw.ROTATE_CLOCKWISE);
    blackFirstLongVert.add(row16, Draw.ROTATE_COUNTERCLOCKWISE);

    whiteFirstLongVert.add(row16, Draw.ROTATE_COUNTERCLOCKWISE);
    whiteFirstLongVert.add(row16, Draw.ROTATE_CLOCKWISE);

  }

  public void setBounds(int minX, int minY,
      int maxX, int maxY)
  {
    this.minX = minX;
    this.minY = minY;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public void render()
  {
    blackFirstHoriz.update();
    blackFirstShortVert.update();
    whiteFirstShortVert.update();
    blackFirstLongVert.update();
    whiteFirstLongVert.update();

    renderTop();
    renderRight();
    renderBottom();
    renderLeft();



  }

  private void renderTop()
  {
    for (int j = minX; j <= maxX; j++)
    {
      blackFirstHoriz.renderNoIncrement(j*16, maxY*16 + 15);
    }
  }
  
  private void renderBottom()
  {
    for (int j = maxX; j >= minX; j--)
    {
      blackFirstHoriz.renderNoIncrement(j*16, minY*16);
    }
  }

  private void renderRight()
  {    
    blackFirstShortVert.renderNoIncrement(maxX*16 + 15, maxY*16 + 1);

    for (int i = maxY - 1; i >= minY + 1; i--)
    {
      whiteFirstLongVert.renderNoIncrement(maxX*16 + 15, i*16 + 1);
    }

    if (minY != maxY)
    {
      whiteFirstLongVert.renderNoIncrement(maxX*16 + 15, minY*16+1);
    }


  }
  
  private void renderLeft()
  {    
    whiteFirstShortVert.renderNoIncrement(minX*16, maxY*16 + 1);

    for (int i = maxY - 1; i >= minY + 1; i--)
    {
      blackFirstLongVert.renderNoIncrement(minX*16, i*16 + 1);
    }

    if (minY != maxY)
    {
      blackFirstLongVert.renderNoIncrement(minX*16, minY*16+1);
    }


  }
  
  public void move(int dx, int dy)
  {
    minX += dx;
    maxX += dx;
    
    minY += dy;
    maxY += dy;
  }
}
