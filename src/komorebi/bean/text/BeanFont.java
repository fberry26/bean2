package komorebi.bean.text;

import komorebi.bean.graphics.Draw;

public class BeanFont extends Font {

  public BeanFont(float scale) {
    super(scale);
  }

  @Override
  public int getTexX(char c) {
    switch (c)
    {
      case 'a': case 'A': case 'j': case 'J':
      case 's': case 'S': case '2':
        return 0;
      case 'b': case 'B': case 'k': case 'K':
      case 't': case 'T': case '3':
        return 13;
      case 'c': case 'C': case 'l': case 'L':
      case 'u': case 'U': case '4':
        return 26;
      case 'd': case 'D': case 'm': case 'M':
      case 'v': case 'V': case '5':
        return 38;
      case 'e': case 'E': case 'n': case 'N':
      case 'w': case 'W': case '6':
        return 51;
      case 'f': case 'F': case 'o': case 'O':
      case 'x': case 'X': case '7':
        return 63;
      case 'g': case 'G': case 'p': case 'P':
      case 'y': case 'Y': case '8':
        return 75;
      case 'h': case 'H': case 'q': case 'Q':
      case 'z': case 'Z': case '9':
        return 88;
      case 'i': case 'I': case 'r': case 'R':
      case '1': case '0':
        return 100;
    }
    
    return 112;
  }

  @Override
  public int getTexY(char c) {
    switch (c)
    {
      case 'a': case 'A': case 'b': case 'B':
      case 'c': case 'C': case 'd': case 'D':
      case 'e': case 'E': case 'f': case 'F':
      case 'g': case 'G': case 'h': case 'H':
      case 'i': case 'I': 
        return 128;
      case 'j': case 'J': case 'k': case 'K':
      case 'l': case 'L': case 'm': case 'M':
      case 'n': case 'N': case 'o': case 'O':
      case 'p': case 'P': case 'q': case 'Q':
      case 'r': case 'R': 
        return 145;
      case 's': case 'S': case 't': case 'T':
      case 'u': case 'U': case 'v': case 'V':
      case 'w': case 'W': case 'x': case 'X':
      case 'y': case 'Y': case 'z': case 'Z':
      case '1': 
        return 162;
      case '2': case '3': case '4': case '5': 
      case '6': case '7': case '8': case '9': 
      case '0':
        return 179;
    }

    return 191;
  }

  @Override
  public int getTexSy(char c) {
    return 12;
  }

  @Override
  public int getTexture() {
    return Draw.SPREADSHEET;
  }

  @Override
  public int getLength(char c) {
    switch (c)
    {
      case ' ':
        return 4;
      case '1':
        return 5;
      case 'c': case 'C': case 'e': case 'E':
      case 'f': case 'F': case 'h': case 'H':
      case 'i': case 'I': case 'l': case 'L': 
      case 'o': case 'O': case 'u': case 'U': 
      case 'v': case 'V': case 'x': case 'X': 
      case 'y': case 'Y': case 'z': case 'Z': 
      case '2': case '3': case '5': case '7': 
      case '0': 
        return 7;
      case 'a': case 'A': case 'b': case 'B':
      case 'd': case 'D': case 'g': case 'G':
      case 'j': case 'J': case 'k': case 'K': 
      case 'n': case 'N': case 'p': case 'P': 
      case 'q': case 'Q': case 'r': case 'R': 
      case 's': case 'S': case '4': case '6': 
      case '8': case '9':
        return 8;
      case 'm': case 'M': case 't': case 'T': 
      case 'w': case 'W':
        return 9;
    }
    
    return 0;

  }

}
