import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class GameScreen extends World
{

    /**
     * Constructor for objects of class MenuScreen.
     * 
     */
    public GameScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1, false); 
    }
    
    public abstract void leaveScreen();
    
    public abstract void goToScreen();
}
