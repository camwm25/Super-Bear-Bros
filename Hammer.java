import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Gordon's Mom's Hammer.
 * 
 * @author Cam Welch Morgan and Devin Joe
 * @version 2024-05-16
 */
public class Hammer extends Weapon
{
    
    GreenfootImage hammer;
    
    public Hammer(int bearerNumber, int direction, double x, double y) {
        super(bearerNumber, direction, 3, x, y);
        imageName = "hammer.png";
        imageScale = 0.75;
        imageDirection = direction;
        // hammer.setTransparency(0);
        // turn(90 * direction);
    }
    
    /**
     * Act - do whatever the Lightsaber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        //setRotation(direction * timer * 6);
        
        Player bearer = ((Map) getWorld()).getPlayer(bearerNumber);
        x = bearer.getXPosition() + bearer.getXVelocity() + 
            -3*direction*timer*timer/2 + 75*direction*timer/2 - direction*75;
        
        y = bearer.getYPosition() + bearer.getYVelocity();
        
        timer++;
        if (timer > 5) {
            // hammer.setTransparency(255);
            // setImage(hammer);
            for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber != bearerNumber) {
                    p.takeDamage(1);
                    p.takeKnockback(direction, 23);
                }
            }
        }
        
        
        if (timer > 20) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
