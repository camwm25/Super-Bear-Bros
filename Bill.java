import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bill here.
 * 
 * @author Devin Joe
 * @version 2024-01-30
 */
public class Bill extends Player
{
    int biteTimer = 20;
    int lightsaberTimer = 30;
    
    Lightsaber[] holder = new Lightsaber[1];
    
    public Bill(String inputType) {
        super("bill", inputType);
        JUMP_POWER = 22;
        SPEED = 1.1;
    }
    
    public void act() {
        super.act();
        
        if (xVelocity < 0) {
            getImage().mirrorHorizontally();
        }
        
        if (biteTimer >= 15 && lightsaberTimer >= 30) {
            setImage(new GreenfootImage("bill_walk_" + (((int) x/10) % 4 + 4) % 4 + ".png"));
        }
        
        if (biteTimer < 15) {
            setImage(new GreenfootImage("bill_bite_" + biteTimer * 3 / 15 + ".png"));
            biteTimer++;
        }
        
        if (lightsaberTimer < 30) {
            setImage(new GreenfootImage("bill_lightsaber_" + lightsaberTimer * 5 / 30 + ".png"));
            lightsaberTimer++;
        }
                
        if (direction == -1) {
            getImage().mirrorHorizontally();
        }
    }
    
    public void normalAttack() {
        if (!dropping && (xVelocity < 150 || xVelocity > -150)) {
            attacking = true;
            biteTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
            
            Hitbox h = new Hitbox(10, playerNumber, 10, 3, direction, 10,
                 (int)getX(), (int)getY());
            ((Map) getWorld()).addObject(h, getX()+(35*direction), getY()-40);
        
            attacking = false;
        }
        
    }
    
    public void specialAttack() {
        //lightsaber
        
        attacking = true;
        Lightsaber l = new Lightsaber(playerNumber, direction);
        holder[0] = l;
        double lightsaberX = getXPosition() + getXVelocity()
            + 70 * Math.sin(Math.toRadians(direction*30))
            + (direction * 10);
        double lightsaberY = getYPosition() + getYVelocity()
            + 70 * -Math.cos(Math.toRadians(direction*30))
            + 5;
        
        ((Map) getWorld()).addObject(l, (int)lightsaberX, (int)lightsaberY); // The location is arbitrary.
        attacking = false;
    }
    
    public void removeProjectiles() {
        ((Map) getWorld()).removeObject(holder[0]);
    }
}
