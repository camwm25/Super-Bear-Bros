import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Scary woman with a big hammer.
 * 
 * @author Devin Joe
 * @version 2024-01-30
 */
public class GordonsMom extends Player
{
    int stareTimer = 20;
    int hammerTimer = 30;
    
    int biteY = -30;
    
    Hammer[] holder = new Hammer[1];
    
    public GordonsMom(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 20;
        SPEED = 1.2;
        imageName = "gordons_mom_walk_0.png";
        imageScale = 1;
    }
    
    public void act() {
        super.act();
        
        if (stareTimer >= 15 && hammerTimer >= 30) {
            imageName = "gordons_mom_walk_" + (((int) x/10) % 4 + 4) % 4 + ".png";
        }
        
        if (stareTimer < 15) {
            imageName = "gordons_mom_stare_" + stareTimer * 3 / 15 + ".png";
            stareTimer++;
        }
        
        if (hammerTimer < 30) {
            imageName = "gordons_mom_hammer_" + hammerTimer * 5 / 30 + ".png";
            hammerTimer++;
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        if (canAttack()) {
            attacking = true;
            stareTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
            
            Hitbox h = new Hitbox(12, playerNumber, 10, 2, direction, 8,
                (int)getXPosition(), (int)getYPosition()+biteY, 15,
                0, biteY);
            ((Map) getWorld()).addObject(h, getX()+(35*direction), getY()-40);
        
            attacking = false;
        }
        
    }
    
    public void specialAttack() {
        //hammer
        if (canAttack()) {
            attacking = true;
            Hammer h = new Hammer(playerNumber, direction, getXPosition(), getYPosition());
            holder[0] = h;
            double hammerX = getXPosition() + getXVelocity();
            double hammerY = getYPosition() + getYVelocity();

            ((Map) getWorld()).addObject(h, (int)hammerX, (int)hammerY);
            attacking = false;
        }
    }
    
    public void removeProjectiles() {
        ((Map) getWorld()).removeObject(holder[0]);
    }
}
