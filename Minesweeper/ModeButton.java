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
    
    //Allow the user to select and de-select the modes
    private boolean selected;
    
    public ModeButton(String label) {
        super(label);
        selected = false;
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
        SoundsManager.playSound("buttonClick.wav", 90);
        
        CustomizationWorld world = (CustomizationWorld)getWorld();

        if(label.equals(TIMED) || label.equals(RELAXED))
        {
            world.selectMode(label);
        }
    
        if(label.equals(EASY) || label.equals(HARD))
        {
            world.selectDifficulty(label);
        }
    }
    
    private void updateImage()
    {
        if(label.equals(TIMED))
        {
            if(selected)
            {
                setImage("buttons/pressed timed.png");
            }
            else
            {
                setImage("buttons/timed (1).png");
            }
        }
        else if(label.equals(RELAXED))
        {
            if(selected)
            {
                setImage("buttons/pressed relaxed.png");
            }
            else
            {
                setImage("buttons/relaxed (1).png");
            }
        }
        else if(label.equals(EASY))
        {
            if(selected)
            {
                setImage("buttons/pressed easy.png");
            }
            else
            {
                setImage("buttons/easy (1).png");
            }
        }
        else if(label.equals(HARD))
        {
            if(selected)
            {
                setImage("buttons/pressed hard.png");
            }
            else
            {
                setImage("buttons/hard (1).png");
            }
        }
    }
    
    /*
    private void updateImage() {
        if(label.equals(TIMED)) {
            setImage("buttons/timed (1).png");
        } else if (label.equals(RELAXED)) {
            setImage("buttons/relaxed (1).png");
        } else if (label.equals(EASY)) {
            setImage("buttons/easy (1).png");
        } else if (label.equals(HARD)) {
            setImage("buttons/hard (1).png");
            if(selected) {
                setImage("buttons/pressed timed.png");
            } else {
                setImage("buttons/timed (1).png");
            }
        } else if (label.equals(RELAXED)) {
            if(selected) {
                setImage("buttons/pressed relaxed.png");
            } else {
                setImage("buttons/relaxed (1).png");
            }
        } else if (label.equals(EASY)) {
            if(selected) {
               setImage("buttons/pressed easy.png"); 
            } else {
                setImage("buttons/easy (1).png");
            }
        } else if (label.equals(HARD)) {
            if(selected) {
              setImage("buttons/pressed hard.png");  
            } else {
                setImage("buttons/hard (1).png");
            }
        }
    }
    */
    
    /*
     * Methods for adding the radio button affect.
     * Allows the deselection of one button when the other is selected
     */
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        updateImage();
    }
    
    public boolean isSelected() {
        return selected;
    }
}
