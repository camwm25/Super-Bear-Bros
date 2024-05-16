import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class Lightsaber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lightsaber extends ForegroundObject
{
    int bearerNumber;
    int direction;    
    int timer = 7;
    
    GreenfootImage lightsaber;
    
    public Lightsaber(int bearerNumber, int direction) {
        this.bearerNumber = bearerNumber;
        this.direction = direction;
        lightsaber = new GreenfootImage("lightsaber.png");
        lightsaber.setTransparency(0);
        setImage(lightsaber);
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
            lightsaber.setTransparency(255);
            setImage(lightsaber);
            for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber != bearerNumber) {
                    p.takeDamage(1);
                    p.takeKnockback(direction, 27);
                }
            }
        }
        
        
        if (timer > 24) {
            ((Map) getWorld()).removeObject(this);
        }
    }
}
