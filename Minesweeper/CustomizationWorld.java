import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CustomizationWorld here.
 * 
 * @author (Aiza) 
 * @version (a version number or a date)
 */
public class CustomizationWorld extends World
{
    /**
     * Constructor for objects of class CustomizationWorld.
     * 
     */
    //variables for storing choices
    private boolean timedMode = false;
    private int gridSize = 9;
    
    //for adding a radio button functionality
    private ModeButton timedButton;
    private ModeButton relaxedButton;
    private ModeButton easyButton;
    private ModeButton hardButton;
    
    public CustomizationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground(new GreenfootImage("border.png"));
        
        //add mode buttons
        //addObject(new ModeButton(ModeButton.RELAXED), 229, 162);
        //addObject(new ModeButton(ModeButton.TIMED), 568, 158);
        //addObject(new ModeButton(ModeButton.EASY), 229, 300);
        //addObject(new ModeButton(ModeButton.HARD), 568, 305);
        
        //Try out for that "radio button" affect
        timedButton = new ModeButton(ModeButton.TIMED);
        relaxedButton = new ModeButton(ModeButton.RELAXED);
        easyButton = new ModeButton(ModeButton.EASY);
        hardButton = new ModeButton(ModeButton.HARD);
        
        addObject(relaxedButton, 229, 162);
        addObject(timedButton, 568, 158);
        addObject(easyButton, 229, 300);
        addObject(hardButton, 568, 305);
        
        //add another start button to lead to game world
        addObject(new MenuButton(MenuButton.START), 400, 460);
        
        SoundsManager.playMusic("customizeMusic.wav");
    }
    
    public void started() {
        SoundsManager.playMusic("customizeMusic.wav");
    }
    
    public void stopped() {
        SoundsManager.stopMusic();
    }
    
    public void setTimedMode(boolean timedMode) {
        this.timedMode = timedMode;
    }
    
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
    
    //getters
    public boolean getTimedMode() {
        return timedMode;
    }
    
    public int getGridSize() {
        return gridSize;
    }
    
    //handle the selection
    public void selectMode(String mode) {
        if(mode.equals(ModeButton.TIMED)) {
            timedButton.setSelected(true);
            relaxedButton.setSelected(false);
            timedMode = true;
        } else if (mode.equals(ModeButton.RELAXED)) {
            timedButton.setSelected(false);
            relaxedButton.setSelected(true);
            timedMode = false;
        }
    }
    
    public void selectDifficulty(String difficulty) {
        if(difficulty.equals(ModeButton.EASY)) {
            easyButton.setSelected(true);
            hardButton.setSelected(false);
            gridSize = 9;
        } else if(difficulty.equals(ModeButton.HARD)) {
            easyButton.setSelected(false);
            hardButton.setSelected(true);
            gridSize = 16;
        }
    }
}
