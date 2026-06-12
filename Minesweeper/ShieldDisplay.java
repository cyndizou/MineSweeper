import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display the shield icon next to the smiley face lives :D
 * 
 * @Cyndi Zou
 * @version (a version number or a date)
 */
public class ShieldDisplay extends Actor
{
    private boolean active;
    
    public ShieldDisplay() {
        active = false;
        updateImage();
    }
    
    public void activate() {
        active = true;
        updateImage();
    }
    
    public void deactivate() {
        active = false;
        setImage("faded Shield.png");
    }
    
    private void updateImage() {
        if (active) {
            setImage("Shield.png");
        } else {
            setImage(new GreenfootImage(1, 1));
        }
    }
    
    public boolean isActive() {
        return active;
    }
}
