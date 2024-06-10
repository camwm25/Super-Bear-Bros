import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Big, burly bear, buff bean by bean.
 * 
 * @author Cam Welch Morgan
 * @version 2024-05-16
 */
public class Bear extends Player
{
    int biteTimer = 30;
    int beansTimer = 30;
    
    int biteX = 50;
    int biteY = -30;
    int biteDuration = 20;
    int biteSize = 9;
    int biteDamage = 3;
    int biteKnockback = 12;
    int biteStun = 20;
    int biteDelay = 35;
    
    int beansDelay = 60;
    
    Beans[] beanList = new Beans[2];
    
    int currentBean = 0;
    
    public Bear(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 20;
        SPEED = 1;
        imageName = "bear_walk_0.png";
        imageScale = 1;
    }
    
    public Bear(int playerNumber, double x, double y, String[] controls) {
        super(playerNumber, x, y, controls);
        JUMP_POWER = 20;
        SPEED = 1;
        imageName = "bear_walk_0.png";
        imageScale = 1;
    }
    
    public void act() {
        super.act();
        
        if (biteTimer >= 30 && beansTimer >= 20) {
            imageName = "bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
            if (stun != 0) {
                imageName = "bear_stun.png";
            }
        }
        
        if (biteTimer < 30) {
            imageName = "bear_bite_" + biteTimer * 7 / 30 + ".png";
            biteTimer++;
        }
        
        if (beansTimer < 20) {
            imageName = "bear_beans_" + beansTimer * 4 / 20 + ".png";
            beansTimer++;
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //bite
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
        // this will be window attack which i cannot add 
    }
    
    public void removeProjectiles() {
        for (Beans b : beanList) {
            ((Map) getWorld()).removeObject(b);
        }
    }
}
