package komorebi.bean.editor.attributes;

import java.awt.Point;

import komorebi.bean.text.TextHandler;

public class ChoiceAttribute extends Attribute {

  private static final int INDENT = 10;
  
  private RadioButtonGroup buttons;
  private Choice[] choices;
 
  private TextHandler text;
  
  public ChoiceAttribute(String description, String[] strings,
      Point begin)
  {
    text = new TextHandler();
    
    choices = new Choice[strings.length];
    RadioButton[] rbuttns = new RadioButton[strings.length];
    
    int x = begin.x;
    int y = begin.y;
    
    text.write(description, x, y, AttributeWindow.TEXT_FONT);
    x+=INDENT;
    y-=10;
        
    for (int i = 0; i < strings.length; i++)
    {
      rbuttns[i] = new RadioButton(x, y, i==0);
      choices[i] = new Choice(strings[i], rbuttns[i]);
      
      text.write(strings[i], x+12, y, AttributeWindow.TEXT_FONT);
      
      y-=10;      
    }
    
    x-=INDENT;
    
    nextAttribute = new Point(x, y);
    
    buttons = new RadioButtonGroup(rbuttns);
  }
  
  public void update()
  {
    buttons.update();
  }
    
  public void render() {
    text.render();
    buttons.render();
  }

}
