package komorebi.bean.graphics;

import java.awt.Point;
import java.util.ArrayList;

import komorebi.bean.editor.Editor;
import komorebi.bean.editor.objects.platform.DirectionChange;
import komorebi.bean.editor.objects.platform.ModPath;
import komorebi.bean.editor.objects.platform.PathDirection;

public class PlatformPath {

  private static final Image HEAD_SHORT = new Image(174, 17, 16, 16,
      Draw.MISCELLANY);
  private static final Image HEAD_LONG = new Image(166, 17, 24, 16,
      Draw.MISCELLANY);
  private static final Image STRAIGHT = new Image(158, 17, 16, 16,
      Draw.MISCELLANY);
  private static final Image TURN = new Image(142, 17, 16, 16,
      Draw.MISCELLANY);

  private static final Frame RIGHT_HEAD = new Frame(HEAD_LONG);
  private static final Frame LEFT_HEAD = new Frame(HEAD_LONG, Transformation.ROTATE_180);
  private static final Frame TOP_HEAD = new Frame(HEAD_SHORT, 
      Transformation.ROTATE_COUNTERCLOCKWISE);
  private static final Frame BOTTOM_HEAD = new Frame(HEAD_SHORT, 
      Transformation.ROTATE_CLOCKWISE);

  private static final Frame TURN_TOP_LEFT = new Frame(TURN);
  private static final Frame TURN_BOTTOM_LEFT = new Frame(TURN,
      Transformation.ROTATE_COUNTERCLOCKWISE);
  private static final Frame TURN_TOP_RIGHT = new Frame(TURN, 
      Transformation.ROTATE_CLOCKWISE);
  private static final Frame TURN_BOTTOM_RIGHT = new Frame(TURN,
      Transformation.ROTATE_180);

  private static final Frame STRAIGHT_HORIZ = new Frame(STRAIGHT);
  private static final Frame STRAIGHT_VERT = new Frame(STRAIGHT, 
      Transformation.ROTATE_CLOCKWISE);

  private ModPath path;

  private static enum ArrowHead
  {
    LEFT(LEFT_HEAD, PathDirection.LEFT), 
    RIGHT(RIGHT_HEAD, PathDirection.RIGHT), 
    UP(TOP_HEAD, PathDirection.UP), 
    DOWN(BOTTOM_HEAD, PathDirection.DOWN);

    private PathDirection direction;
    private Frame image;

    private ArrowHead(Frame image, PathDirection direction)
    {
      this.image = image;
      this.direction = direction;
    }

    public PathDirection getDirection()
    {
      return direction;
    }

    public Frame getImage()
    {
      return image;
    }

    public static Frame getArrowHead(PathDirection dir)
    {
      for (ArrowHead arrow: values())
      {
        if (arrow.getDirection() == dir)
        {
          return arrow.getImage();
        }
      }

      throw new RuntimeException("No arrow head for " + dir);
    }
  }
  
  private static enum ArrowStraight
  {
    VERTICAL(STRAIGHT_VERT, PathDirection.UP, PathDirection.DOWN),
    HORIZONTAL(STRAIGHT_HORIZ, PathDirection.LEFT, PathDirection.RIGHT);
    
    private Frame image;
    private PathDirection[] directions;
    private ArrowStraight(Frame image, PathDirection...directions)
    {
      this.image = image;
      this.directions = directions;
    }
    
    public boolean matches(PathDirection dir)
    {
      for (PathDirection direction: directions)
      {
        if (direction == dir)
        {
          return true;
        }
      }
      
      return false;
    }
    
    public Frame getImage()
    {
      return image;
    }
    
    public static Frame getArrowStraight(PathDirection dir)
    {
      for (ArrowStraight arrow: values())
      {
        if (arrow.matches(dir))
        {
          return arrow.getImage();
        }
      }
      
      throw new RuntimeException("No arrow straight for " + dir);
    }
  }
  
