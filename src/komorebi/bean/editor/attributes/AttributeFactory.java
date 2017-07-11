package komorebi.bean.editor.attributes;

import java.awt.Point;

public class AttributeFactory {

  
  public static ChoiceAttribute createFastSlowAttribute(Point point)
  {
    return createChoiceAttribute(point, "Speed", "Fast", "Slow");
  }
  
  public static ChoiceAttribute createLeftRightAttribute(Point point)
  {
    return createChoiceAttribute(point, "Direction", "Left", "Right");
  }
  
  private static ChoiceAttribute createChoiceAttribute(Point point, String description,
       String...choices)
  {
    return new ChoiceAttribute(description, choices, point);
  }
}
