import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Invisible hitboxes for projectile deflecting/absorbing attacks.
 * Currently the same as Hitbox.class. WIll be changed to be different for projectiles.
 * @author Cam Welch Morgan
 * @version 2024-01-30
 */
public class ProjectileBox extends InvisibleObject
{
    int size;
    int power;
    int attackTime;
    int direction;
    int distance;
    int timer = 0;
    int stunAmount;
    /**
     * Act - do whatever the hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public ProjectileBox(int size, int playerCreator, int time, int damage, 
                    int direction, int knockback, double x, double y, int stun) {
        super(playerCreator, x, y, direction);
        imageName = "hitbox.png";
        imageScale = 2.0/size;
        
        imageTransparency = 0;
        
        this.size = size;
        creator = playerCreator;
        attackTime = time;
        power = damage;
        this.direction = direction;
        distance = knockback;
        stunAmount = stun;
    }
    
    public void checkHit() {
        if (timer < attackTime) {
            timer++;
            for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber != creator) {
                    p.takeDamage(power);
                    p.takeKnockback(direction, distance);
                    p.setStun(stunAmount);
                }
            }
            checkOwner();
        }
        else {
            ((Map) getWorld()).removeObject(this);
        }
        
    }
}
