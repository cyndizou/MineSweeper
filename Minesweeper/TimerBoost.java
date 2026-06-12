import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Timer Boost is one of the three items users can collect
 * - gives extra 15 seconds only if user clicks on the cell
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimerBoost extends Item
{
    public TimerBoost(){
        super("Timer Boost");
    }
    
    @Override
    public void use(MyWorld world){
         if (world.getTimerDisplay() != null) {
            world.getTimerDisplay().addTime(15);
        }
        world.addObject(new BoostPopup(), 400, 300);
    }
}
