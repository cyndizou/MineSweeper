import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allows the user to choose between Timed and Relaxed, ans then transitions the user
 * into the game world where it is either timed or stopwatch. 
 * 
 * @author (Aiza) 
 * @version (a version number or a date)
 */
public class ModeButton extends Button
{
    //variables
    public static final String TIMED = "Timed";
    public static final String RELAXED = "Relaxed";
    public static final String EASY = "Easy";
    public static final String HARD = "Hard"; 
    
    public ModeButton(String label) {
        super(label);
        updateImage();
    }
    
    
    public void act()
    {
        super.act();
        if(isClicked()) {
            handleClick();
        }
    }
    
    private void handleClick() {
        CustomizationWorld world = (CustomizationWorld)getWorld();
        /*
        if(label.equals(TIMED)) {
            Greenfoot.setWorld(new MyWorld(9));
        } else if (label.equals(RELAXED)) {
            Greenfoot.setWorld(new MyWorld(9));
        }
        */
       
        if(label.equals(TIMED)) {
            world.setTimedMode(true);
        } else if (label.equals(RELAXED)) {
            world.setTimedMode(false);
        } else if (label.equals(EASY)) {
            world.setGridSize(9);
        } else if (label.equals(HARD)) {
            world.setGridSize(16);
        }
    }
    
    private void updateImage() {
        if(label.equals(TIMED)) {
            setImage("buttons/timed.png");
        } else if (label.equals(RELAXED)) {
            setImage("buttons/relaxed.png");
        } else if (label.equals(EASY)) {
            setImage("buttons/easy.png");
        } else if (label.equals(HARD)) {
            setImage("buttons/hard.png");
        }
    }
}
