import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button is the superclass for all mouse-clicking elements
 * 
 * @Cyndi Zou
 * @version (a version number or a date)
 */
public class Button extends Actor
{
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
