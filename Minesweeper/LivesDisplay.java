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
    private boolean lost;
    /**
     * Constructor
     */
    public LivesDisplay(int faceNumber) {
        this.faceNumber = faceNumber;
        lost = false;
        updateImage();
    }
    
    private void updateImage() {
        if (lost) {
            if (faceNumber == 1) {
                //setImage("faded happy.png");
            } else if (faceNumber == 2) {
                //setImage("faded mid.png");
            } else {
                //setImage("faded sad.png");
            }
        } else {
            if (faceNumber == 1) {
                setImage("happy.png");
            } else if (faceNumber == 2) {
                setImage("mid.png");
            } else {
                setImage("sad.png");
            }
        }
    }
    
     /**
     * removes this face from the screen
     */
    public void lose() {
        lost = true;
        updateImage();
    }
}
