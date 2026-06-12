import greenfoot.*;
/**
 * Boost Popup on the screen
 */
public class BoostPopup extends Actor {
    
    private int lifetime;
    
    public BoostPopup() {
        lifetime = 120;
        //setImage("boost_popup.png");
    }
    
    public void act() {
        lifetime--;
        if (lifetime <= 0) {
            getWorld().removeObject(this);
        }
    }
}
