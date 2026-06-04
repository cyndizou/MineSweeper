import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main board screen
 * 
 * @Cyndi Zou
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //initialize all elements needed for the board
    private Cell[][] grid;
    private int gridSize;
    private int totalBombs;
    private static final int CELL_SIZE = 50;
    private BombCounter bombCounter;
    
    /**
     * Constructor - sets up the world to the user's chosen size
     * @param gridSize 9x9 or 16x16
     */
    public MyWorld(int gridSize){  
        //set template of grid
        super(gridSize * CELL_SIZE, gridSize * CELL_SIZE + 100, 1);
        
        //calculate size of grid
        this.gridSize = gridSize;
        if(gridSize==9){
            totalBombs=10;
        }else if(gridSize==16){
            totalBombs=40;
        }
        
        grid = new Cell[gridSize][gridSize];
        
        initializeGrid();
        placeBombs();
        calculateNeighbors();
        addUI();
    }
    
    //create the individual cells and placed at the position of the grid
    private void initializeGrid(){
        for (int row=0; row<gridSize; row++){
            for(int col=0; col<gridSize; col++){
                Cell cell = new Cell();
                grid[row][col]=cell;
                
                //calculate pixel positions for this cell
                int x = col*CELL_SIZE + CELL_SIZE/2;
                int y = row*CELL_SIZE + CELL_SIZE/2 + 100;
                
                addObject(cell, x, y);
            }
        }
    }
    
    /**
     * randomly place bombs on the grid while making sure there isn't
     * one already in the cell
     */
    private void placeBombs(){
        int bombsPlaced = 0;
        
        while(bombsPlaced<totalBombs){
            int randomRow = Greenfoot.getRandomNumber(gridSize);
            int randomCol = Greenfoot.getRandomNumber(gridSize);
            
            //only place a bomb if there isn't one already
            if(grid[randomRow][randomCol].getIsBomb()==false){
                grid[randomRow][randomCol].setBomb(true);
                bombsPlaced++;
            }
        }
    }
    
    /**
     * goes through every cell and counts how many bombs are in
     * the surrounding cells
     */
    private void calculateNeighbors(){
        for(int row=0; row<gridSize; row++){
            for(int col = 0; col<gridSize; col++){
                //skip if the cell is a bomb
                if(grid[row][col].getIsBomb()){
                    continue;
                }
                
                int count = 0;
                
                //loop through all 8 neighboring cells
                for (int rowOffset = -1; rowOffset<1; rowOffset++){
                    for (int colOffset = -1; colOffset <=1; colOffset++){
                        int neighborRow = row + rowOffset;
                        int neighborCol = col + colOffset;
                        
                        //make sure not out of boundaries
                        if(neighborRow>=0 && neighborRow < gridSize && neighborCol>=0 && neighborCol < gridSize){
                            if (grid[neighborRow][neighborCol].getIsBomb()){
                                count++;
                            }
                        }
                    }
                }
                
                grid[row][col].setNeighborCount(count);
            }
        }
    }
    
    /**
     * adds the UI elements to the top of the screen
     * including the bomb counter and menu buttons
     */
    private void addUI(){
        bombCounter = new BombCounter(totalBombs);
        addObject(bombCounter, gridSize * CELL_SIZE / 2, 50);
        
        addObject(new MenuButton(MenuButton.RESTART), gridSize*CELL_SIZE-100, 50);
        addObject(new MenuButton(MenuButton.QUIT), gridSize * CELL_SIZE-200, 50);
        addObject(new MenuButton(MenuButton.SOUND), gridSize*CELL_SIZE - 50, 20);
    }
    
    /**
     * called when a cell is revealed
     * checks if the player hit a bomb or won the game
     * @param cell - the cell that was just revealed
     */
    public void cellRevealed(Cell cell){
        if(cell.getIsBomb()){
            gameOver();
        }else{
            checkWin();
        }
    }
    
    /**
     * Called when a flag is placed or removed
     * updates the bomb counter display
     * @param flagged - true if the flag was added, false if removed
     */
    public void updateFlag(boolean flagged){
        bombCounter.updateFlagCount(flagged);
    }
    
    /**
     * Checks if the player has won
     * win condition is if all non-bomb cells are revealed
     */
    private void checkWin(){
        for(int row = 0; row<gridSize; row++){
            for(int col=0; col<gridSize; col++){
                if(grid[row][col].getIsBomb()==false && grid[row][col].getIsRevealed()==false){
                    return;
                }
            }
        }
        Greenfoot.stop();
    }
    
    /**
     * ends the game when a bomb is clicked
     * reveals all bombs on the board
     */
    private void gameOver(){
        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col<gridSize; col++){
                if(grid[row][col].getIsBomb()){
                    grid[row][col].revealBomb();
                }
            }
        }
        Greenfoot.stop();
    }
    
    /**
     * getter - returns the grid array
     */
    public Cell[][] getGrid(){
        return grid;
    }
    
    /**
     * getter - returns the grid size
     */
    public int getGridSize(){
        return gridSize;
    }
}
