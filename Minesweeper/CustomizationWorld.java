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
    
    public CustomizationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground(new GreenfootImage("border.png"));
        
        //add mode buttons
        addObject(new ModeButton(ModeButton.RELAXED), 229, 162);
        addObject(new ModeButton(ModeButton.TIMED), 568, 158);
        addObject(new ModeButton(ModeButton.EASY), 229, 300);
        addObject(new ModeButton(ModeButton.HARD), 568, 305);
        
        //add another start button to lead to game world
        addObject(new MenuButton(MenuButton.START), 400, 460);
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
}
