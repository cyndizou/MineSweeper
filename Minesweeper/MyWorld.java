import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
/**
 * Main board screen
 * 
 * @Cyndi Zou (edited by Aiza -- for mode selection)
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //initialize all elements needed for the board
    private Cell[][] grid;
    private int gridSize;
    private int totalBombs;
    private static final int CELL_SIZE_SMALL = 50;
    private static final int CELL_SIZE_LARGE = 30;
    private int cellSize;
    private int boostAmount = 15;
    private BombCounter bombCounter;
    
    //for mode selection
    private boolean timedMode;
    
    /**
     * Constructor - sets up the world to the user's chosen size
     * @param gridSize 9x9 or 16x16
     */
    public MyWorld(int gridSize, boolean timedMode){  
        //set template of grid
        super(800, 600, 1);
        setBackground("border.png");
        
        //calculate size of grid
        this.gridSize = gridSize;
        this.timedMode = timedMode;
        if(gridSize==9){
            totalBombs=10;
            cellSize = CELL_SIZE_SMALL;
        }else if(gridSize==16){
            totalBombs=40;
            cellSize = CELL_SIZE_LARGE;
        }
        
        grid = new Cell[gridSize][gridSize];
        
        initializeGrid();
        placeBombs();
        calculateNeighbors();
        addUI();
    }
    
    //create the individual cells and placed at the position of the grid
    private void initializeGrid(){
        //initialize where the grid should start
        int startX = (800 - gridSize * cellSize) / 2 + cellSize / 2;
        int startY = (600 - gridSize * cellSize) / 2 + cellSize / 2 + 25;

    
        for (int row=0; row<gridSize; row++){
            for(int col=0; col<gridSize; col++){
                Cell cell = new Cell();
                grid[row][col]=cell;
                
                //set size first
                cell.setCellSize(cellSize);
                cell.setPosition(row, col);
                                
                //calculate pixel positions for this cell
                int x = col*cellSize + startX;
                int y = row*cellSize + startY;
                
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
                for (int rowOffset = -1; rowOffset<=1; rowOffset++){
                    for (int colOffset = -1; colOffset <=1; colOffset++){
                        //skip the cell itself
                        if(rowOffset==0 & colOffset==0){
                            continue;
                        }
                        
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
     * implement the flood effect that reveals all plain blocks next to the one user clicks
     * stops spreading when it hits a numbered cell
     */
    public void floodReveal(int startRow, int startCol){
        Stack<int[]> stack = new Stack<int[]>();
        
        //first put the clicked cell
        stack.push(new int[]{startRow, startCol});
        
        while(stack.isEmpty()==false){
            //take the next cell off the stack
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            
            //check all 8 neighboring cells
            for(int rowOffset = -1; rowOffset<=1; rowOffset++){
                for(int colOffset = -1; colOffset <= 1; colOffset++){
                    if(rowOffset == 0 && colOffset == 0){
                        continue;
                    }
                    
                    int neighborRow = row + rowOffset;
                    int neighborCol = col + colOffset;
                    
                    //make sure dont go out of bounds
                    if (neighborRow >= 0 && neighborRow < gridSize && neighborCol >= 0 && neighborCol < gridSize) {
                        Cell neighbor = grid[neighborRow][neighborCol];
                        
                        //only reveal if it isn't already revealed and it's not a bomb
                        if (neighbor.getIsRevealed() == false && neighbor.getIsBomb() == false) {
                            neighbor.forceReveal();
                            
                            if (neighbor.getNeighborCount() == 0) {
                                stack.push(new int[]{neighborRow, neighborCol});
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * adds the UI elements to the top of the screen
     * including the bomb counter and menu buttons
     */
    private void addUI(){
        bombCounter = new BombCounter(totalBombs);
        addObject(bombCounter, 400, 50);
        
        MenuButton restartButton = new MenuButton(MenuButton.RESTART);
        restartButton.resize(20, 10);
        addObject(restartButton, 200, 100);
        //addObject(new MenuButton(MenuButton.RESTART), 100, 50);
        addObject(new MenuButton(MenuButton.QUIT), 200, 50);
        addObject(new MenuButton(MenuButton.SOUND), 700, 50);
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
     * randomly place one timer boost on the game grid AND make sure it doens't land 
     * on the bombs
     */
    private void placeBoost(){
        boolean placed = false;
        while(placed==false){
            int randomRow = Greenfoot.getRandomNumber(gridSize);
            int randomCol = Greenfoot.getRandomNumber(gridSize);
            
            if (grid[randomRow][randomCol].getIsBomb() == false) {
                grid[randomRow][randomCol].setBoost(true);
                placed = true;
            }
        }
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
    
    //getter for timer mode
    public boolean getTimedMode() {
        return timedMode;
    }
    
    //apply timer boost
    public void applyTimerBoost(){
        //TO BE CONNECTED!!!
        System.out.println("boost applied");
    }
}