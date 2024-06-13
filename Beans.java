import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bear's trusty bean can(s).
 * 
 * @author Devin Joe & Cam Welch Morgan 
 * @version 2024-05-16
 */
public class Beans extends Projectile
{
    int beanTimer = 0;
    Bear thrower;
    
    // Some parameters
    final int SELF_DAMAGE = 6;    // Damage done if the thrower touches the beans
    final int DAMAGE = 8;         // Damage done if someone else touches the beans
    
    /*
     * Identifies this Beans with a unique number to prevent having too many
     * beans in the map.
     */
    int beanNumber;
    int explosionTimer = 0;
    
    public Beans(double x, double y, double xVelocity, double yVelocity, int creator, int beanNumber) {
        super(x, y, creator);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        
        throwerNumber = creator;
        this.beanNumber = beanNumber;
        
        imageName = "beans0.png";
        imageScale = 1;
    }
    
    /**
     * Act - do whatever the Beans wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateVelocity();
        move();
        super.act();
        if (explosionTimer == 0) {
            checkHit();
        }
        updateImage();
        updateExplosionTimer();
    }
    
    private void updateExplosionTimer() {
        if (explosionTimer >= 1) {
            explosionTimer++;
        }
        if (explosionTimer >= 16) {
            ((Map) getWorld()).removeObject(this);
        }
    }
    
    private void updateImage() {
        imageName = "beans" + explosionTimer / 4 + ".png";
    }
    
    private void checkHit() {
        thrower = (Bear) ((Map) getWorld()).getPlayer(throwerNumber);
        
        //will explode upon seeing any players, hammers or lightsabers
        
        beanTimer++;
        if (beanTimer >= 600) {
            thrower.updateCurrentBean(beanNumber);
            ((Map) getWorld()).removeObject(this);
        }
        else if (beanTimer > 20) {  // If the Beans is ripe
            for (Player p: getIntersectingObjects(Player.class)) {
                p.setStun(30);
                if (throwerNumber == p.playerNumber) {
                    p.takeDamage(SELF_DAMAGE);
                    if (this.getXPosition() > p.getXPosition()) {
                        p.takeKnockback(-1, 10, 35);
                    }
                    else {
                        p.takeKnockback(1, 10, 35);
                    }
                }
                else {
                    p.takeDamage(DAMAGE);
                    if (this.getXPosition() > p.getXPosition()) {
                        p.takeKnockback(-1, 10, 35);
                    }
                    else {
                        p.takeKnockback(1, 10, 35);
                    }
                }
                explosionTimer = 1;
            }
            for (Hammer h : getIntersectingObjects(Hammer.class)) {
                this.explosionTimer = 1;
            }
        }
    }
}

