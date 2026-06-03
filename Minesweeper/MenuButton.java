import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuButton extends Button
{
    //initalize constants for possible menu buttons 
    public static final String START = "Start";
    public static final String QUIT = "Quit";
    public static final String RESTART = "Restart";
    public static final String SOUND = "Sound";

    //tracks if sound is on or off
    private boolean soundOn;
    
    /**
     * Constructor - creates a menu button with the given label and sets sound
     * to on by default
     */
    public MenuButton(String label){
        super(label);
        soundOn = true;
        //updateImage();
    }
    
    /**
     * called every frame by greenfoot
     * checks for clicks and updates the image
     */
    public void act()
    {
        super.act();
        if(isClicked()){
            handleClick();
        }
        updateImage();
    }
    
    /**
     * handles what happens when the button is clicked
     * depending on what type of button it is
     */
    private void handleClick(){
        if(label.equals(START)){
            //switch to main game world
            Greenfoot.setWorld(new MyWorld());
        }else if(label.equals(QUIT)){
            //stop the game
            Greenfoot.stop();
        }else if(label.equals(RESTART)){
            //restart a new world 
            Greenfoot.setWorld(new MyWorld());
        }else if (label.equals(SOUND)){
            //flip sound between on and off
            soundOn = !soundOn;
        }
    }
    
    /**
     * sets the image of this button based on its label
     */
    private void updateImage(){
        String displayLabel;
        if(label.equals(SOUND)){
            if(soundOn){
                //setImage("");
            }else{
                //setImage("");
            }
        }else if(label.equals(START)){
            //setImage("start.png");
        }else if(label.equals(QUIT)){
            //setImage("quit.png");
        }else if(label.equals(RESTART)){
            //setImage("restart.png");
        }
    }
    
    //returns whether sound is currently on, used by other classes 
    public boolean isSoundOn(){
        return soundOn;
    }
}
