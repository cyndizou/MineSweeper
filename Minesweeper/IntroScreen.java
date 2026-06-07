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
        super(800, 600, 1); 
        setBackground(new GreenfootImage("introScreen.png"));
        //startButton = new Button("Start", 150);
        //addObject(startButton, getWidth() / 2, getHeight() / 5 * 4 + 20);
        //Greenfoot.setWorld(this)
        
        //set background image
        
        //add menu buttons to the screen
        addObject(new MenuButton(MenuButton.START), 400, 400);
        addObject(new MenuButton(MenuButton.SOUND), 700, 510);
        //addObject(new MenuButton(MenuButton.INFO),
    }
    
    public void act() {
        //MenuButton class handles the clicks! :)
    }
}
