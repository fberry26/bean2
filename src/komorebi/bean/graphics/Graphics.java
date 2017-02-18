package komorebi.bean.graphics;

public final class Graphics {

  /* SCREENS */
  public static final Image TITLE_SCREEN = new Image(0,0, 320, 256,
      Draw.TITLE_SCREEN);
  public static final Image BLANK_TITLE_SCREEN = new Image(0, 256,
      320, 256, Draw.TITLE_SCREEN);
  public static final Image GREY_BACKGROUND = new Image(0, 0, 1, 1,
      Draw.MISCELLANY);

  
  /* ENTITIES */
  public static final int TREADMILL_L = 0, TREADMILL_C = 1,
      TREADMILL_R = 2;
  public static final Image[][] TREADMILL = new Image[3][8];
  
  
  public static final int RED = 0, ORANGE = 1, YELLOW = 2, 
      GREEN = 3, BLUE = 4, PURPLE = 5, PINK = 6;
  public static final Image[] SQUARES = new Image[7];
  public static final Image[] BEAN = new Image[7];
  
  public static final int SPIKE_C = 0, SPIKE_ALONE = 1,
      SPIKE_R = 2, SPIKE_L = 3;
  public static final Image[][] SPIKES = new Image[4][7];
  
  public static final int BUTTON_UP = 0, BUTTON_DEPRESS = 1;
  public static final Image[][] BUTTONS = new Image[2][7];
  
  
  public static final int BULLET_FLY = 0, BULLET_BREAK_1 = 1,
      BULLET_BREAK_2 = 2;
  public static final Image[][] BULLETS = new Image[3][7];
  
  public static final int PISTON_MID = 0, PISTON_END = 1;
  
  public static final Image[][] PISTON = new Image[2][7];
  
  public static final Image[] PLATFORM = new Image[7];
  
  public static final Image[] LADDER = new Image[7];
  
  public static final int GATE_UP = 0, GATE_BREAK_1 = 1,
      GATE_BREAK_2 = 2, GATE_BREAK_3 = 3;
  public static final Image GATE[][] = new Image[4][7];
  
  public static final Image FLAG = new Image(84, 0, 12, 16, 
      Draw.SPREADSHEET);
  public static final Image TURRET = new Image(108, 0, 16, 16,
      Draw.SPREADSHEET);
  public static final Image ARROW = new Image(208, 32, 10, 10, 
      Draw.SPREADSHEET);

  
  /* EDITOR */
  public static final Image GRID = new Image(0, 16, 16, 16, 
      Draw.MISCELLANY);
  public static final Image EDITOR_BUTTONS = new Image(16, 0, 64, 16,
      Draw.MISCELLANY);
  public static final Image BUTTON_HOVER_SQUARE = new Image(80, 0, 32, 32,
      Draw.MISCELLANY);
  public static final Image GREEN_PALETTE_SELECTION = new Image(1,0,1,1,
      Draw.MISCELLANY);
  public static final Image BLUE_SELECTED_FUNCTION = new Image(2,0,1,1,
      Draw.MISCELLANY);
  
  public static final Image PENCIL = new Image(128, 0, 16, 16, 
      Draw.MISCELLANY);
  public static final Image MOUSE_POINTER = new Image(144, 0, 16, 16,
      Draw.MISCELLANY);
  public static final Image DELETE_X = new Image(160, 0, 16, 16,
      Draw.MISCELLANY);
  public static final Image EXPAND_ARROW = new Image(112, 16, 9, 7, 
      Draw.MISCELLANY);
  
  public static void initialize()
  {
    for (int i = 0; i < TREADMILL.length; i++)
    {
      for (int j = 0; j < TREADMILL[i].length; j++)
      {
        TREADMILL[i][j] = new Image(128+i*16,16+16*j, 16, 16,
            Draw.SPREADSHEET);
      }
    }
    
    for (int i = RED; i <= PINK; i++)
    {
      BEAN[i] = new Image(12*i, 0, 12, 16, Draw.SPREADSHEET);
      SQUARES[i] = new Image(0, 16 + 16*i, 16, 16, Draw.SPREADSHEET);
      PLATFORM[i] = new Image(190 + 50*i, 19, 20, 8, Draw.SPREADSHEET);
      LADDER[i] = new Image(176 + 16*i, 128, 16, 16, Draw.SPREADSHEET);
      
      for (int j = SPIKE_C; j <= SPIKE_L; j++)
      {
        SPIKES[j][i] = new Image(64+j*16, 16+i*16, 16, 16, 
            Draw.SPREADSHEET);
      }
      
      for (int j = BUTTON_UP; j <= BUTTON_DEPRESS; j++)
      {
        BUTTONS[j][i] = new Image(124 + 56*j + 8*i, 12, 8, 4,
            Draw.SPREADSHEET);
      }
      
      for (int j = 0 ; j < GATE.length; j++)
      {
        GATE[j][i] = new Image(176 + 50*i, 16 + j*16, 4 + j,
            16, Draw.SPREADSHEET);
      }
      
      for (int j = PISTON_MID; j <= PISTON_END; j++)
      {
        PISTON[j][i] = new Image(192 + 50*i, 32 + 16*j, 16, 16,
            Draw.SPREADSHEET);
      }
      
      BULLETS[BULLET_FLY][i] = new Image(126 + 8*i, 0, 4, 2,
          Draw.SPREADSHEET);
      BULLETS[BULLET_BREAK_1][i] = new Image(127 + 8*i, 3, 3,
          7, Draw.SPREADSHEET);
      BULLETS[BULLET_BREAK_2][i] = new Image(183 + 8*i, 2, 3,
          9, Draw.SPREADSHEET);
    }
  }
}
