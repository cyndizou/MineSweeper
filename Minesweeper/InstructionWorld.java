import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is an instruction page. If the player wants to know how to play the game,
 * by clicking on the "i" on the intro screen will bring them to this world which
 * has the instructions on how to play this back. By pressing back, the will return 
 * to the intro screen. 
 * 
 * @author (Aiza) 
 * @version (a version number or a date)
 */
public class InstructionWorld extends World
{

    /**
     * Constructor for objects of class InstructionClass.
     * 
     */
    public InstructionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground(new GreenfootImage("border.png"));
    }
    
    public void started() {
        SoundsManager.playMusic("customizeMusic.wav");
    }
    
    public void stopped() {
        SoundsManager.stopMusic();
    }
}
