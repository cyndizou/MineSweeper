import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends Actor
{
    private Queue<Item> items;
    private static final int MAX_ITEMS = 3;
    
    public Inventory() {
        items = new LinkedList<Item>();
        GreenfootImage img = new GreenfootImage("inventory.png");
        img.scale(240, 300);
        setImage(img);
    }
    
    /**
     * adds a collected item to the inventory
     */
    public void addItem(Item item) {
        if (items.size() < MAX_ITEMS) {
            items.add(item);
            updateImage();
        }
    }
    
    /**
     * uses the next item in the queue FIFO
     */
    private void useNextItem(){
        if(items.isEmpty()==false){
            Item item = items.poll();
            MyWorld world = (MyWorld) getWorld();
            item.use(world);
            updateImage();
        }
    }
    
    /**
     * removes a specific item by name
     */
    public void removeItem(String itemName) {
        Queue<Item> updated = new LinkedList<Item>();
        boolean removed = false;
        
        for (Item item : items) {
            if (item.getItemName().equals(itemName) == false || removed) {
                updated.add(item);
            } else {
                removed = true;
            }
        }
        items = updated;
        updateImage();
    }
    
    //getter for item name
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * updates inventory image to show current items
     */
    private void updateImage() {
        setImage("inventory.png");
        addItemImages();
    }
    
    /**
     * draws each item icon on top of the inventory background
     */
    private void addItemImages() {
        GreenfootImage inv = getImage();
        int slot = 0;
        for (Item item : items) {
            GreenfootImage itemImg = new GreenfootImage(item.getItemName() + ".png");
            itemImg.scale(40, 40);
            //adjust the y offset to match image slots
            inv.drawImage(itemImg, 5, 10 + slot * 50);
            slot++;
        }
        setImage(inv);
    }
    
    public int getItemCount() {
        return items.size();
    }
}
