/*
 * Draw.java    June 2, 2016, 7:42:38 PM
 */

package komorebi.bean.graphics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import komorebi.bean.graphics.Transformation.TransformationSeries;
import komorebi.bean.graphics.mod.loop.ImageSplitter;

/**
 * Draws stuff. :D
 *
 * @author Aaron Roy
 */
public class Draw {

  /** To ensure rotations can only happen in multiples of 90 degrees.*/
  private static final int RIGHT_ANGLE = 90;

  /** Holds all of the textures for this class.*/
  private static Texture[] tex = new Texture[3];


  public static final int SPREADSHEET = 0,
      TITLE_SCREEN = 1, MISCELLANY = 2;

  /**
   * Loads textures
   * 
   * <p><u>Current:</u><br>
   *    0: Terra<br>
   *    1: Pokemon Tiles<br>
   *    2: Selector/Grid<br>
   *    3: Ash Ketchum<br>
   *    4: Ness<br>
   *    5: Earthbound Font<br>
   *    6: Textbox<br>
   *    7: Picker<br>
   *    8: Fader<br>
   *    9: Clyde's Tiles<br>
   *    10: Miscellaneous Items<br>
   *    11: Fillers for Project Soul<br>
   */
  public static void loadTextures() {
    try {

      tex[0] = TextureLoader.getTexture("PNG", new FileInputStream(
          new File("res/gfx/bean-spreadsheet.png")));
      tex[1] = TextureLoader.getTexture("PNG", new FileInputStream(
          new File("res/gfx/title-screen.png")));
      tex[2] = TextureLoader.getTexture("PNG", new FileInputStream(
          new File("res/gfx/misc.png")));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Draws a sprite on the screen from the specified image, with rotation.
   * 
   * @param x the X position on the screen, starting from the left           
   * @param y the Y position on the screen, starting from the <i>bottom</i>  
   * @param sx the width                                                     
   * @param sy the height                                                    
   * @param texx X position on the picture, starting from the left           
   * @param texy Y position on the picture, starting from the <i>top</i>     
   * @param texsx end X position on the picture, starting from the left      
   * @param texsy end Y position on the picture, starting from the <i>top</i>
   * @param angle the rotation of the tile / 90 degrees
   * @param texID see {@link Draw#loadTextures() loadTextures}
   */
  private static void rect(float x, float y, float sx, float sy, int texx, 
      int texy, int texsx, int texsy, int texID, 
      Transformation[] transformations) {
    int actualX = (int) x;
    int actualY = (int) y;
    
    TransformationSeries series = 
        Transformation.consolidate(transformations);
    
    if (series.flipX())
    {
      int temp = texx;
      texx = texsx;
      texsx = temp;
    }
    
    if (series.flipY())
    { 
      int temp = texy;
      texy = texsy;
      texsy = temp;
    }
    
    switch (series.getAngle())
    {
      case 3:
        actualY += (int) sx;
        break;
      case 1:
        actualX += (int) sy;
        break;
      case 2:
        actualX += (int) sx;
        actualY += (int) sy;
        break;
      default: break;  
    }

    glPushMatrix();
    {
      int imgX, imgY;
      try
      {
        imgX = tex[texID].getImageWidth();
      } catch (NullPointerException e)
      {
        loadTextures();
        imgX = tex[texID].getImageWidth();
      } finally {
        imgY = tex[texID].getImageHeight();
      }

      glTranslatef((int) actualX, (int) actualY, 0);
      glRotatef(series.getAngle() * RIGHT_ANGLE, 0.0f, 0.0f, 1.0f);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

      tex[texID].bind();

      glBegin(GL_QUADS);
      {
        glTexCoord2f((float) texx / imgX, (float) texsy / imgY);
        glVertex2f(0, 0);

        glTexCoord2f((float) texx / imgX, (float) texy / imgY);
        glVertex2f(0, (int) sy);

        glTexCoord2f((float) texsx / imgX, (float) texy / imgY);
        glVertex2f((int) sx, (int) sy);

        glTexCoord2f((float) texsx / imgX, (float) texsy / imgY);
        glVertex2f((int) sx, 0);
      }
      glEnd();
    }

    glPopMatrix();

  }
  
  public static void rect(float x, float y, float sx, float sy, int texx, 
      int texy, int texsx, int texsy, int texID)
  {
    rect(x, y, sx, sy, texx, texy, texsx, texsy, texID,
        new Transformation[0]);
  }
  
  public static void draw(Image image, float x, float y, float scale,
      Transformation...alterations)
  {
    rect(x, y, image.getWidth()*scale, image.getHeight()*scale,
        image.getTexX(), image.getTexY(),
        image.getTexX()+image.getWidth(),
        image.getTexY()+image.getHeight(), image.getTexture(),
        alterations);
  }
  
  public static void draw(Image image, float x, float y, Transformation...alterations)
  {
    draw(image, x, y, 1, alterations);
  }

  public static void drawMod(Image image, float x, float y, 
      Dimension modSpace, Transformation...alterations)
  { 
    ImageSplitter splitter = new ImageSplitter(image,
        x, y, modSpace, alterations);
    
    Image[] images = splitter.getImages();
    Rectangle[] rects = splitter.getRectangles();
    
    for (int i = 0; i < images.length; i++)
    {
      Draw.draw(images[i], rects[i].x, 
          rects[i].y, alterations);
    }
  }
  
  public static void drawMod(Frame frame, float x, float y, 
      Dimension modSpace)
  {
    drawMod(frame.getImage(), x, y, modSpace, frame.getAlterations());
  }
  
  public static void draw(Frame frame, float x, float y)
  {
    draw(frame.getImage(), x, y, frame.getAlterations());
  }

  public static void fill(Image image, float x, float y, int sx, int sy)
  {
    rect(x, y, sx, sy, image.getTexX(), image.getTexY(), 
        image.getTexX()+image.getWidth(),
        image.getTexY()+image.getHeight(), image.getTexture(),
        new Transformation[0]);
  }

}
