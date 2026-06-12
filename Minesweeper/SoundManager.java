import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SoundManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundManager  
{
    private static GreenfootSound music;
    private static String currentSong = null;
    
    private static GreenfootSound[] sound = new GreenfootSound[20];
    private static boolean soundsInitialized = false;
    
    public SoundManager() {
        
    }
        
    public static void playMusic(String fileName) {
        if (music != null && currentSong != null && currentSong.equals(fileName) && music.isPlaying()) {
            return;
        }
        
        // stops the music if something else is playing
        if (music != null) {
            music.stop();
        }
        
        music = new GreenfootSound(fileName);
        music.setVolume(50);
        currentSong = fileName;
        music.playLoop();
    }
    
    // stops the music completely 
    public static void stopMusic() {
        if (music != null) {
            music.stop();
            music = null;
        }
    }
    
    public static void pauseMusic() {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }
    
    public static void resumeMusic() {
        if (music != null && !music.isPlaying()) {
            music.playLoop();
        }
    }
    
    public static void playSound(String fileName, int volume) {        
        if (!soundsInitialized) {
            for (int i = 0; i < sound.length; i++) {
                sound[i] = null;
            }
            soundsInitialized = true;
        }
        
        for (int i = 0; i < sound.length; i++) {
            if (sound[i] == null || !sound[i].isPlaying()) {
                if (sound[i] != null) {
                    sound[i].stop();
                }
                
                sound[i] = new GreenfootSound(fileName);
                sound[i].setVolume(volume);
                sound[i].play();
                break;
            }
        }
    }
    
    public static void stopSounds() {
        for (int i = 0; i < sound.length; i++) {
            if (sound[i] != null) {
                sound[i].stop();
                sound[i] = null;
            }
        }
    }
}
