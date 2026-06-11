import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is to display the timer or the stopwatch, depending
 * on the mode. If relaxed is chosen, a stopwatch will be implemented
 * where the player has all the time they need, and the timer will be
 * prevent the player from taking as much time as needed, making the
 * game a little harder
 * 
 * @author (Aiza) 
 * @version (a version number or a date)
 */
public class TimerDisplay extends Actor
{
    private int timeSeconds;
    private boolean countDown;
    
    public void act()
    {
        // Add your action code here.
    }
    
    public TimerDisplay(int startTime, boolean countDown) {
        this.timeSeconds = startTime;
        this.countDown = countDown;
    }
    
    public void updateTime() {
        if(countDown) {
            timeSeconds--;
        } else {
            timeSeconds++;
        }
        
        updateImage();
    }
    
    private void updateImage() {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds % 60;
    
        String timeText = String.format("%d:%02d", minutes, seconds);
    
        setImage(new GreenfootImage(
            timeText,
            32,
            Color.WHITE,
            new Color(0, 0, 0, 0)
        ));
    }
    
    public int getTime() {
        return timeSeconds;
    }
    
    public void addTime(int seconds) {
        timeSeconds += seconds;
        updateImage();
    }
}
