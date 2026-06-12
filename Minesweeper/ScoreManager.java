import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Handles saving and loading highscores -- lower time is better
 * 
 * Easy score stored in "slot 0"
 * Hard score stored in "slot 1"
 * 
 * Credits:
 * String formatting from ChatGPT
 * 
 * @author (Aiza) 
 * @version (a version number or a date)
 */
public class ScoreManager extends Actor
{
    private static final int EASY_INDEX = 0;
    private static final int HARD_INDEX = 1;
    
    //Saves a score if it beats the current high score
    
    public static void saveScore(int time, int gridSize) {
        UserInfo user = UserInfo.getMyInfo();
        
        if(user == null) {
            return;
        }
        
        int index;
        
        if(gridSize == 9) {
            index = EASY_INDEX;
        } else {
            index = HARD_INDEX;
        }
        
        int currentBest = user.getInt(index);
        
        if(currentBest == 0 || time < currentBest) {
            user.setInt(index, time);
            user.store();
        }
    }
    
    //Returns the high score for the chosen difficulty 
    public static int getHighScore(int gridSize) {
        UserInfo user = UserInfo.getMyInfo();
        
        if(user == null) {
            return 0;
        }
        
        if(gridSize == 9) {
            return user.getInt(EASY_INDEX);
        }
        
        return user.getInt(HARD_INDEX);
    }
    
    //Converts seconds into minutes:seconds format
    public static String formatTime(int seconds) {
        int minutes = seconds/60;
        int remainingSeconds = seconds % 60;
        
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
    
    
    
    public void act()
    {
        // Add your action code here.
    }
}
