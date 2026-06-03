import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bomb Counter - displays the number on the screen showing how many bombs 
 * are left unflagged
 * 
 * @Cyndi Zou
 * @06.02.26
 */
public class BombCounter extends Actor
{
    //total number of bombs on th eboard
    private int totalBombs;
    
    //the number of cells the player has flagged
    private int flagCount;
    
    /**
     * Constructor - sets up the counter with the total number of bombs
     * @param totalBombs
     */
    public BombCounter(int totalBombs){
        this.totalBombs = totalBombs;
        this.flagCount = 0;
        updateImage();
    }
    
    /**
     * updates the display to show bombs remaining
     */
    public void updateFlagCount(boolean flagged){
        if(flagged){
            flagCount++;
        }else{
            flagCount--;
        }
        updateImage();
    }
    
    /**
     * updates the display to show bombs remaining
     */
    private void updateImage(){
        int bombsLeft = totalBombs - flagCount;
        
        GreenfootImage img = new GreenfootImage(100, 50);
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, 100, 50);
        img.setColor(Color.RED);
        img.setFont(new Font("Arial", true, false, 30));
        img.drawString(bombsLeft + "", 25, 38);
        setImage(img);
        
    }
    
    //returns the current number of bombs remaining
    public int getBombsLeft(){
        return totalBombs - flagCount;
    }
}
