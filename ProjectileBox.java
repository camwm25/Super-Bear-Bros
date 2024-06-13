import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Invisible hitboxes for projectile deflecting/absorbing attacks.
 * Currently the same as Hitbox.class. WIll be changed to be different for projectiles.
 * @author Cam Welch Morgan
 * @version 2024-01-30
 */
public class ProjectileBox extends InvisibleObject
{
    double size;
    int direction;
    int timer = 0;
    int time = 0;
    int healing;
    
    boolean isTimer = false;
    boolean wantMove = true;
    
    /**
     * Act - do whatever the hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public ProjectileBox(double size, int playerCreator, int direction, double x, double y, int time,
                        int healFactor) {
        this(size, playerCreator, direction, x, y, healFactor);
        isTimer = true;
        this.time = time;
    }
    
    public ProjectileBox(double size, int playerCreator, int direction, double x, double y, int healFactor) {
        super(playerCreator, x, y, direction);
        imageName = "hitbox.png";
        imageScale = 2.0/size;
        
        imageTransparency = 0;
        
        healing = healFactor;
        
        this.size = size;
        creator = playerCreator;
        this.direction = direction;
    }
    
    public ProjectileBox(double size, int playerCreator, int direction, double x, double y, boolean wantMove,
                        int healFactor) {
        this(size, playerCreator, direction, x, y, healFactor);
        this.wantMove = wantMove;
    }
    
    public void checkHit() {
        if (isTimer) {
            if (timer < time) {
                timer++;
                for (Projectile p: getIntersectingObjects(Projectile.class)) {
                    ((Map) getWorld()).removeObject(p);
                    Player play = ((Map) getWorld()).getPlayer(creator);
                    play.healDamage(healing*2);
                }
                for (Bullet b: getIntersectingObjects(Bullet.class)) {
                    ((Map) getWorld()).removeObject(b);
                    Player play = ((Map) getWorld()).getPlayer(creator);
                    play.healDamage(healing);
                }
                checkOwner();
                checkMove();
            }
            else {
                ((Map) getWorld()).removeObject(this);
            }
        }
        else {
            if (checkHeld()) {
                timer++;
                for (Projectile p: getIntersectingObjects(Projectile.class)) {
                    ((Map) getWorld()).removeObject(p);
                    Player play = ((Map) getWorld()).getPlayer(creator);
                    play.healDamage(healing*2);
                }
                for (Bullet b: getIntersectingObjects(Bullet.class)) {
                    ((Map) getWorld()).removeObject(b);
                    Player play = ((Map) getWorld()).getPlayer(creator);
                    play.healDamage(healing);
                }
                checkOwner();
                checkMove();
            }
            else {
                ((Map) getWorld()).removeObject(this);
            }
        }
    }
    
    public boolean checkHeld() {
        if (Greenfoot.isKeyDown(((Map) getWorld()).getPlayer(creator).alternateAttack)) {
            return true;
        } 
        return false;
    }
    
    public void checkMove() {
        if (!wantMove && this.getWorld() != null) {
            Player p = ((Map) getWorld()).getPlayer(creator);
            if (Math.abs(yCoord - (int)p.getYPosition()) > 30) {
                ((PolarBear)p).projectileBoxGone = true;
                ((Map) getWorld()).removeObject(this);
            }
            if (Math.abs(xCoord - (int)p.getXPosition()) > 10) {
                ((PolarBear)p).projectileBoxGone = true;
                ((Map) getWorld()).removeObject(this);
            }
        }
        
    }
}
