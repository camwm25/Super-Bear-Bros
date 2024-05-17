import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objects you can stand on.
 * 
 * @author Devin Joe
 * @version 2024-01-31
 */
public class Ground extends ForegroundObject
{   
    private String type;
    
    public Ground(String imageName, double x, double y, String type) {
        super(x, y);
        this.imageName = "" + imageName + ".png";
        imageScale = 0.125;
        
        this.type = type;
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
