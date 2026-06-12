import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Items users can collect in the game, both beneficial and harmful
 * 
 * This is the abstract superclass that will be inherited by all specific items
 * 
 * @Cyndi Zou
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    protected String itemName;
    protected boolean collected;
    
    public Item(String itemName){
        this.itemName = itemName;
        this.collected = false;
    }
    
    /**
     * called when item is used
     */
    public void use(MyWorld world){
        //for children to override
    }
    
    //getter for item's name
    public String getItemName(){
        return itemName;
    }
}
