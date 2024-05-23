import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Bill's Lightsaber.
 * 
 * @author Cam Welch Morgan and Devin Joe
 * @version 2024-05-16
 */
public class Lightsaber extends Weapon
{
   
    GreenfootImage lightsaber;
    
    public Lightsaber(int bearerNumber, int direction, double x, double y) {
        super(bearerNumber, direction, 7, x, y);
        imageName = "lightsaber.png";
        imageScale = 1;
        imageTransparency = 0;
        turn(90 * direction);
    }
    
    /**
     * Act - do whatever the Lightsaber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
        setRotation(direction * timer * 6);
        
        Player bearer = ((Map) getWorld()).getPlayer(bearerNumber);
        x = bearer.getXPosition() + bearer.getXVelocity()
            + 70 * Math.sin(Math.toRadians(getRotation()))
            + direction * 10;
        y = bearer.getYPosition() + bearer.getYVelocity()
            + 70 * -Math.cos(Math.toRadians(getRotation()))
            + 5;
        
        timer++;
        if (timer > 9) {
            imageTransparency = 255;
            for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber != bearerNumber) {
                    p.takeDamage(1);
                    p.takeKnockback(direction, 27);
                    p.setStun(20);
                }
            }
        }
        
        
        if (timer > 24) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
