import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Polar Bear's Lightning.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lightning extends Weapon
{
    int lightningPhase = 0;
    /**
     * Act - do whatever the Lightning wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Lightning(int bearerNumber, int direction, double x, double y) {
        super(bearerNumber, direction, 0, x, y);
        imageName = "lightning_0.png";
        imageScale = 0.9;
        imageDirection = direction;
        imageTransparency = 0;
    }
    
    public void updateImage() {
        if (timer < 35) {
            imageName = "lightning_" + (timer-15)/4 + ".png";
        }
        else {
            imageName = "lightning_4.png";
        }
        lightningPhase = (timer-15)/4;
    }
    
    public void updatePosition(Player bearer) {
        switch(lightningPhase) {
            case 0:
                x = bearer.getXPosition() + bearer.getXVelocity() + 87*direction;
                break;
            case 1:
                x = bearer.getXPosition() + bearer.getXVelocity() + 111*direction;
                break;
            case 2:
                x = bearer.getXPosition() + bearer.getXVelocity() + 128*direction;
                break;
            case 3:
                x = bearer.getXPosition() + bearer.getXVelocity() + 141*direction;
                break;
            case 4:
                x = bearer.getXPosition() + bearer.getXVelocity() + 141*direction;
                break;
            default:
                x = bearer.getXPosition() + bearer.getXVelocity();
        }
    }
    
    public void act()
    {
        super.act();
        
        //setRotation(direction * timer * 6);
        
        Player bearer = ((Map) getWorld()).getPlayer(bearerNumber);
        
        y = bearer.getYPosition() + bearer.getYVelocity()+10;
        
        timer++;
        
        if (timer > 15) {
            updateImage();
            updatePosition(bearer);

            imageTransparency = 255;
            
            for (Player p: getIntersectingObjects(Player.class)) {
                if (p.playerNumber != bearerNumber) {
                    p.takeDamage(2);
                    p.takeKnockback(direction, 26);
                    p.setStun(25);
                }
            }
        }
        
        if (timer >= 35) {
            ((Map) getWorld()).removeObject(this);
        }
        
        
        
    }
}
