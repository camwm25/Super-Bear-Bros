import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Window extends Projectile
{
    int throwerNumber;
    
    int boomTimer = 0;
    
    Player thrower;
    
    int windowTimer;
    
    int SELF_DAMAGE = 4;
    int DAMAGE = 10;
    
    /**
     * Act - do whatever the Window wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateVelocity();
        move();
        super.act();
        if (boomTimer == 0) {
            checkHit();
        }
        updateExplosionTimer();
        updateImage();
    }
    
    private void updateExplosionTimer() {
        if (boomTimer  >= 1) {
            boomTimer++;
        }
        if (boomTimer >= 18) {
            ((Map) getWorld()).removeObject(this);
        }
    }
    
    private void updateImage() {
        imageName = "window" + boomTimer / 3 + ".png";
    }
    
    
    public Window(double x, double y, double xVelocity, double yVelocity, int creator) {
        super(x, y, creator);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        
        throwerNumber = creator;
        
        imageName = "window0.png";
        imageScale = 1;
    }
    
    private void checkHit() {
        thrower = (Bear) ((Map) getWorld()).getPlayer(throwerNumber);
        
        //will explode upon seeing any players, hammers or lightsabers
        
        windowTimer++;
        if (windowTimer >= 600) {
            ((Map) getWorld()).removeObject(this);
        }
        else if (windowTimer > 20) {  // If the Window is ripe
            for (Player p: getIntersectingObjects(Player.class)) {
                p.setStun(30);
                if (throwerNumber == p.playerNumber) {
                    p.takeDamage(SELF_DAMAGE);
                    if (this.getXPosition() > p.getXPosition()) {
                        p.takeKnockback(-1, 10, 30);
                    }
                    else {
                        p.takeKnockback(1, 10, 30);
                    }
                }
                else {
                    p.takeDamage(DAMAGE);
                    if (this.getXPosition() > p.getXPosition()) {
                        p.takeKnockback(-1, 10, 45);
                    }
                    else {
                        p.takeKnockback(1, 10, 45);
                    }
                }
                boomTimer = 1;
            }
            for (Ground g : getIntersectingObjects(Ground.class)) {
                if (g.canFallThrough()) {
                    dropping = true;
                }
                
                else {
                    boomTimer = 1;
                }
            }
            for (Lightsaber l : getIntersectingObjects(Lightsaber.class)) {
                this.boomTimer = 1;
            }
        }
        
        
    }
    
    public boolean onGround() {
            return false;
        }
}
