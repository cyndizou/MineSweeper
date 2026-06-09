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
    public static final String INFO = "Info";

    //tracks if sound is on or off
    private boolean soundOn;
    
    /**
     * Constructor - creates a menu button with the given label and sets sound
     * to on by default
     */
    public MenuButton(String label){
        super(label);
        soundOn = true;
        updateImage();
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
    }
    
    /**
     * handles what happens when the button is clicked
     * depending on what type of button it is
     */
    private void handleClick(){
        if(label.equals(START)){
            //switch to main game world
            //commented out going to main world to bringing 
            //user to customization world
            //Greenfoot.setWorld(new MyWorld(9));
            //Greenfoot.setWorld(new CustomizationWorld());
            
            if(getWorld() instanceof CustomizationWorld) {
                CustomizationWorld world = (CustomizationWorld)getWorld();
                Greenfoot.setWorld(new MyWorld(world.getGridSize(), world.getTimedMode()));
            } else {
                Greenfoot.setWorld(new CustomizationWorld());
            }
            
        } else if(label.equals(QUIT)){
            //stop the game
            Greenfoot.stop();
        } else if(label.equals(RESTART)){
            //restart a new world 
            //had to add changes here 
            //Greenfoot.setWorld(new MyWorld(9));
            
            MyWorld world = (MyWorld)getWorld();
            Greenfoot.setWorld(new MyWorld(world.getGridSize(), world.getTimedMode()));
        } else if (label.equals(SOUND)){
            //flip sound between on and off
            soundOn = !soundOn;
            updateImage();
        } else if (label.equals(INFO)) {
            Greenfoot.setWorld(new InstructionWorld());
        }
    }
    
    /**
     * sets the image of this button based on its label
     */
    private void updateImage() {
        if (label.equals(SOUND)) {
            if (soundOn) {
                setImage("buttons/sound on.png");
            } else {
                setImage("buttons/sound off.png");
            }
        } else if (label.equals(START)) {
            setImage("buttons/start.png");
        } else if (label.equals(QUIT)) {
            setImage("buttons/quit.png");
        } else if (label.equals(RESTART)) {
            setImage("buttons/restart.png");
            setImage("buttons/quit (2).png");
        } else if (label.equals(RESTART)) {
            setImage("buttons/restart (2).png");
        } else if (label.equals(INFO)) {
            setImage("buttons/info.png");
        }
    }
    
    //returns whether sound is currently on, used by other classes 
    public boolean isSoundOn(){
        return soundOn;
    }
    
    //resizing the button in the game world
    public void resize(int width, int height) {
        getImage().scale(width, height);
    }
}
