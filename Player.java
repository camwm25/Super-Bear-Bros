import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends PhysicsObject
{
    double JUMP_POWER;
    double SPEED;
    
    public int playerNumber;
    
    int COOLDOWN = 60;
    
    String upKey;
    String downKey;
    String leftKey;
    String rightKey;
    
    String normalAttack;
    String specialAttack;
    
    int direction = 1;
    
    int attackDelay = 0;
    
    boolean attacking = false;
    boolean dropping = false;
    boolean inGround = false;
    
    public Player(String character, String inputKeys) {        
        switch (inputKeys) {
            case "IJKL":
                upKey = "I";
                downKey = "K";
                leftKey = "J";
                rightKey = "L";
                normalAttack = "U";
                specialAttack = "O";
                playerNumber = 2;
                break;
            case "WASD":
            default:
                upKey = "W";
                downKey = "S";
                leftKey = "A";
                rightKey = "D";
                normalAttack = "Q";
                specialAttack = "E";
                playerNumber = 1;
                break;
        }
        
        if (playerNumber == 1) {
            x = -200;
            y = 200;
            direction = 1;
        }
        else {
            x = 200;
            y = 200;
            direction = -1;
        }
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        updateVelocity();
        move();
        updateScreenLocation();
        updateDrop();
        fixOverlap();
        checkAttack();
        checkInVoid();
    }
    
    private void collideWithPlayers() {
        for (Player p : getIntersectingObjects(Player.class)) {
            double xDifference = p.getXPosition() - x;
            
            if (Math.abs(xDifference) < (getImage().getWidth() + p.getImage().getWidth()) * 0.4) {
                if (xDifference > 0) {
                    xVelocity -= 0.5;
                }
                else {
                    xVelocity += 0.5;
                }
            }
        }
    }
    
    private void updateVelocity() {
        if (onGround()) {
            if (dropping) {
                experienceTheWonderfulForceOfGravity();
            }
            else {
                yVelocity = 0;
            }
            
            if (Greenfoot.isKeyDown(upKey)) {
                yVelocity = -JUMP_POWER;
            }
            
            xVelocity *= FRICTION;
        }
        else {
            experienceTheWonderfulForceOfGravity();
            
            xVelocity *= DRAG;
        }
        
        if (Greenfoot.isKeyDown(leftKey)) {
            xVelocity -= SPEED;
            direction = -1;
        }
        
        if (Greenfoot.isKeyDown(rightKey)) {
            xVelocity += SPEED;
            direction = 1;
        }
        
        collideWithPlayers();
    }
    
    private void checkAttack() {
        if (attackDelay == 0) {
            if (Greenfoot.isKeyDown(normalAttack)) {
                normalAttack();
                attackDelay = COOLDOWN;
            }
            if (Greenfoot.isKeyDown(specialAttack)) {
                specialAttack();
                attackDelay = COOLDOWN;
            } 
            
        }
        else if (attackDelay > 0){
            attackDelay--;
        }
        
    }
    
    protected void move() {
        if (!attacking) {
            super.move();
        }
    }
    
    private void updateDrop() {
        if (onGround() && !onBaseLayerGround() && Greenfoot.isKeyDown(downKey)) {
            dropping = true;
            inGround = true;
        }
        
        if (dropping) {
            if (onGround() && !inGround) {
                dropping = false;
            }
            
            if (inGround && !onGround()) {
                inGround = false;
            }
        }
    }
    
    private void fixOverlap() {
        if (onGround() && !inGround) {
            
            for (int i = 0; i < 20; i++) {
                if (onGround()) {
                    y -= 1;
                    updateScreenLocation();
                } else {
                    break;
                }
            }
            
            y += 1;
            updateScreenLocation();
        }
    }
    
    private void checkInVoid() {
        if (y > 3000) {
            ((Map) getWorld()).setCamFocus(3 - playerNumber);
        }
        if (y > 10000) {
            takeDamage(1000);
        }
    }
    
    public void takeKnockback(int knockDirection, int knockDistance) {
        switch (knockDirection) {
            case 1:
                xVelocity = knockDistance;
                yVelocity = -0.5*knockDistance;
                break;
            case -1:
                xVelocity = -knockDistance;
                yVelocity = -0.5*knockDistance;
                break;
            case 0:
                xVelocity = -direction * knockDistance;
                yVelocity = -0.5*knockDistance;
        }
    }
    
    public void takeDamage(int damage) {
        ((Map) getWorld()).changeHealth(damage, playerNumber);
    }
    
    public int getDirection() {
        return direction;
    }
    
    public abstract void normalAttack();
    public abstract void specialAttack();
    public abstract void removeProjectiles();
        
}
