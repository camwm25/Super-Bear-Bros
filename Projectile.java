import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectile extends PhysicsObject
{
    int throwerNumber;
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Projectile(double x, double y) {
        super(x, y);
    }
    
    public Projectile(double x, double y, int thrower) {
        this(x, y);
        throwerNumber = thrower;
    }
    
    public void updateVelocity() {
        if (onGround()) {
            yVelocity = 0;
            
            xVelocity *= FRICTION;
        }
        else {
            experienceTheWonderfulForceOfGravity();
            
            xVelocity *= DRAG;
        }
    }
}
