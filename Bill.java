import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bill, our lightsaber weilding vampish friend.
 * 
 * @author Devin Joe
 * @version 2024-01-30
 */
public class Bill extends Player
{
    int biteTimer = 20;
    int lightsaberTimer = 30;
    
    int biteY = -30;
    int biteX = 10;
    int biteDuration = 10;
    int biteSize = 11;
    int biteDamage = 3;
    int biteKnockback = 12;
    int biteStun = 15;
    int biteDelay = 35;
    
    int lightsaberDelay = 60;
    
    Lightsaber[] holder = new Lightsaber[1];
    
    public Bill(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 22;
        SPEED = 1.1;
        imageName = "bill_walk_0.png";
        imageScale = 1;
        
        defaultImage = new GreenfootImage("bill_walk_0.png");
    }
    
    public Bill(int playerNumber, double x, double y, String[] controls) {
        super(playerNumber, x, y, controls);
        JUMP_POWER = 22;
        SPEED = 1.1;
        imageName = "bill_walk_0.png";
        imageScale = 1;
    }

    
    public void act() {
        super.act();
        
        if (biteTimer >= 15 && lightsaberTimer >= 30) {
            imageName = "bill_walk_" + (((int) x/10) % 4 + 4) % 4 + ".png";
            if (stun != 0) {
                imageName = "bill_stun.png";
            }
        }
        
        if (biteTimer < 15) {
            imageName = "bill_bite_" + biteTimer * 3 / 15 + ".png";
            biteTimer++;
        }
        
        if (lightsaberTimer < 30) {
            imageName = "bill_lightsaber_" + lightsaberTimer * 5 / 30 + ".png";
            lightsaberTimer++;
        }
                
        imageDirection = direction;
    }
    
    public void normalAttack() {
        if (canAttack()) {
            attacking = true;
            biteTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
            
            Hitbox h = new Hitbox(biteSize, playerNumber, biteDuration, biteDamage, direction, 
                biteKnockback, (int)getXPosition()+(biteX*direction), (int)getYPosition()+biteY, 
                biteStun);
            ((Map) getWorld()).addObject(h, getX()+(35*direction), getY()-40);
        
            setAttackDelay(biteDelay);
            attacking = false;
        }
        
    }
    
    public void specialAttack() {
        //lightsaber
        if (canAttack()) {
            attacking = true;
            Lightsaber l = new Lightsaber(playerNumber, direction, getXPosition(), getYPosition());
            holder[0] = l;
            double lightsaberX = getXPosition() + getXVelocity()
            + 70 * Math.sin(Math.toRadians(direction*30))
            + (direction * 10);
            double lightsaberY = getYPosition() + getYVelocity()
            + 70 * -Math.cos(Math.toRadians(direction*30))
            + 5;
        
            ((Map) getWorld()).addObject(l, (int)lightsaberX, (int)lightsaberY); // The location is arbitrary.
            setAttackDelay(lightsaberDelay);
            attacking = false;
        }
        
    }
    
    public void alternateAttack() {
        // transform into bat
    }
    
    public void removeProjectiles() {
        ((Map) getWorld()).removeObject(holder[0]);
    }
}
