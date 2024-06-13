import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Big, burly bear, buff bean by bean.
 * 
 * @author Cam Welch Morgan
 * @version 2024-05-16
 */
public class Carl extends Player
{
    
    int swimmingTimer = 0;
    
    int rollTimer = 30;
    
    int rollX = 40;
    int rollY = 0;
    int rollDuration = 20;
    int rollSize = 9;
    int rollDamage = 10;
    int rollKnockback = 18;
    int rollStun = 25;
    int rollDelay = 30;
    
    int splashTimer = 30;
    
    public Carl(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 19;
        SPEED = 1.2;
        imageName = "carl_swim_0.png";
        imageScale = 1;
        defaultImage = new GreenfootImage("carl_swim_0.png");
    }
    
    public Carl(int playerNumber, double x, double y, String[] controls) {
        super(playerNumber, x, y, controls);
        JUMP_POWER = 19;
        SPEED = 1.2;
        imageName = "carl_swim_0.png";
        imageScale = 1;
        defaultImage = new GreenfootImage("carl_swim_0.png");
    }
    
    public void act() {
        super.act();
        
        if (splashTimer >= 30 && rollTimer >= 30) {
            imageName = "carl_swim_" + (swimmingTimer/15) + ".png";
            swimmingTimer++;
            if (swimmingTimer == 60) {
                swimmingTimer = 0;
            }
            if (stun != 0) {
                imageName = "carl_stun.png";
            }
        }
        
        if (rollTimer < 30) {
            imageName = "carl_roll_" + rollTimer * 5 / 30 + ".png";
            rollTimer++;
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //bite
        if (canAttack()) {
            attacking = true;
            rollTimer = 0;
            
            if (direction == 1) {
                xVelocity = 20;
            }
            else {
                xVelocity = -20;
            }
        
            Hitbox h = new Hitbox(rollSize, playerNumber, rollDuration, rollDamage, direction, 
                rollKnockback, (int)getXPosition()+(rollX*direction), (int)getYPosition()+rollY, 
                rollStun);
            ((Map) getWorld()).addObject(h, getX()+(rollX*direction), getY()+rollY);
            setAttackDelay(rollDelay);
            attacking = false;

        }
        
    }
    
    public void specialAttack() {
        //projectile (beans)
        
        
    }
    
    public void alternateAttack() {
        // projectile (window)
        
    }
    
    public void removeProjectiles() {

    }
}
