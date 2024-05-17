import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objects seen in the foreground of the game.
 * 
 * @author Cam Welch Morgan and Devin Joe
 * @version 2024-05-16
 */
public abstract class ForegroundObject extends Actor
{
    double x;
    double y;
    
    double imageScale = 1;
    
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
        int screenX = (int) ((x - world.getCamX()) / world.getCamZoom()
                              + (world.getWidth() / 2));
        int screenY = (int) ((y - world.getCamY()) / world.getCamZoom()
                              + (world.getHeight() / 2));
        GreenfootImage image = getImage();
        image.scale((int) (image.getWidth() * imageScale * world.getCamZoom()),
                    (int) (image.getHeight() * imageScale * world.getCamZoom()));
        setLocation(screenX, screenY);
    }
    
    public double getXPosition() {
        return x;
    }
    
    public double getYPosition() {
        return y;
    }
}
