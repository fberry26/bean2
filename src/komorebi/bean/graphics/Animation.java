/*
 * Animation.java           Feb 13, 2016
 */

package komorebi.bean.graphics;

/**
 * Represents a set of pictures
 * 
 * @author Aaron Roy
 * @author Andrew Faulkenberry
 */
public class Animation {

  private int frames;
  private int time;
  private int counter;

  private int scale;

  private Image[] images;
  private int[] rot;
  private boolean[] flipped;
  private int currFrame;
  private int cAddFrame;
  private boolean playing = true;
  private boolean onlyOnce = false;  


  public Animation(int f, int t, boolean loop,
      int scale){

    this.scale = scale;
    onlyOnce = !loop;

    frames = f;
    images = new Image[frames];
    rot = new int[frames];
    flipped = new boolean[frames];
    time = t;

  }



  /**
   * Creates a playable animation
   * 
   * @param f Max number of frames
   * @param t Time till next frame in frames
   * @param sx size x for the animation 
   *             *used to calculate other tex coordinates too
   * @param sy size y for the animation 
   *             *used to calculate other tex coordinates too
   * @param id The Texture ID
   */
  public Animation(int f, int t){
    this(f, t, true, 1);
  }

  public Animation(int f, int t, int scale){
    this(f, t, true, scale);
  }


  /**
   * Creates a playable animation
   * 
   * @param f Max number of frames
   * @param t Time till next frame in frames
   * @param id The Texture ID
   * @param loop Whether the animation should play on loop
   */
  public Animation(int f, int t, boolean loop){
    this(f, t, loop, 1);
  }



  public void add(Image image, int rot, boolean flip)
  {
    this.images[cAddFrame] = image;
    this.rot[cAddFrame] = rot;
    flipped[cAddFrame] = flip;
    cAddFrame++;
  }

  public void add(Image image, int rot)
  {
    add(image, rot, false);
  }

  public void add(Image image, boolean flip)
  {
    add(image, Draw.ROTATE_NONE, flip);
  }

  public void add(Image image)
  {
    add(image, Draw.ROTATE_NONE, false);
  }
  
  public void update()
  {
    increment();
  }


  /**
   * Plays the animation at the specified location
   * 
   * @param x x location for the animation
   * @param y y location for the animation
   */
  public void render(float x, float y){

    renderNoIncrement(x, y);
    increment();
  }
  
  public void renderNoIncrement(float x, float y)
  {
    Draw.draw(images[currFrame], x, y, scale, rot[currFrame], 
        flipped[currFrame]);
  }

  /*
   * Increments the frame counter
   */
  private void increment()
  {
    if(playing){
      counter++;
      if(counter > time){
        counter = 0;
        currFrame++;
        if(currFrame >= frames){
          if (onlyOnce)
          {
            hStop();
          } else
          {
            currFrame = 0;
          }
        }
      }
    }
  }


  /**
   * Stops the animation, keeping the frame
   */
  public void stop(){
    playing = false;
  }

  /**
   * Stops the animation and resets the frame
   */
  public void hStop(){
    playing  = false;
    currFrame = 0;
  }


  /**
   * Resumes the animation
   */
  public void resume(){
    playing  = true;
  }
  /**
   * Sets the speed of the animation
   * @param speed speed in frames
   */
  public void setSpeed(int speed){
    time = speed;
  }

  /**
   * @return true if the animation is currently playing, else false
   */
  public boolean playing()
  {
    return playing;
  }

  /**
   * @return True if the animation is on its last frame, else false
   */
  public boolean lastFrame()
  {
    return (currFrame == frames - 1);
  }

  public void reset()
  {
    currFrame = 0;
  }

  public int currentFrame()
  {
    return currFrame;
  }
}
