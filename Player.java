import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * All players.
 * 
 * @author Devin Joe & Cam Welch Morgan
 * @version 2024-05-16
 */
public abstract class Player extends PhysicsObject
{
    double JUMP_POWER;
    double SPEED;
    
    public int playerNumber;
    
    int COOLDOWN = 20;
    
    int attacked = 0;
    public int stun = 0; 
    // thinking about stun rn. idk
    
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
    
    // to do: fix attacking and getting hit at same time cancelling knockback
    // to do: fix other knockback tidbits
    
    public Player(String character, String inputKeys, double x, double y) {        
        super(x, y);
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
            direction = 1;
        }
        else {
            direction = -1;
        }
    }
        
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        super.act();
        updateDrop();
        updateVelocity();
        move();
        fixOverlap();
        checkAttack();
        checkInVoid();
        updateAttackedAndStun();
        System.out.println("" + inGround + onGround() + playerNumber);
    }
    
    public boolean canAttack() {
        if (getStun() == 0 && attacked == 0 && !dropping && (xVelocity < 150 || xVelocity > -150)) {
            return true;
        }
        return false;
    }
    
    public int getStun() {
        return stun;
    }
    
    public void setStun(int timeStun) {
        stun = timeStun;
    }
    
    private void updateAttackedAndStun() {
        if (attacked != 0) {
            attacked--;
        }
        if (stun != 0) {
            stun--;
        }
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
            
            if (Greenfoot.isKeyDown(upKey) && getStun() == 0) {
                yVelocity = -JUMP_POWER;
            }
            
            xVelocity *= FRICTION;
        }
        else {
            experienceTheWonderfulForceOfGravity();
            
            xVelocity *= DRAG;
        }
        
        if (getStun() == 0) {
            
            if (Greenfoot.isKeyDown(leftKey)) {
                xVelocity -= SPEED;
                direction = -1;
            }
        
            if (Greenfoot.isKeyDown(rightKey)) {
                xVelocity += SPEED;
                direction = 1;
            }
        
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
            
            if (yVelocity <= 0) {
                dropping = false;
                inGround = false;
            }
        }
    }
    
    private void fixOverlap() {
        if ((onGround() || onBaseLayerGround()) && !inGround) {
            double tolerance = 20 * ((Map) getWorld()).getCamZoom();
            
            for (int i = 0; i < tolerance; i++) {
                if (onGround() || onBaseLayerGround()) {
                    y -= 1;
                    updateOnScreen();
                } else {
                    break;
                }
            }
            
            y += 1;
            updateOnScreen();
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
        attacked = knockDistance;
    }
    
    public void takeKnockback(int knockDirection, int knockDistanceHor, int knockDistanceVert) {
        switch (knockDirection) {
            case 1:
                xVelocity = knockDistanceHor;
                yVelocity = -0.5*knockDistanceVert;
                break;
            case -1:
                xVelocity = -knockDistanceHor;
                yVelocity = -0.5*knockDistanceVert;
                break;
            case 0:
                xVelocity = -direction * knockDistanceHor;
                yVelocity = -0.5*knockDistanceVert;
        }
        attacked = Math.max(knockDistanceVert, knockDistanceHor);
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
