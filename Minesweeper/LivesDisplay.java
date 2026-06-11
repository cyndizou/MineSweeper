import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LivesDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LivesDisplay extends Actor
{
    private int faceNumber;
    private boolean active;
    /**
     * Constructor
     */
    public LivesDisplay(int faceNumber) {
        this.faceNumber = faceNumber;
        this.active = true;
        updateImage();
    }

    
    /**
     * updates the image based on remaining lives
     */
    private void updateImage() {
        if (faceNumber == 1) {
            setImage("happy.png");
        } else if (faceNumber == 2) {
            setImage("mid.png");
        } else if (faceNumber == 3) {
            setImage("sad.png");
        }
    }
    
     /**
     * removes this face from the screen
     */
    public void lose() {
        active = false;
        getWorld().removeObject(this);
    }
}
