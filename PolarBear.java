import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Big, burly bear, buff bean by bean.
 * 
 * @author Devin Joe & Cam Welch Morgan
 * @version 2024-05-24
 */
public class PolarBear extends Player
{
    int punchTimer = 30;
    int beansTimer = 30;
    
    boolean punchActivate = false;
    
    int punchX = 60;
    int punchY = 5;
    int punchDuration = 20;
    int punchSize = 9;
    int punchDamage = 2;
    int punchKnockback = 16;
    int punchStun = 20;
    
    int punchDelay = 40;
    
    int currentBean = 0;
    
    public PolarBear(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 20;
        SPEED = 1;
        imageName = "polar_bear_walk_0.png";
        imageScale = 1;
    }
    
    public void act() {
        super.act();
        
        if (punchTimer >= 30 && beansTimer >= 30) {
            imageName = "polar_bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
            if (stun != 0) {
                imageName = "polar_bear_stun.png";
            }
        }
        
        if (punchTimer < 30) {
            imageName = "polar_bear_punch_" + punchTimer * 7 / 30 + ".png";
            punchTimer++;
        }
        
        if (punchTimer == 8 && canAttack()) {
            Hitbox h = new Hitbox(punchSize, playerNumber, punchDuration, punchDamage, direction, 
                punchKnockback, (int)getXPosition()+(punchX*direction), (int)getYPosition()+punchY, 
                punchStun);
            ((Map) getWorld()).addObject(h, getX()+(punchX*direction), getY()+punchY);
            setAttackDelay(punchDelay);
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //punch
        if (canAttack()) {
            attacking = true;
            punchTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
    
            attacking = false;
        }
        
    }
    
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void specialAttack() {
        //lighting
        
    }
    
    public void alternateAttack() {
        //lightning force field
    }
    
    public void removeProjectiles() {
        
    }
}
