import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button clicked to start the game.
 * 
 * @author Cam Welch Morgan
 * @version 2024-01-12
 */
public class GameButton extends Button
{
    /**
     * Act - do whatever the GameButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void onPress() { 
        ((GameScreen) getWorld()).leaveScreen();
        ((GameScreen) getWorld()).removeObject(this);
    }
}
