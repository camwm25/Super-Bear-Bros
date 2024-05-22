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
    
    String imageName = "hitbox.png"; // this line should in theory say String imageName;
    double imageScale;
    
    // 1 by default, -1 if reflected.
    int imageDirection = 1;
    int imageTransparency = 255;
    
    public ForegroundObject(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Act - do whatever the ForegroundObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateOnScreen();
    }
    
    public void updateOnScreen() {
        Map world = (Map) getWorld();
        int screenX = (int) ((x - world.getCamX()) * world.getCamZoom()
                              + (world.getWidth() / 2));
        int screenY = (int) ((y - world.getCamY()) * world.getCamZoom()
                              + (world.getHeight() / 2));
        
        GreenfootImage image = new GreenfootImage(imageName);
        image.scale((int) (image.getWidth() * imageScale * world.getCamZoom()) + 1,
                    (int) (image.getHeight() * imageScale * world.getCamZoom()) + 1);
        if (imageDirection == -1) {
            image.mirrorHorizontally();
        }
        image.setTransparency(imageTransparency);
        setImage(image);
        setLocation(screenX, screenY);
    }
    
    public double getXPosition() {
        return x;
    }
    
    public double getYPosition() {
        return y;
    }
}
