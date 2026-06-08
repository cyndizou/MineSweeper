import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cell - each individual block that user clicks on to interact
 * 
 * @Cyndi Zou 
 * @version (a version number or a date)
 */
public class Cell extends Button
{
    //initialization of main functions
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isFlagged;
    private int neighborCount;
    private int cellSize;
    private int row;
    private int col;
    
    /**
     * Constructor - creates a hidden unflagged, bombless cell
     */
    public Cell(){
        super("cell");
        isBomb = false;
        isRevealed = false;
        isFlagged = false;
        neighborCount = 0;
        updateImage();
    }

    /**
     * called every frame by Greenfot
     * checks for left and right clicks
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                //1 for left click
                if (mouse.getButton() == 1) {
                    handleLeftClick();
                } else if (mouse.getButton() == 3) {
                    //3 for left click
                    handleRightClick();
                }
            }
        }
    }
    
    /**
     * handles left click - reveals the cell
     * does nothing if cell is already revealed or flagged
     */
    private void handleLeftClick(){
        if(isRevealed){
            return;
        }
        if(isFlagged){
            return;
        }
        isRevealed = true;
        updateImage();
        
        //tell MyWorld the cell was revealed
        MyWorld world = (MyWorld) getWorld();
        world.cellRevealed(this);
    }
    
    /**
     * handles right click - places flag on and off
     * does nothing if cell already revealed
     */
    private void handleRightClick(){
        if(isRevealed){
            return;
        }
        isFlagged = !isFlagged;
        updateImage();
        
        //tell MyWorld the flag count needs to change
        MyWorld world = (MyWorld) getWorld();
        world.updateFlag(isFlagged);
    }
    
    /**
     * updates the image of the cell based on its current state
     */
    private void updateImage(){
        if(isRevealed){
            if(isBomb){
                setImage("bomb.png");
            } else if(neighborCount>0){
                //show image with number of neighbouring bombs
                setImage(neighborCount + ".png");
            }else{
                //empty cell with no neighbouring bombs
                setImage("blank block.png");
            }
        }else if (isFlagged){
            //show flag image
            setImage("flag.png");
        }else{
            //default cell
            setImage("cell.png");
        }
        
        //update size only after size is set
        if(cellSize>0){
            GreenfootImage img = getImage();
            img.scale(cellSize, cellSize);
            setImage(img);
        }
    }
    
    /**
     * forces the cell to show the bomb image
     * called by MyWorld when the game is over
     */
    public void revealBomb(){
        isRevealed = true;
        updateImage();
    }
    
    /**
     * sets cell sizes according to game mode
     */
    public void setCellSize(int cellSize){
        this.cellSize = cellSize;
        updateImage();
    }
    
    /**
     * sets cell position
     */
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }
    //sets whether the cell is a bomb
    public void setBomb(boolean isBomb){
        this.isBomb = isBomb;
    }
    
    //sets the number of nieghbouring bombs
    public void setNeighborCount(int count){
        this.neighborCount = count;
    }
    
    //returns whether this cell is a bomb
    public boolean getIsBomb(){
        return isBomb;
    }
    
    //returns whether this cell has been revealed
    public boolean getIsRevealed(){
        return isRevealed;
    }
    
    //returns whether this cell is flagged
    public boolean getIsFlagged(){
        return isFlagged;
    }
    
    //returns the number of neighbouring bombs
    public int getNeighborCount(){
        return neighborCount;
    }
    
    //getter for row position
    public int getRow(){
        return row;
    }
    
    //getter for col position
    public int getCol(){
        return col;
    }
}
