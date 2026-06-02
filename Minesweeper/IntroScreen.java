import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen() {
        super(1200, 800, 1); 
        
        //set background image
        
        //add menu buttons to the screen
        addObject(new MenuButton(MenuButton.START), 600, 500);
        addObject(new MenuButton(MenuButton.SOUND), 1150, 50);
    }
    
    public void act() {
        //MenuButton class handles the clicks! :)
    }
}
