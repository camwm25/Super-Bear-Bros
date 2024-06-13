import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Electrical powers and a cool headband. May or may not be related to Bear.
 * 
 * @author Devin Joe & Cam Welch Morgan
 * @version 2024-05-24
 */
public class PolarBear extends Player
{
    int punchTimer = 30;
    int fieldTimer = 40;
    int lightningTimer = 42;
    
    public boolean projectileBoxGone = false;
    
    boolean punchActivate = false;
    
    int punchX = 60;
    int punchY = 5;
    int punchDuration = 20;
    int punchSize = 7;
    int punchDamage = 3;
    int punchKnockback = 16;
    int punchStun = 30;
    
    double fieldSize = 3.25;
    int fieldHealFactor = 2;
    
    ProjectileBox[] boxHolder = new ProjectileBox[1];
    Lightning[] lightningHolder = new Lightning[1];
    
    int punchDelay = 40;
    int fieldDelay = 60;
    int lightningDelay = 60;
    
    public PolarBear(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 21;
        SPEED = 1.05;
        imageName = "polar_bear_walk_0.png";
        imageScale = 1;
        
        defaultImage = new GreenfootImage("polar_bear_walk_0.png");
    }
    
    public PolarBear(int playerNumber, double x, double y, String[] controls) {
        super(playerNumber, x, y, controls);
        JUMP_POWER = 21;
        SPEED = 1.05;
        imageName = "polar_bear_walk_0.png";
        imageScale = 1;
        
        defaultImage = new GreenfootImage("polar_bear_walk_0.png");
    }
    
    public void act() {
        super.act();
        
        if (punchTimer >= 30 && fieldTimer >= 40 && lightningTimer >= 42) {
            imageName = "polar_bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
            if (stun != 0) {
                imageName = "polar_bear_stun.png";
            }
        }
        
        if (punchTimer < 30) {
            imageName = "polar_bear_punch_" + punchTimer * 7 / 30 + ".png";
            punchTimer++;
        }
        
        if (fieldTimer < 40) {
            if (fieldTimer < 9) {
                imageName = "electric_shield_" + (fieldTimer*3/8) +".png";
            }
            else if (fieldTimer < 17) {
                imageName = "electric_shield_" + (3 - (fieldTimer-8)*3/8) +".png";
            }
            else {
                imageName = "polar_bear_walk_" + (((int) x/10) % 16 + 16) % 16 + ".png";
            }
            fieldTimer++;
        }
        
        if (lightningTimer < 42) {
            imageName = "polar_bear_lightning_" + lightningTimer / 7 + ".png";
            lightningTimer++;
        }
        
        if (punchTimer == 5 && canAttack()) {
            Hitbox h = new Hitbox(punchSize, playerNumber, punchDuration, punchDamage, direction, 
                punchKnockback, (int)getXPosition()+(punchX*direction), (int)getYPosition()+punchY, 
                punchStun);
            ((Map) getWorld()).addObject(h, getX()+(punchX*direction), getY()+punchY);
            setAttackDelay(punchDelay);
        }
        
        if (fieldTimer == 8 && checkHeld()) {
            fieldTimer--;
            if (projectileBoxGone) {
                fieldTimer = 9;
            }
            if (stun > 0) {
                fieldTimer = 10;
            }
        }
        
        
        if (fieldTimer == 10) {
            setAttackDelay(fieldDelay);
            ((Map) getWorld()).removeObject(boxHolder[0]);
            projectileBoxGone = false;
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
    
    public void specialAttack() {
        //lighting
        if (canAttack()) {
            attacking = true;
            lightningTimer = 0;
            Lightning l = new Lightning(playerNumber, direction, getXPosition(), getYPosition());
            lightningHolder[0] = l;
            double lightningX = getXPosition() + getXVelocity();
            double lightningY = getYPosition() + getYVelocity();

            ((Map) getWorld()).addObject(l, (int)lightningX, (int)lightningY);
            setAttackDelay(lightningDelay);
            attacking = false;
        }
    }
    
    public void alternateAttack() {
        if (canAttack() && fieldTimer >= 40) {
            attacking = true;
            fieldTimer = 0;
            
            if (direction == 1) {
                xVelocity = 0;
            }
            else {
                xVelocity = 0;
            }
            
            ProjectileBox p = new ProjectileBox(fieldSize, playerNumber, direction, 
                (int)getXPosition(), (int)getYPosition(), false, fieldHealFactor);
            boxHolder[0] = p;
            ((Map) getWorld()).addObject(p, getX(), getY());

    
            attacking = false;
        }
    }
    
    public boolean checkHeld() {
        if (Greenfoot.isKeyDown(leftKey) || Greenfoot.isKeyDown(rightKey) || Greenfoot.isKeyDown(upKey) || Greenfoot.isKeyDown(downKey)) {
            return false;
        }
        else if (Greenfoot.isKeyDown(this.alternateAttack)) {
            return true;
        } 
        return false;
    }
    
    public void updateCurrentBean(int update) {
        
    }
    
    public void removeProjectiles() {
        ((Map) getWorld()).removeObject(lightningHolder[0]);
    }
}
