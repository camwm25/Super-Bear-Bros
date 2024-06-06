import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlButton extends Button
{
    /**
     * Act - do whatever the ControlButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void onPress() { 
        ControlScreen c = ((ControlScreen) getWorld());
        c.changeControlOne();
        ((GameScreen) getWorld()).removeObject(this);
    }
}
