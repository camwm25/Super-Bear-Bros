import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class Lightsaber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hammer extends ForegroundObject
{
    int bearerNumber;
    int direction;    
    int timer = 3;
    
    GreenfootImage hammer;
    
    public Hammer(int bearerNumber, int direction) {
        this.bearerNumber = bearerNumber;
        this.direction = direction;
        hammer = new GreenfootImage("hammer.png");
        hammer.scale(3*hammer.getWidth() / 4, 3*hammer.getHeight() / 4);
        hammer.setTransparency(0);
        setImage(hammer);
        turn(90 * direction);
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
            -3*direction*timer*timer/2 + 75* direction*timer/2- direction*75;
        
        y = bearer.getYPosition() + bearer.getYVelocity();
        
        
        //x = bearer.getXPosition() + bearer.getXVelocity()
            //+ 70 * Math.sin(Math.toRadians(getRotation()))
            //+ direction * 10;
        //y = bearer.getYPosition() + bearer.getYVelocity()
            //+ 70 * -Math.cos(Math.toRadians(getRotation()))
            //+ 5;
        
        timer++;
        if (timer > 5) {
            hammer.setTransparency(255);
            setImage(hammer);
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
