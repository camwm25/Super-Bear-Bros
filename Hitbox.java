import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Invisible hitboxes for close range attacks.
 * 
 * @author Cam Welch Morgan
 * @version 2024-01-30
 */
public class Hitbox extends ForegroundObject
{
    int size;
    int creator;
    int power;
    int attackTime;
    int direction;
    int distance;
    int timer = 0;
    int stunAmount;
    
    int xCoord;
    int yCoord;
    /**
     * Act - do whatever the hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkHit();
    }
    
    public Hitbox(int size, int playerCreator, int time, int damage, 
                    int direction, int knockback, int x, int y, int stun) {
        GreenfootImage image = new GreenfootImage("hitbox.png");
        image.scale(2*image.getWidth() / size, 2*image.getHeight() / size);
        image.setTransparency(0);
        setImage(image);
        
        
        this.size = size;
        creator = playerCreator;
        attackTime = time;
        power = damage;
        this.direction = direction;
        distance = knockback;
        stunAmount = stun;
        
        xCoord = x;
        yCoord = y;
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
    
    public void checkOwner() {
        boolean ownerHere = false;
        int ownerDirection = direction;
        
        int ownerX = 0;
        int ownerY = 0;
        
        for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber == creator) {
                    ownerHere = true;
                    ownerDirection = p.getDirection();
                    
                    ownerX = Math.abs(xCoord - (int)p.getX());
                    ownerY = Math.abs(yCoord - (int)p.getY());
                    
                }
            }
        if (!ownerHere || ownerDirection != direction || ownerX > 40 || ownerY > 20) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
