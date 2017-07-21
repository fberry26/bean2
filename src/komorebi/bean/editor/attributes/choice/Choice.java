package komorebi.bean.editor.attributes.choice;

public class Choice {

  private String text;
  private RadioButton button;
  
  public Choice(String text, RadioButton button)
  {
    this.text = text;
    this.button = button;
  }
  
  public String getText()
  {
    return text;
  }
  
  public boolean isChosen()
  {
    return button.isChecked();
  }
}
