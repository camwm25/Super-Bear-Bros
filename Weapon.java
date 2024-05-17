import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class for character melee weapons (Bill's lightsaber, etc.).
 * 
 * @author Cam Welch Morgan
 * @version 2024-05-16
 */
public abstract class Weapon extends ForegroundObject
{
    public int bearerNumber;
    public int direction;    
    public int timer;
    /**
     * Act - do whatever the Weapons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Weapon(int bearerNumber, int direction, int timer, double x, double y) {
        super(x, y);
        this.bearerNumber = bearerNumber;
        this.direction = direction;
        this.timer = timer;
    }
}
