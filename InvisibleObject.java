import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All Invisible Objects.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class InvisibleObject extends ForegroundObject
{
    int creator;
    int direction;
    double xCoord;
    double yCoord;
    
    int trans = 255;
    /**
     * Act - do whatever the hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        checkHit();
        
        imageTransparency = trans;
    }
    
    public InvisibleObject(int playerCreator, double x, double y, int direction) {
        super(x,y);
        creator = playerCreator;
        this.direction = direction;
        xCoord = x;
        yCoord = y;
    }
    
    public abstract void checkHit();
    
    public void checkOwner() {
        boolean ownerHere = false;
        int ownerDirection = direction;
        
        int ownerX = 0;
        int ownerY = 0;
        
        for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber == creator) {
                    ownerHere = true;
                    ownerDirection = p.getDirection();
                    
                    ownerX = Math.abs((int)xCoord - (int)p.getXPosition());
                    ownerY = Math.abs((int)yCoord - (int)p.getYPosition());
                    
                    ownerX = Math.abs((int)p.getXVelocity());
                    ownerY = Math.abs((int)p.getYVelocity());
                    
                }
            }
        if (!ownerHere || ownerDirection != direction || ownerX > 6 || ownerY > 10) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
