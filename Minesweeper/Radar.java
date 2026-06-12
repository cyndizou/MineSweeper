import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * clears any 3x3 area without risking lives
 * 
 * Cyndi Zou
 * @version (a version number or a date)
 */
public class Radar extends Item
{
    public Radar(){
        super("Radar");
    }
        
    @Override
    public void use(MyWorld world){
        Cell[][] grid = world.getGrid();
        int gridSize = world.getGridSize();
        
        //find a random unrevealed non-bomb cell to center the radar on
        boolean placed = false;
        while(placed == false){
            int randomRow = Greenfoot.getRandomNumber(gridSize);
            int randomCol = Greenfoot.getRandomNumber(gridSize);
            
            if(grid[randomRow][randomCol].getIsBomb() == false && grid[randomRow][randomCol].getIsRevealed() == false){
                //reveal the 3x3 area around it
                for(int rowOffset = -1; rowOffset <=1; rowOffset++){
                    for (int colOffset = -1; colOffset <= 1; colOffset++) {
                        int neighborRow = randomRow + rowOffset;
                        int neighborCol = randomCol + colOffset;
                        
                        if (neighborRow >= 0 && neighborRow < gridSize && 
                            neighborCol >= 0 && neighborCol < gridSize) {
                            if (grid[neighborRow][neighborCol].getIsBomb() == false) {
                                grid[neighborRow][neighborCol].forceReveal();
                            }
                        }
                    }
                }
                placed = true;
            }
        }
    }
}
