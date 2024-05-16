import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When clicked, leaves start screen and moves to character select screen.
 * 
 * @author Cam Welch Morgan 
 * @version 2024-01-12
 */
public class StartButton extends Button
{    
    public void onPress() { 
        ((StartScreen) getWorld()).leaveScreen();
        ((StartScreen) getWorld()).removeObject(this);
    }
}
