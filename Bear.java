import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bear extends Player
{
    int biteTimer = 30;
    int beansTimer = 30;
    
    Beans[] beanList = new Beans[2];
    
    int currentBean = 0;
    
    public Bear(String inputType) {
        super("bear", inputType);
        JUMP_POWER = 20;
        SPEED = 1;
    }
    
    public void act() {
        super.act();
        
        if (biteTimer >= 30 && beansTimer >= 30) {
            setImage(new GreenfootImage("bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png"));
        }
        
        if (biteTimer < 30) {
            setImage(new GreenfootImage("bear_bite_" + biteTimer * 7 / 30 + ".png"));
            biteTimer++;
        }
        
        if (beansTimer < 30) {
            setImage(new GreenfootImage("bear_beans_" + beansTimer * 7 / 30 + ".png"));
            beansTimer++;
        }
                
        if (direction == -1) {
            getImage().mirrorHorizontally();
        }
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
            
            Hitbox h = new Hitbox(9, playerNumber, 20, 2, direction, 20, 
                (int)getX(), (int)getY());
            ((Map) getWorld()).addObject(h, getX()+(60*direction), getY()-32);
        
            attacking = false;
        }
        
    }
    
    public void updateCurrentBean(int update) {
        currentBean = update;
    }
    
    public void specialAttack() {
        //projectile (beans)
        
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
    
    public void removeProjectiles() {
        for (Beans b : beanList) {
            ((Map) getWorld()).removeObject(b);
        }
    }
}
