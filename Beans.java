import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beans here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Beans extends PhysicsObject
{
    int beanTimer = 0;
    int throwerNumber;
    Bear thrower;
    int beanNumber;
    int explosionTimer = 0;
    
    public Beans(double x, double y, double xVelocity, double yVelocity, int creator, int beanNumber) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        
        throwerNumber = creator;
        this.beanNumber = beanNumber;
        
        setImage(new GreenfootImage("beans0.png"));
    }
    
    /**
     * Act - do whatever the Beans wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateVelocity();
        move();
        updateScreenLocation();
        if (explosionTimer == 0) {
            checkHit();
        }
        updateImage();
    }
    
    private void updateImage() {
        setImage(new GreenfootImage("beans" + explosionTimer / 4 + ".png"));
        
        if (explosionTimer >= 1) {
            explosionTimer++;
        }

        if (explosionTimer >= 16) {
            ((Map) getWorld()).removeObject(this);
        }

    }
    
    private void updateVelocity() {
        if (onGround()) {
            yVelocity = 0;
            
            xVelocity *= FRICTION;
        }
        else {
            experienceTheWonderfulForceOfGravity();
            
            xVelocity *= DRAG;
        }
    }
    
    private void checkHit() {
        thrower = (Bear) ((Map) getWorld()).getPlayer(throwerNumber);
        
        beanTimer++;
        if (beanTimer == 600) {
            thrower.updateCurrentBean(beanNumber);
            ((Map) getWorld()).removeObject(this);
        }
        else if (beanTimer > 20) {
            for (Player p: getIntersectingObjects(Player.class)) {
                if (throwerNumber == p.playerNumber) {
                    p.takeDamage(6);
                    p.takeKnockback(0, 20);
                }
                else {
                    p.takeDamage(8);
                    p.takeKnockback(0, 30);
                }
                explosionTimer = 1;

                            }
        }
    }
}
