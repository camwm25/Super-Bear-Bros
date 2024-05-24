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
    
    int changeX = 0;
    int changeY = 0;
    
    double xCoord;
    double yCoord;
    /**
     * Act - do whatever the hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        checkHit();
        
        imageTransparency = 0;
        //imageTransparency = 255;
    }
    
    public Hitbox(int size, int playerCreator, int time, int damage, 
                    int direction, int knockback, double x, double y, int stun,
                    int changeX, int changeY) {
        this(size, playerCreator, time, damage, direction, knockback, x, y, stun);
    }
    
    public Hitbox(int size, int playerCreator, int time, int damage, 
                    int direction, int knockback, double x, double y, int stun) {
        super(x, y);
        imageName = "hitbox.png";
        imageScale = 2.0/size;
        
        //imageTransparency = 0;
        
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
                    
                    ownerX = Math.abs((int)xCoord - ((int)p.getXPosition()+changeX));
                    ownerY = Math.abs((int)yCoord - ((int)p.getYPosition()+changeY));
                    
                    ownerX = Math.abs((int)p.getXVelocity());
                    ownerY = Math.abs((int)p.getYVelocity());
                    
                }
            }
        if (!ownerHere || ownerDirection != direction || ownerX > 6 || ownerY > 20) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
