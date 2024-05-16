import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameButton extends Button
{
    /**
     * Act - do whatever the GameButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void onPress() { 
        ((CharacterSelect) getWorld()).leaveScreen();
        ((CharacterSelect) getWorld()).removeObject(this);
    }
}
