package komorebi.bean.game;

import static komorebi.bean.graphics.Graphics.BUTTON_UP;
import static komorebi.bean.graphics.Graphics.GATE_UP;
import static komorebi.bean.graphics.Graphics.RED;

import komorebi.bean.editor.PaletteItem;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Graphics;
import komorebi.bean.graphics.Image;

public enum Tile {

  //ROW 1
  BLOCK(Graphics.SQUARES[RED]), BEAN(Graphics.BEAN[RED]), 
  FLAG(Graphics.FLAG), BUTTON(Graphics.BUTTONS[BUTTON_UP][RED]), 
  TURRET_LEFT(Graphics.TURRET), TURRET_RIGHT(Graphics.TURRET, true),
  GATE_VERT(Graphics.GATE[GATE_UP][RED]), 
  GATE_HORIZ(Graphics.GATE[GATE_UP][RED],
      Draw.ROTATE_CLOCKWISE),
  LADDER(Graphics.LADDER[RED]), 
  PISTON_MID(Graphics.PISTON[Graphics.PISTON_MID][RED]),
  TREADMILL_L(Graphics.TREADMILL[Graphics.TREADMILL_L][0]), 
  TREADMILL_C(Graphics.TREADMILL[Graphics.TREADMILL_C][0]), 
  TREADMILL_R(Graphics.TREADMILL[Graphics.TREADMILL_R][0]), 
  PISTON_END(Graphics.PISTON[Graphics.PISTON_END][RED]), 
  SPIKE_L_UP(Graphics.SPIKES[Graphics.SPIKE_L][RED]), 
  SPIKE_C_UP(Graphics.SPIKES[Graphics.SPIKE_C][RED]), 
  SPIKE_R_UP(Graphics.SPIKES[Graphics.SPIKE_R][RED]), 
  SPIKE_ALONE_UP(Graphics.SPIKES[Graphics.SPIKE_ALONE][RED]),
  SPIKE_L_RIGHT(Graphics.SPIKES[Graphics.SPIKE_L][RED], 
      Draw.ROTATE_CLOCKWISE), 
  SPIKE_C_RIGHT(Graphics.SPIKES[Graphics.SPIKE_C][RED], 
      Draw.ROTATE_CLOCKWISE), 
  SPIKE_R_RIGHT(Graphics.SPIKES[Graphics.SPIKE_R][RED], 
      Draw.ROTATE_CLOCKWISE), 
  SPIKE_ALONE_RIGHT(Graphics.SPIKES[Graphics.SPIKE_ALONE][RED], 
      Draw.ROTATE_CLOCKWISE),
  SPIKE_L_DOWN(Graphics.SPIKES[Graphics.SPIKE_L][RED], 
      Draw.ROTATE_180), 
  SPIKE_C_DOWN(Graphics.SPIKES[Graphics.SPIKE_C][RED], 
      Draw.ROTATE_180), 
  SPIKE_R_DOWN(Graphics.SPIKES[Graphics.SPIKE_R][RED], 
      Draw.ROTATE_180), 
  SPIKE_ALONE_DOWN(Graphics.SPIKES[Graphics.SPIKE_ALONE][RED], 
      Draw.ROTATE_180),
  SPIKE_L_LEFT(Graphics.SPIKES[Graphics.SPIKE_L][RED], 
      Draw.ROTATE_COUNTERCLOCKWISE), 
  SPIKE_C_LEFT(Graphics.SPIKES[Graphics.SPIKE_C][RED], 
      Draw.ROTATE_COUNTERCLOCKWISE), 
  SPIKE_R_LEFT(Graphics.SPIKES[Graphics.SPIKE_R][RED], 
      Draw.ROTATE_COUNTERCLOCKWISE), 
  SPIKE_ALONE_LEFT(Graphics.SPIKES[Graphics.SPIKE_ALONE][RED], 
      Draw.ROTATE_COUNTERCLOCKWISE),
  PLATFORM(Graphics.PLATFORM[RED]),
  BLANK(null);
  
