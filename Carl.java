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
        imageName = "bear_walk_0.png";
        imageScale = 1;
        defaultImage = new GreenfootImage("carl_swim_0");
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
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //bite
        if (canAttack()) {
            attacking = true;
            rollTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
        
            Hitbox h = new Hitbox(biteSize, playerNumber, biteDuration, biteDamage, direction, 
                biteKnockback, (int)getXPosition()+(biteX*direction), (int)getYPosition()+biteY, 
                biteStun);
            ((Map) getWorld()).addObject(h, getX()+(biteX*direction), getY()+biteY);
            setAttackDelay(biteDelay);
            attacking = false;

        }
        
    }
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void specialAttack() {
        //projectile (beans)
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
        // projectile (window)
        if (canAttack()) {
            windowTimer = 0;
            Window w = new Window(getXPosition(), getYPosition()+50, 
            0, getYVelocity() - 30, playerNumber);
            if (beanList[0] != null) {
                Window oldWindow = windowList[0];
                ((Map) getWorld()).removeObject(oldWindow);
            }
            windowList[0] = w;
            ((Map) getWorld()).addObject(w, 0, 0); // 0, 0 is fine because it will update anyway
            setAttackDelay(windowDelay);
        }
    }
    
    public void removeProjectiles() {
        for (Beans b : beanList) {
            ((Map) getWorld()).removeObject(b);
        }
        for (Window w : windowList) {
            ((Map) getWorld()).removeObject(w);
        }
    }
}
