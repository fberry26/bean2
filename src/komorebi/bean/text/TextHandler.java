/**
 * TextHandler.java  Jun 12, 2016, 1:48:14 PM
 */
package komorebi.bean.text;

import java.util.ArrayList;

import komorebi.bean.graphics.Draw;

/**
 * 
 * @author Aaron Roy
 * @version 
 */
public class TextHandler {

  public static final int SCALE = 16;

  public ArrayList<Word> words;
  private static BeanFont defFont = new BeanFont(1);

  /**
   * Creates a text handler object which will render words
   */
  public TextHandler()
  {
    words = new ArrayList<Word>();
  }

  /**
   * 
   * @param s
   * @param x
   * @param y
   * @param fontPt
   */
  public void write(String s, int x, int y, Font font)
  {
    words.add(new Word(s, x, y, font));
  }

  public void write(String s, int x, int y)
  {
    words.add(new Word(s, x, y, defFont));
  }

  public void writeMindLength(String s, int x, int y, Font font, int maxLength)
  {
    if (pixLengthOf(s, font) < maxLength)
    {
      write(s, x, y, font);
    } else {
      int subIndex = s.length()-1;

      while (pixLengthOf(s.substring(0, subIndex) + "...", font) > maxLength)
      {
        subIndex--;
      }

      write(s.substring(0, subIndex) + "...", x, y, font);
    }
  }

  public void writeMindLength(String s, int x, int y, int maxLength)
  {
    writeMindLength(s, x, y, defFont, maxLength);
  }

  public void writeMindLengthCursor(String s, int x, int y, Font font, int maxLength, 
      int cursor)
  {

  }

  public int pixLengthOf(String str, Font font)
  {
    int num = 0;

    for (int i = 0; i < str.length(); i++)
    {
      num += font.getLength(str.charAt(i))*font.getScale() + 1;
    }

    return num;
  }

  /**
   * Renders the text in a style specified by the text handler's attributess
   */
  public void render()
  {

    for (Word word: words)
    {
      render(word);
    }

  }

  public void render(Word word)
  {    
    int horiz = word.getX();
    int vert = word.getY();
    char[] letters = word.currentParagraph();

    for (int i=0; i < letters.length; i++)
    {
      int texx = word.getFont().getTexX(letters[i]),
          texy = word.getFont().getTexY(letters[i]),
          texsx = word.getFont().getLength(letters[i]),
          texsy = word.getFont().getTexSy(letters[i]);
      
      float scale = word.getFontSize();
          
      
      
      Draw.rect(horiz, vert, texsx * scale, texsy * scale,
          texx, texy, texsx + texx,  texsy + texy,
          word.getFont().getTexture());
      horiz+=texsx*scale+1;


    }
  }




  /**
   * Clears the text handler's memory
   */
  public void clear()
  {
    words.clear();
  }

  public void replace(String erase, String replace)
  {
    for (Word word: words)
    {
      if (word.getString().equals(erase))
      {
        word.setString(replace);
      }
    }
  }

  public void move(String move, int dx, int dy)
  {
    for (Word w: words)
    {
      if (w.getString().equals((move)))
      {
        w.move(dx, dy);
      }
    }
  }


}
