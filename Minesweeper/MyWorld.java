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
    private BombCounter bombCounter;
    private boolean gameOver = false;
    private boolean firstClick = true;
    
    //lives
    private LivesDisplay happyFace;
    private LivesDisplay midFace;
    private LivesDisplay sadFace;
    private int livesRemaining = 3;
    
    //for mode selection
    private boolean timedMode;
    
    //For game over delay
    private boolean gameLost = false;
    private int endDelay = 0;
    
    //Timer display
    private TimerDisplay timerDisplay;
    private int frameCounter;
    
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
        calculateNeighbors();
        addUI();
        
        SoundsManager.playMusic("gameMusic.wav");
    }
    
    public void act() {
        //timer
        frameCounter++;
        if(frameCounter >= 60) {
            frameCounter = 0;
            timerDisplay.updateTime();
            if(timedMode && timerDisplay.getTime() <= 0) {
                gameOver();
            }
        }
        
        if(gameLost) {
            endDelay--;
            if(endDelay <= 0) {
                SoundsManager.stopMusic();
                Greenfoot.setWorld(new EndWorld());
            }
        }
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
    private void placeBombs(int safeRow, int safeCol){
        int bombsPlaced = 0;
        while(bombsPlaced < totalBombs){
            int randomRow = Greenfoot.getRandomNumber(gridSize);
            int randomCol = Greenfoot.getRandomNumber(gridSize);
            if(grid[randomRow][randomCol].getIsBomb() == false &&
               randomRow != safeRow &&
               randomCol != safeCol){
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
        addObject(bombCounter, 400, 50);
       
        addObject(new MenuButton(MenuButton.RESTART), 685, 500);
        addObject(new MenuButton(MenuButton.QUIT), 111, 500);
        addObject(new MenuButton(MenuButton.SOUND), 700, 50);
        
        //creating the correct timer (stopwatch or timer)
        if(timedMode) {
            // 3 min timer
            timerDisplay = new TimerDisplay(180, true);
        } else {
            timerDisplay = new TimerDisplay(0, false);
        }
        
        addObject(timerDisplay, 600, 50);
        
        happyFace = new LivesDisplay(1);
        midFace = new LivesDisplay(2);
        sadFace = new LivesDisplay(3);
        
        addObject(happyFace, 300, 50);
        addObject(midFace, 350, 50);
        addObject(sadFace, 400, 50);
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
         livesRemaining--;
        
        if (livesRemaining == 2) {
            //lose first life
            happyFace.lose();
            SoundsManager.playSound("livesLost.wav", 80);
            resetBoard();
        } else if (livesRemaining == 1) {
            //lost second life
            midFace.lose();
            SoundsManager.playSound("livesLost.wav", 80);
            resetBoard();
        } else if (livesRemaining == 0) {
            // lost last life - remove sad face and end game
            sadFace.lose();
            SoundsManager.playSound("livesLost.wav", 80);
            gameOver = true;
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    if (grid[row][col].getIsBomb()) {
                        grid[row][col].revealBomb();
                    }
                }
            }
            gameLost = true;
            endDelay = 120;
        }
    }
    
    /**
     * randomly places one boost cell avoiding bombs
     */
    private void placeBoost(){
        //no timer boost in relaxed mode
        if (timedMode == false) {
            return; 
        }
            
        boolean placed = false;
        while(placed == false){
            int randomRow = Greenfoot.getRandomNumber(gridSize);
            int randomCol = Greenfoot.getRandomNumber(gridSize);
            if(grid[randomRow][randomCol].getIsBomb() == false){
                grid[randomRow][randomCol].setBoost(true);
                placed = true;
            }
        }
    }
    
    /**
     * handles all cell click logic in one place
     */
    public void handleCellClick(int row, int col){
        Cell cell = grid[row][col];
        
        if(firstClick){
            firstClick = false;
            placeBombs(row, col);
            placeBoost();
            calculateNeighbors();
        }
        
        if(cell.getIsBomb()){
            cell.forceReveal();
            gameOver();
            return;
        }
        
        cell.forceReveal();
        
        if(cell.getIsBoost()){
            applyTimerBoost();
        }
        
        if(cell.getNeighborCount() == 0){
            floodReveal(row, col);
        }
        
        checkWin();
    }
    
    /**
     * reveals all connected empty cells from the clicked cell
     */
    public void floodReveal(int startRow, int startCol){
        Stack<int[]> stack = new Stack<int[]>();
        
        for(int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for(int colOffset = -1; colOffset <= 1; colOffset++){
                if(rowOffset == 0 && colOffset == 0){
                    continue;
                }
                int neighborRow = startRow + rowOffset;
                int neighborCol = startCol + colOffset;
                if(neighborRow >= 0 && neighborRow < gridSize && neighborCol >= 0 && neighborCol < gridSize){
                    stack.push(new int[]{neighborRow, neighborCol});
                }
            }
        }
        
        while(stack.isEmpty() == false){
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            
            if(grid[row][col].getIsRevealed()){
                continue;
            }
            
            Cell neighbor = grid[row][col];
            
            if(neighbor.getIsBomb() == false){
                neighbor.forceReveal();
                
                //MAKE SURE TO PUSH ALL NEIGHBORS TO TEHE STACK
                if(neighbor.getNeighborCount() == 0){
                    for(int rowOffset = -1; rowOffset <= 1; rowOffset++){
                        for(int colOffset = -1; colOffset <= 1; colOffset++){
                            if(rowOffset == 0 && colOffset == 0){
                                continue;
                            }
                            int newRow = row + rowOffset;
                            int newCol = col + colOffset;
                            if(newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize){
                                stack.push(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * resets the board after losing a life
     * clears all cells and replaces bombs
     */
    private void resetBoard() {
        //remove all existing cells from the world
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                removeObject(grid[row][col]);
            }
        }
        
        //rebuild grid
        grid = new Cell[gridSize][gridSize];
        initializeGrid();
        calculateNeighbors();
        
        //reset flags
        gameOver = false;
        firstClick = true;
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
    
    /**
     * called when player directly clicks the boost cell
     */
    public void applyTimerBoost(){
        if (timedMode) {
            if (timerDisplay != null) {
                timerDisplay.addTime(15);
                SoundsManager.playSound("booster.wav", 75);
            }
            addObject(new BoostPopup(), 400, 300);
        }
    }

    //getter for timer mode
    public boolean getTimedMode() {
        return timedMode;
    }
    
    //game over
    public boolean isGameOver(){
        return gameOver;
    }
    
    public void started() {
        SoundsManager.playMusic("gameMusic.wav");
    }
    
    public void stopped() {
        SoundsManager.stopMusic();
    }
}