  private static enum ArrowTurn
  {
    TOP_LEFT(TURN_TOP_LEFT, 
        new DirectionChange(PathDirection.UP, PathDirection.RIGHT),
        new DirectionChange(PathDirection.LEFT, PathDirection.DOWN)),
    TOP_RIGHT(TURN_TOP_RIGHT,
        new DirectionChange(PathDirection.UP, PathDirection.LEFT),
        new DirectionChange(PathDirection.RIGHT, PathDirection.DOWN)),
    BOTTOM_LEFT(TURN_BOTTOM_LEFT,
        new DirectionChange(PathDirection.DOWN, PathDirection.RIGHT),
        new DirectionChange(PathDirection.LEFT, PathDirection.UP)),
    BOTTOM_RIGHT(TURN_BOTTOM_RIGHT,
        new DirectionChange(PathDirection.DOWN, PathDirection.LEFT),
        new DirectionChange(PathDirection.RIGHT, PathDirection.UP));
    
    private Frame image;
    private DirectionChange[] changes;
    
    private ArrowTurn(Frame image, DirectionChange... changes)
    {
      this.image = image;
      this.changes = changes;
    }
    
    public boolean matches(DirectionChange args)
    {      
      for (DirectionChange change: changes)
      {
        if (change.equals(args))
        {
          return true;
        }
      }
      
      return false;
    }
    
    public Frame getImage()
    {
      return image;
    }
    
    public static Frame getArrowTurn(PathDirection from,
        PathDirection to)
    {
      DirectionChange change = new DirectionChange(from, to);
      
      for (ArrowTurn turn: values())
      {
        if (turn.matches(change))
          return turn.getImage();
      }
      
      throw new RuntimeException("No turn from " + from + " to " + to);
    }
  }

  public PlatformPath(ModPath path)
  {
    this.path = path;
  }

  public void render()
  {
    ArrayList<PathDirection> directions = 
        path.getDirections();
    
    PathDirection first;

    if (directions.size() == 0) 
    {
      first = PathDirection.RIGHT; 
    } else
    {
      first = directions.get(0);
    }
    Frame arrowHead = ArrowHead.getArrowHead(first.getOpposite());
    
    Point drawAt = locationOfFirstArrow(first.getOpposite());
    Draw.drawMod(arrowHead, drawAt.x, drawAt.y, Editor.MODSPACE);
    
    
    if (directions.size() == 0)
    {
      drawAt.translate(8, 0);
    } else
    {
      switch (first)
      {
        case RIGHT:
          translate(drawAt, first, 24, 0);
          break;
        case LEFT: case UP: case DOWN:
          translate(drawAt, first, 16, 16);
          break;
        default: break;
      }
    }


    PathDirection wasMovingIn, isMovingIn = first;
    Frame image;
    
    for (int i = 1; i < directions.size(); i++)
    {
      wasMovingIn = directions.get(i-1);
      isMovingIn = directions.get(i);
            
      if (wasMovingIn == isMovingIn)
      {
        image = ArrowStraight.getArrowStraight(isMovingIn);
      } else
      {
        image = ArrowTurn.getArrowTurn(wasMovingIn, isMovingIn);
      }
      
      Draw.drawMod(image, drawAt.x, drawAt.y, Editor.MODSPACE);
      
      translate(drawAt, isMovingIn, 16,
          16);
    }
    
    if (isMovingIn == PathDirection.LEFT)
      drawAt.x -= 8;
    
    Draw.drawMod(ArrowHead.getArrowHead(isMovingIn), drawAt.x, drawAt.y,
        Editor.MODSPACE);

  }

  private Point locationOfFirstArrow(PathDirection arrow)
  {
    int x = path.x()*16;
    int y = path.y()*16;
    
    switch (arrow)
    {
      case DOWN:
        x += 8;
        break;
      case RIGHT:
        x += (path.getComponentRectangles()[0].width*16 - 24);
        break;
      case UP:
        x += 8;
        y += (path.getComponentRectangles()[0].height*16 - 16);
        break;
      default: break;
    }
    
    return new Point(x, y);
  }
  
  private void translate(Point point, PathDirection dir, int width,
      int height)
  {
    switch (dir)
    {
      case DOWN:
        point.translate(0, -height);
        break;
      case LEFT:
        point.translate(-width, 0);
        break;
      case RIGHT:
        point.translate(width, 0);
        break;
      case UP:
        point.translate(0, height);
        break;
      default: break;
      
    }
  }
}
