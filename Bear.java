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
    int biteSize = 8;
    int biteDamage = 3;
    int biteKnockback = 8;
    int biteStun = 20;
    
    Beans[] beanList = new Beans[2];
    
    int currentBean = 0;
    
    public Bear(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 20;
        SPEED = 1;
        imageName = "bear_walk_0.png";
        imageScale = 1;
    }
    
    public void act() {
        super.act();
        
        if (biteTimer >= 30 && beansTimer >= 30) {
            imageName = "bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
        }
        
        if (biteTimer < 30) {
            imageName = "bear_bite_" + biteTimer * 7 / 30 + ".png";
            biteTimer++;
        }
        
        if (beansTimer < 30) {
            imageName = "bear_beans_" + beansTimer * 7 / 30 + ".png";
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
                biteStun, 0, biteY);
            ((Map) getWorld()).addObject(h, getX()+(biteX*direction), getY()+biteY);
            attacking = false;
        }
        
    }
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void specialAttack() {
        //projectile (beans)
        if (canAttack()) {
            Beans b = new Beans(getXPosition(), getYPosition(), 
            getXVelocity() + direction * 25, getYVelocity() - 15, playerNumber, currentBean);
            if (beanList[currentBean] != null) {
                Beans oldBean = beanList[currentBean];
                ((Map) getWorld()).removeObject(oldBean);
            }
            beanList[currentBean] = b;
            currentBean = (++currentBean % 2);
            ((Map) getWorld()).addObject(b, getX(), getY()); // 0, 0 is fine because it will update anyway
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
