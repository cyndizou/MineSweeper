import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Item
{
    public Shield(){
        super("Shield");
    }
    
    /**
     * show shield icon next to faces and give one more life
     */
    @Override
    public void use(MyWorld world){
        world.getShieldDisplay().activate();
    }
    
    public void collect(){
        collected = true;
        MyWorld world = (MyWorld) getWorld();
        world.getInventory().addItem(this);
    }
}
