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
    
    int beansDelay = 60;
    int beansTimer = 30;
    
    int currentBean = 0;
    
    Beans[] beanList = new Beans[2];
    
    int rollX = 40;
    int rollY = 0;
    int rollDuration = 20;
    int rollSize = 8;
    int rollDamage = 10;
    int rollKnockback = 18;
    int rollStun = 25;
    int rollDelay = 30;
    
    int splashX = 20;
    int splashY = -30;
    int splashDuration = 20;
    int splashSize = 7;
    int splashDamage = 4;
    int splashKnockback = 18;
    int splashStun = 20;
    int splashDelay = 30;
    
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
        
        if (splashTimer < 30) {
            imageName = "carl_splash_" + splashTimer * 6 / 30 + ".png";
            splashTimer++;
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //roll
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
        //beans
        if (canAttack()) {
            beansTimer = 0;
            Beans b = new Beans(getXPosition()+10, getYPosition()+40, 
            getXVelocity() + direction * 25, getYVelocity() - 15, playerNumber, currentBean);
            if (beanList[currentBean] != null) {
                Beans oldBean = beanList[currentBean];
                ((Map) getWorld()).removeObject(oldBean);
            }
            beanList[currentBean] = b;
            currentBean = (++currentBean % 2);
            ((Map) getWorld()).addObject(b, 0, 0); // 0, 0 is fine because it will update anyway
            setAttackDelay(beansDelay);
        }
    }
    
    public void alternateAttack() {
        // splash
        
        if (canAttack()) {
            attacking = true;
            splashTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
        
            Hitbox h = new Hitbox(splashSize, playerNumber, splashDuration, splashDamage, direction, 
                splashKnockback, (int)getXPosition()+(splashX*direction), (int)getYPosition()+splashY, 
                splashStun);
            ((Map) getWorld()).addObject(h, getX()+(rollX*direction), getY()+rollY);
            setAttackDelay(splashDelay);
            attacking = false;

        }
    }
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void removeProjectiles() {
        for (Beans b : beanList) {
            ((Map) getWorld()).removeObject(b);
        }
    }
}
