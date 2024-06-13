import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Scary woman with a big hammer.
 * 
 * @author Devin Joe
 * @version 2024-01-30
 */
public class GordonsMom extends Player
{
    //int stareTimer = 20;
    int kickTimer = 15;
    int hammerTimer = 30;
    int blasterTimer = 20;
    
    //int stareY = -30;
    //int stareX = 10;
    //int stareDuration = 10;
    //int stareSize = 12;
    //int stareDamage = 2;
    //int stareKnockback = 8;
    //int stareStun = 15;
    
    int kickY = 30;
    int kickX = 20;
    int kickDuration = 15;
    int kickSize = 10;
    int kickDamage = 2;
    int kickKnockback = 12;
    int kickStun = 20;
    int kickDelay = 40;
    
    int hammerDelay = 60;
    int blasterDelay = 60;
    
    Hammer[] holder = new Hammer[1];
    Bullet[] blaster = new Bullet[1];
    
    public GordonsMom(String inputType, double x, double y) {
        super(inputType, x, y);
        JUMP_POWER = 20;
        SPEED = 1.2;
        imageName = "gordons_mom_walk_0.png";
        imageScale = 1;
        
        defaultImage = new GreenfootImage("gordons_mom_walk_0.png");
    }
    
    public GordonsMom(int playerNumber, double x, double y, String[] controls) {
        super(playerNumber, x, y, controls);
        JUMP_POWER = 20;
        SPEED = 1.2;
        imageName = "gordons_mom_walk_0.png";
        imageScale = 1;
    }

    
    public void act() {
        super.act();
        
        if (blasterTimer >= 20 && hammerTimer >= 30 && kickTimer >= 15) {
            imageName = "gordons_mom_walk_" + (((int) x/10) % 4 + 4) % 4 + ".png";
            if (stun != 0) {
                imageName = "gordons_mom_stun.png";
            }
        }
        
        //if (stareTimer < 15) {
            //imageName = "gordons_mom_stare_" + stareTimer * 3 / 15 + ".png";
            //stareTimer++;
        //}
        
        if (kickTimer < 15) {
            imageName = "gordons_mom_kick_" + kickTimer / 4 + ".png";
            kickTimer++;
        }
        
        if (hammerTimer < 30) {
            imageName = "gordons_mom_hammer_" + hammerTimer * 5 / 30 + ".png";
            hammerTimer++;
        }
        
        if (blasterTimer < 20) {
            imageName = "gordons_mom_blaster_" + blasterTimer * 4 / 20 + ".png";
            blasterTimer++;
        }
        
        if(blasterTimer == 15 && canAttack()) {
            Bullet b = new Bullet(getXPosition(), getYPosition()-5, direction, playerNumber);
            blaster[0] = b;
            double BulletX = getXPosition() + getXVelocity();
            double BulletY = getYPosition() + getYVelocity();

            ((Map) getWorld()).addObject(b, (int)BulletX, (int)BulletY);
            setAttackDelay(blasterDelay);
        }
        
        imageDirection = direction;
    }
    
    public void normalAttack() {
        //formerly stare, now kick
        //if (canAttack()) {
            //attacking = true;
            //stareTimer = 0;
            
            //if (direction == 1) {
                //xVelocity = 1;
            //}
            //else {
                //xVelocity = -1;
            //}
            
            //Hitbox h = new Hitbox(stareSize, playerNumber, stareDuration, stareDamage, direction, 
                //stareKnockback, (int)getXPosition()+(stareX*direction), (int)getYPosition()+stareY, 
                //stareStun, 0, stareY);
            //((Map) getWorld()).addObject(h, getX()+(35*direction), getY()-40);
        
            //attacking = false;
        //}
        
        if (canAttack()) {
            attacking = true;
            kickTimer = 0;
            
            if (direction == 1) {
                xVelocity = 1;
            }
            else {
                xVelocity = -1;
            }
            
            Hitbox h = new Hitbox(kickSize, playerNumber, kickDuration, kickDamage, direction, 
                kickKnockback, (int)getXPosition() + kickX*direction, (int)getYPosition()+kickY, 
                kickStun);
            ((Map) getWorld()).addObject(h, getX()+(35*direction), getY());
            
            
            setAttackDelay(kickDelay);
            attacking = false;
        }
        
    }
    
    public void specialAttack() {
        //hammer
        if (canAttack()) {
            attacking = true;
            Hammer h = new Hammer(playerNumber, direction, getXPosition(), getYPosition());
            holder[0] = h;
            double hammerX = getXPosition() + getXVelocity();
            double hammerY = getYPosition() + getYVelocity();

            ((Map) getWorld()).addObject(h, (int)hammerX, (int)hammerY);
            setAttackDelay(hammerDelay);
            attacking = false;
        }
    }
    
    public void alternateAttack() {
        //blaster
        if (canAttack()) {
            blasterTimer = 0;
            attacking = true;
            attacking = false;
        }
    }
    
    public void removeProjectiles() {
        ((Map) getWorld()).removeObject(holder[0]);
    }
}
