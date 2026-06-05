import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allows the user to choose between Timed and Relaxed, ans then transitions the user
 * into the game world where it is either timed or stopwatch. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ModeButton extends Button
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */ that man is acc js gaming beside u 
    
    private boolean timed;
    public ModeButton(boolean timed) {
        this.timed = timed;
        
        if(timed) {
            setImage("timed.png");
        } else {
            setImage("relaxed.png");
        }
    }
    
    
    public void act()
    {
        // Add your action code here.
    }
}
