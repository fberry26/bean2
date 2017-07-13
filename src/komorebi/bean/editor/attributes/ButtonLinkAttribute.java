package komorebi.bean.editor.attributes;

import java.awt.Point;
import java.awt.Rectangle;

import komorebi.bean.editor.Editor;
import komorebi.bean.graphics.Draw;
import komorebi.bean.graphics.Image;
import komorebi.bean.text.BeanFont;
import komorebi.bean.text.Font;
import komorebi.bean.text.TextHandler;

public class ButtonLinkAttribute extends Attribute{

  private TextHandler text;
  private static final Font TEXT_FONT = new BeanFont(0.5f);

  private static final Image PLUS_SIGN = new Image(101, 32, 10, 10,
      Draw.MISCELLANY);
  private static final Image CANCEL_SIGN = new Image(111, 32, 10, 10,
      Draw.MISCELLANY);
  private static final Image MINUS_SIGN = new Image(121, 32, 10, 10,
      Draw.MISCELLANY);

  private static final int DISTANCE_FROM_LEFT_OF_TEXT_TO_BUTTON = 50;
  

  private Link link;
  private LinkableObjectPicker linkPicker;

  private boolean pickingObject;

  public ButtonLinkAttribute(Point point)
  {    
    int x = point.x;
    int y = point.y;

    text = new TextHandler();
    text.write("Linked to", x, y, TEXT_FONT);

    y-=10;

    createEmptyLink(x, y);

    y-=10;

    nextAttribute = new Point(x, y);
  }
  
  private void createEmptyLink(int x, int y)
  {
    link = new EmptyLink(x + 5, y, new ClickButton(new Rectangle(
        x + DISTANCE_FROM_LEFT_OF_TEXT_TO_BUTTON, 
        y, 10, 10), PLUS_SIGN) {

      @Override
      public void uponClick() {
        if (!pickingObject)
          pickObject();
        else
          cancelPick();
      }
    });
  }

  @Override
  public void update() {
    
    link.update();
    
    if (pickingObject)
    {
      linkPicker.update();
    }

  }

  private void pickObject()
  {
    link.switchImageOnButtonTo(CANCEL_SIGN);
    Editor.setToolsEnabled(false);

    linkPicker = new LinkableObjectPicker()
    {

      @Override
      public void complete(Linkable linkable) {
        completePick(linkable);
      }

      @Override
      public void cancel() {
        cancelPick();
      }

    };

    pickingObject = true;
  }
  
  private void completePick(Linkable linkable)
  {
    Rectangle buttonArea = link.getButtonArea();
    ClickButton deleteThisPickButton = new ClickButton(buttonArea,
        MINUS_SIGN)
        {

          @Override
          public void uponClick() {
            createEmptyLink(buttonArea.x - DISTANCE_FROM_LEFT_OF_TEXT_TO_BUTTON,
                buttonArea.y);
          }
      
        };
    
    link = new SatisfiedLink(linkable, buttonArea.x - 
        DISTANCE_FROM_LEFT_OF_TEXT_TO_BUTTON + 5, buttonArea.y,
        deleteThisPickButton);
    
    donePicking();
  }

  private void cancelPick()
  {
    link.switchImageOnButtonTo(PLUS_SIGN);
    donePicking();
  }

  private void donePicking()
  {
    pickingObject = false;
    Editor.setToolsEnabled(true);
    
    linkPicker = null;
  }

  @Override
  public void render() {
    text.render();
    link.render();
    
    if (pickingObject)
    {
      linkPicker.render();
    }


  }

}
