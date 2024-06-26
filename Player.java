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

    public int stun = 0; 
    public int attackDelay = 0;
    
    String upKey;
    String downKey;
    String leftKey;
    String rightKey;
    
    String normalAttack;
    String specialAttack;
    String alternateAttack;
    
    int direction = 1;
    
    boolean attacking = false;
    boolean inGround = false;
    
    // Using Bear as a default to avoid having a null image for the first few frames.
    GreenfootImage defaultImage = new GreenfootImage("bear_walk_0.png");
    
    // to do: fix attacking and getting hit at same time cancelling knockback
    // to do: fix other knockback tidbits
    
    public Player(String inputKeys, double x, double y) {        
        super(x, y);
        switch (inputKeys) {
            case "IJKL":
                upKey = "I";
                downKey = "K";
                leftKey = "J";
                rightKey = "L";
                normalAttack = "U";
                specialAttack = "O";
                alternateAttack = "H";
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
                alternateAttack = "F";
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

    
    public Player(int number, double x, double y, String[] controls) {
        super(x,y);
        playerNumber = number;
        upKey = controls[0];
        downKey = controls[1];
        leftKey = controls[2];
        rightKey = controls[3];
        normalAttack = controls[4];
        specialAttack = controls[5];
        alternateAttack = controls[6];
        
        if (playerNumber == 1) {
            direction = 1;
        }
        else {
            direction = -1;
        }
    }
    
    public abstract void updateCurrentBean(int update);
        
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        super.act();
        updateVelocity();
        move();
        fixOverlap();
        updateDrop();
        checkAttack();
        checkInVoid();
        updateStun();
    }
    
    public boolean canAttack() {
        if (getStun() == 0 && getAttackDelay() == 0 & !dropping && (xVelocity < 150 || xVelocity > -150)) {
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
    
    public int getAttackDelay() {
        return attackDelay;
    }
    
    public void setAttackDelay(int timeDelay) {
        attackDelay = timeDelay;
    }
    
    private void updateStun() {
        if (stun != 0) {
            stun--;
        }
    }
    
    private void collideWithPlayers() {
        for (Player p : getIntersectingObjects(Player.class)) {
            double xDifference = p.getXPosition() - x;
            
            if (Math.abs(xDifference) < (getImage().getWidth()*imageScale + p.getImage().getWidth()*imageScale) * 0.4) {
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
            }
            if (Greenfoot.isKeyDown(specialAttack)) {
                specialAttack();
            } 
            if (Greenfoot.isKeyDown(alternateAttack)) {
                alternateAttack();
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
            
            if (yVelocity < 0) {
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
                yVelocity = -0.75*knockDistance;
                break;
            case -1:
                xVelocity = -knockDistance;
                yVelocity = -0.75*knockDistance;
                break;
            case 0:
                xVelocity = -direction * knockDistance;
                yVelocity = -0.75*knockDistance;
        }
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
    }
    
    public void takeDamage(int damage) {
        ((Map) getWorld()).changeHealth(damage, playerNumber);
    }
    
    public void healDamage(int damage) {
        ((Map) getWorld()).changeHealth(-damage, playerNumber);
    }
    
    public int getDirection() {
        return direction;
    }
    
    public GreenfootImage getDefaultImage() {
        return defaultImage;
    }
    
    public double getHeight() {
        return getDefaultImage().getHeight() * imageScale;
    }
    
    public abstract void normalAttack();
    public abstract void specialAttack();
    public abstract void alternateAttack();
    public abstract void removeProjectiles();
        
}