  private Image image;
  private boolean flip;
  private int rot;
  
  private Tile(Image image)
  {
    this(image, Draw.ROTATE_NONE, false);
  }
  
  private Tile(Image image, boolean flip)
  {
    this(image, Draw.ROTATE_NONE, flip);
  }
  
  private Tile(Image image, int rot)
  {
    this(image, rot, false);
  }
  
  private Tile(Image image, int rot, boolean flip)
  {
    this.image = image;
    this.rot = rot;
    this.flip = flip;
  }
  
  private static int color = 0;
  
  private static Tile[] coloredObjects = {BLOCK, BEAN, BUTTON, 
      GATE_HORIZ, GATE_VERT, LADDER, PISTON_MID, PISTON_END, 
      SPIKE_L_UP, SPIKE_C_UP, SPIKE_R_UP, SPIKE_ALONE_UP, 
      SPIKE_L_DOWN, SPIKE_C_DOWN, SPIKE_R_DOWN, SPIKE_ALONE_DOWN, 
      SPIKE_L_RIGHT, SPIKE_C_RIGHT, SPIKE_R_RIGHT, SPIKE_ALONE_RIGHT, 
      SPIKE_L_LEFT, SPIKE_C_LEFT, SPIKE_R_LEFT, SPIKE_ALONE_LEFT, 
      PLATFORM};

  public static void nextColor()
  {
    color++;
    if (color > Graphics.PINK)
      color = Graphics.RED;
    
    updateColors();
  }


  public static void updateColors()
  { 
    for (Tile object: coloredObjects)
    {
      switch (object)
      {
        case BLOCK:
          object.setImage(Graphics.SQUARES[color]);
          break;
        case BEAN:
          object.setImage(Graphics.BEAN[color]);
          break;
        case BUTTON:
          object.setImage(Graphics.BUTTONS[BUTTON_UP][color]);
          break;
        case GATE_HORIZ: case GATE_VERT:
          object.setImage(Graphics.GATE[GATE_UP][color]);
          break;
        case LADDER:
          object.setImage(Graphics.LADDER[color]);
          break;
        case PISTON_MID:
          object.setImage(Graphics.PISTON[Graphics.PISTON_MID][color]);
          break;
        case PISTON_END:
          object.setImage(Graphics.PISTON[Graphics.PISTON_END][color]);
          break;
        case SPIKE_L_UP: case SPIKE_L_DOWN: case SPIKE_L_LEFT:
        case SPIKE_L_RIGHT:
          object.setImage(Graphics.SPIKES[Graphics.SPIKE_L][color]);
          break;
        case SPIKE_C_UP: case SPIKE_C_DOWN: case SPIKE_C_LEFT:
        case SPIKE_C_RIGHT:
          object.setImage(Graphics.SPIKES[Graphics.SPIKE_C][color]);
          break;
        case SPIKE_R_UP: case SPIKE_R_DOWN: case SPIKE_R_LEFT:
        case SPIKE_R_RIGHT:
          object.setImage(Graphics.SPIKES[Graphics.SPIKE_R][color]);
          break;
        case SPIKE_ALONE_UP: case SPIKE_ALONE_DOWN: case SPIKE_ALONE_LEFT:
        case SPIKE_ALONE_RIGHT:          
          object.setImage(Graphics.SPIKES[Graphics.SPIKE_ALONE][color]);
          break;
        case PLATFORM:
          object.setImage(Graphics.PLATFORM[color]);
          break;
        default: break;
      }
    }
  }
  
  public void setImage(Image image)
  {
    this.image = image;
  }

  public Image getImage()
  {
    return image;
  }
  
  public boolean isFlipped()
  {
    return flip;
  }
  
  public int getRotation()
  {
    return rot;
  }
  
  public static int getColor()
  {
    return color;
  }
}
