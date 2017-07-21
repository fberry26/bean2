package komorebi.bean.editor.attributes.buttonlink;

public class SatisfiedLink extends Link {

  protected SatisfiedLink(Linkable linked, int x, int y,
      ClickButton button) {
    super(linked, linked.getName(), x, y, button);
  }

}
