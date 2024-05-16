import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author Devin Joe
 * @version 2024-01-31
 */
public class Ground extends ForegroundObject
{   
    private String type;
    
    public Ground(String imageName, double x, double y, String type) {
        GreenfootImage image = new GreenfootImage("" + imageName + ".png");
        image.scale(image.getWidth() / 8, image.getHeight() / 8);
        setImage(image);
        
        this.type = type;
        
        this.x = x;
        this.y = y;
    }
    
    public Ground(String imageName, double x, double y) {
        this(imageName, x, y, "normal");
    }
    
    public boolean canFallThrough() {
        return !(type.equals("base"));
    }
    
    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // public void act() {}
}
