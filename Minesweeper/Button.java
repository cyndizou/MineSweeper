import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  Button Class
 */
public class Button extends Actor
{
    private GreenfootImage image;
    private static final Font buttonFont = new Font ("Comic Sans MS", true, false, 24);
    private static GreenfootSound[] clickSound;
    private static int clickSoundIndex;
    
    public Button (String text, int width){
        drawButton (text, width);
    }
    
    private void drawButton (String text, int width){
        image = new GreenfootImage (width, 48);
        image.setColor (new Color(254, 234, 213));
        image.fill();
        image.setColor (Color.BLACK);
        image.drawRect (0, 0, width-1, 47);
        image.setFont (buttonFont);
        drawCenteredText (image, text, 32);
        setImage(image);
    }
    
    //make these additions in world
    /**
     *   addObject(new StartButton(), 300, 200);

     *   addObject(new ArrowButton(1, -1), 100, 300);
     *   addObject(new ArrowButton(1, 1), 200, 300);
        
     *   addObject(new ArrowButton(2, -1), 400, 300);
     *   addObject(new ArrowButton(2, 1), 500, 300);
        
     *   addObject(new SelectButton(), 300, 400);
     */
    
    
    //Remove if needed
    public void act()
    {
        
    }
    
    public void onClick()
    {
        // default: do nothing
    }
    
    
    /**
     * <p> Center a String on a Canvas via the getStringWidth method. This version will center
     *      text on the GreenfootImage passed in, so no x coordinate is required.
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Similar to the method above, except it always centers the text on the whole image
     *    instead of a specified x position. UNTESTED!</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int bottomY){
        canvas.drawString (text, canvas.getWidth()/2 - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }
    
     /**
     * <h3>Mr. Cohen's Text Centering Algorithm</h3>
     * 
     * <p>Get the Width of a String, if it was printed out using the drawString command in a particular
     * Font.</p>
     * <p>There is a performance cost to this, although it is more significant on the Gallery, and 
     * especially on the Gallery when browsed on a mobile device. It is appropriate to call this in the 
     * constructor, and in most cases it is ideal NOT to call it from an act method, especially
     * every act.</p>
     * 
     * <p>In cases where values are pre-determined, it may be ideal to cache the values (save them) so 
     * you don't have to run this repeatedly on the same text. If you do this in the World constructor,
     * there is no performance cost while running.</p>
     * 
     * <h3>Performance & Compatibility:</h3>
     * <ul>
     *  <li> Locally, performance should be sufficient on any moderate computer (average call 0.1-0.2ms on my laptop)</li>
     *  <li> To be compatible with Greenfoot Gallery, removed use of getAwtImage() and replaced with getColorAt() on a GreenfootImage</li>
     *  <li> On Gallery, performance is about 10x slower than locally (4ms on Gallery via Computer). For reference, an act() should be
     *       less than 16.6ms to maintain 60 frames/acts per second. </li>
     *  <li> HUGE performance drop on Gallery via Mobile devices - not sure why, going to ignore for now. (Average update duration 34ms, more
     *       than 2 optimal acts)</li>
     * </ul>
     * 
     * @param font the GreenFoot.Font which is being used to draw text
     * @param text the actual text to be drawn
     * @return int  the width of the String text as draw in Font font, in pixels.
     * 
     * @since June 2021
     * @version December 2021 - Even more Efficiency Improvement - sub 0.06ms per update on setSpeed(100)!
     */
    public static int getStringWidth (Font font, String text){

        // Dividing font size by 1.2 should work for even the widest fonts, as fonts are
        // taller than wide. For example, a 24 point font is usually 24 points tall 
        // height varies by character but even a w or m should be less than 20 wide
        // 24 / 1.2 = 20
        int maxWidth = (int)(text.length() * (font.getSize()/1.20));//1000; 
        int fontSize = font.getSize();
        int marginOfError = fontSize / 6; // how many pixels can be skipped scanning vertically for pixels?
        int checkX;

        GreenfootImage temp = new GreenfootImage (maxWidth, fontSize);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);

        //int testValue = 1000;
        boolean running = true;

        checkX = maxWidth - 1;
        while(running){
            boolean found = false;
            for (int i = fontSize - 1; i >= 0 && !found; i-=marginOfError){

                if (temp.getColorAt(checkX, i).getAlpha() != 0){
                    // This lets me only look at every other pixel on the first run - check back one to the right
                    // when I find a pixel to see if I passed the first pixel or not. This should almost half the 
                    // total calls to getColorAt().
                    if (temp.getColorAt(checkX + 1, i).getAlpha() != 0){
                        checkX++;
                        if (temp.getColorAt(checkX + 1, i).getAlpha() != 0){
                            checkX++;
                        }
                    }
                    found = true;
                }
            }
            if (found){
                return checkX;
            }
            checkX-=3; // shift 3 pixels at a time in my search - above code will make sure I don't miss anything
            if (checkX <= marginOfError)
                running = false;
        }
 
    return 0;
/**
 * Button is the superclass for all mouse-clicking elements
 * 
 * @Cyndi Zou
 * @version (a version number or a date)
 */
public class Button extends Actor{
    //initilaization - identify type of button and whether mouse is hovering over it
    protected String label;
    protected boolean isHovered;
    
    /**
     * Constructor - sets up the button with a label
     * @param label the name of this button
     */
    public Button(String label){
        this.label = label;
        this.isHovered = false;
    }
    
    /**
     * called every frame by Greenfoot, checks hover state
     */
    public void act(){
        checkHover();
    }
    
    /**
     * check if mouse is currently over this button and updates accordingly
     */
    protected void checkHover(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            if(mouse.getActor()==this){
                isHovered = true;
            }else{
                isHovered = false;
            }
        }
    }
    
    /**
     * Returns true if the mouse has clicked this button
     */
    protected boolean isClicked(){
        return Greenfoot.mouseClicked(this);
    }
    
    //returns the name/label of this button
    public String getLabel(){
        return label;
    }
    }
}
