import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class GameScreen extends World
{    
    public static GreenfootSound music = new GreenfootSound("main_music.wav");
    
    /**
     * Constructor for objects of class MenuScreen.
     * 
     */
    public GameScreen()
    {    
        // Create a new world with 960x540 cells with a cell size of 1x1 pixels.
        super(960, 540, 1, false);
    }
    
    public abstract void leaveScreen();
    
        
    public void displayText(String text, int x, int y, double size) {
        Text displayText = new Text(text, x, y, size);
        displayText.show(this);
    }
    
    /*
     * Prepares any objects that must be on this screen opens this screen.
     */
    public abstract void goToScreen();
}
