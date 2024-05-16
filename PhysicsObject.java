import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Foreground object which experiences physical forces (gravity, friction, etc).
 * 
 * @author Devin Joe
 * @version 2024-01-31
 */
public abstract class PhysicsObject extends ForegroundObject
{
    static double FRICTION = 0.9; // A constant from 0 to 1.
    static double DRAG = 0.9; // A constant from 0 to 1.
    
    double xVelocity = 0;
    double yVelocity = 0;
    
    /**
     * Act - do whatever the PhysicsObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    protected void experienceTheWonderfulForceOfGravity() {
        yVelocity += 1;
    }
    
        
    public boolean onGround() {
        double tolerance = 30;
        
        if (yVelocity < 0) {
            return false;
        }
        
        for (Ground tile : getIntersectingObjects(Ground.class)) {
            
            double playerBottom = y + getImage().getHeight() / 2;
            double groundTop = tile.getYPosition() - tile.getImage().getHeight() / 2;
            
            if (playerBottom < groundTop + tolerance) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean onBaseLayerGround() {
        double tolerance = 50;
        
        if (yVelocity < 0) {
            return false;
        }
        
        for (Ground tile : getIntersectingObjects(Ground.class)) {
            double playerBottom = y + getImage().getHeight() / 2;
            double groundTop = tile.getYPosition() - tile.getImage().getHeight() / 2;
            
            if (playerBottom < groundTop + tolerance && !tile.canFallThrough()) {
                return true;
            }
        }
        
        return false;
    }
    
    public double getXVelocity() {
        return xVelocity;
    }
    
    public double getYVelocity() {
        return yVelocity;
    }
    
    protected void move() {
        x += xVelocity;
        y += yVelocity / 2;
        updateScreenLocation();
        if (!onBaseLayerGround()) {
            y += yVelocity / 2;
            updateScreenLocation();
        }
    }
}
