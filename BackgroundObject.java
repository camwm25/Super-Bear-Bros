import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objects seen in the foreground of the game.
 * 
 * @author Cam Welch Morgan and Devin Joe
 * @version 2024-05-16
 */
public abstract class BackgroundObject extends Actor
{
    double x;
    double y;
    double z;
    
    String imageName = "hitbox.png"; // this line should in theory say String imageName;
    double imageScale;
    
    double parallaxFactor;
    
    // 1 by default, -1 if reflected.
    int imageDirection = 1;
    int imageTransparency = 255;
    
    public BackgroundObject(double x, double y, double z) {
        // Set the image to transparent to begin with to avoid unwanted flashing
        getImage().setTransparency(0);
        this.x = x;
        this.y = y;
        this.z = z;
        parallaxFactor = 1 / (1 + z);
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
        int screenX = (int) (parallaxFactor * (x - world.getCamX()) * world.getCamZoom()
                              + (world.getWidth() / 2));
        int screenY = (int) (parallaxFactor * (y - world.getCamY()) * world.getCamZoom()
                              + (world.getHeight() / 2));
        
        GreenfootImage image = new GreenfootImage(imageName);
        image.scale((int) (image.getWidth() * imageScale * world.getCamZoom() * parallaxFactor) + 1,
                    (int) (image.getHeight() * imageScale * world.getCamZoom() * parallaxFactor) + 1);
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
