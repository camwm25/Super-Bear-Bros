import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Big, burly bear, buff bean by bean.
 * 
 * @author Devin Joe & Cam Welch Morgan
 * @version 2024-05-24
 */
public class PolarBear extends Player
{
    int biteTimer = 30;
    int beansTimer = 30;
    
    public int biteX = 50;
    public int biteY = -30;
    
    Lightning[] beanList = new Lightning[2]; // This is obviously temporary
    
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
        
        if (biteTimer >= 30 && beansTimer >= 30) {
            imageName = "polar_bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
        }
        
        if (biteTimer < 30) {
            imageName = "polar_bear_bite_" + biteTimer * 7 / 30 + ".png";
            biteTimer++;
        }
        
        if (beansTimer < 30) {
            imageName = "polar_bear_beans_" + beansTimer * 7 / 30 + ".png";
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
            
            Hitbox h = new Hitbox(9, playerNumber, 20, 2, direction, 10, 
                (int)getXPosition()+ (biteX*direction), (int)getYPosition() + biteY, 20, 
                biteX*direction, biteY);
            ((Map) getWorld()).addObject(h, getX()+(biteX*direction), getY()+biteY);
        
            attacking = false;
        }
        
    }
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void specialAttack() {
        //projectile (lighting)
        /**
         * Temporary attack!!!
         * 
         * It is stupid!!!
         * 
         * Fix it!!!
         */
        if (canAttack()) {
            Lightning b = new Lightning(getXPosition(), getYPosition(), 
            getXVelocity() + direction * 25, getYVelocity() - 15, playerNumber, currentBean);
            if (beanList[currentBean] != null) {
                Lightning oldBean = beanList[currentBean];
                ((Map) getWorld()).removeObject(oldBean);
            }
            beanList[currentBean] = b;
            currentBean = (++currentBean % 2);
            ((Map) getWorld()).addObject(b, getX(), getY()); // 0, 0 is fine because it will update anyway
        }
        
    }
    
    public void alternateAttack() {
        
    }
    
    public void removeProjectiles() {
        for (Lightning b : beanList) {
            ((Map) getWorld()).removeObject(b);
        }
    }
}
