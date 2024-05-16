import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ForegroundObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ForegroundObject extends Actor
{
    double x;
    double y;
    
    /**
     * Act - do whatever the ForegroundObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateScreenLocation();
    }
    
    public void updateScreenLocation() {
        Map world = (Map) getWorld();
        int screenX = (int) (x - world.getCamX() + world.getWidth() / 2);
        int screenY = (int) (y - world.getCamY() + world.getHeight() / 2);
        setLocation(screenX, screenY);
    }
    
    public double getXPosition() {
        return x;
    }
    
    public double getYPosition() {
        return y;
    }
}
