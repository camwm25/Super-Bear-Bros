import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends ForegroundObject
{
    private int velocity = 20;
    private Player shooter;
    
    private int direction;
    private int throwerNumber;
    
    final int SELF_DAMAGE = 3;    // Damage done if the thrower touches the buller
    final int DAMAGE = 4;         // Damage done if someone else touches the bullet
    
    private int bulletTimer = 0;
    private int explosionTimer = 0;
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        bulletMove();
        if (explosionTimer == 0) {
            checkHit();
        }
        updateBulletTimer();
        updateImage();
    }
    public Bullet(double x, double y, int direction, int thrower) {
        super(x, y);
        
        imageName = "beans0.png";
        throwerNumber = thrower;
        this.direction = direction;
        imageScale = 0.5;
    }
    private void bulletMove() {
        x += velocity*direction;
    }
    private void updateBulletTimer() {
        if (explosionTimer >= 1) {
            explosionTimer++;
        }
        if (explosionTimer >= 8) {
            ((Map) getWorld()).removeObject(this);
        }
    }
    
    private void updateImage() {
        imageName = "beans" + explosionTimer / 2 + ".png";
    }
    private void checkHit() {
        shooter = (GordonsMom) ((Map) getWorld()).getPlayer(throwerNumber);
        
        //will explode upon seeing any players, hammers or beans
        
        bulletTimer++;
        if (bulletTimer >= 20) {
            ((Map) getWorld()).removeObject(this);
        }
        else if (bulletTimer > 5) {  // If the bullet is ripe
            for (Player p: getIntersectingObjects(Player.class)) {
                
                if (throwerNumber == p.playerNumber) {
                    //p.setStun(20);
                    //p.takeDamage(SELF_DAMAGE);
                    //p.takeKnockback(direction, 8, 25);
                    //explosionTimer = 1;
                }
                else {
                    p.setStun(20);
                    p.takeDamage(DAMAGE);
                    p.takeKnockback(direction, 8, 25);
                    explosionTimer = 1;
                }
            }
            for (Hammer h : getIntersectingObjects(Hammer.class)) {
                this.explosionTimer = 4;
            }
            for (Lightsaber l : getIntersectingObjects(Lightsaber.class)) {
                this.explosionTimer = 4;
            }
            for (Ground g : getIntersectingObjects(Ground.class)) {
                this.explosionTimer = 4;
            }
        }
    }
}

