import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerFollower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerFollower extends ForegroundObject
{
    Player thisPlayer;
    
    /**
     * Act - do whatever the PlayerFollower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        moveToSpot();
    }
    
    public PlayerFollower(int x, int y) {
        super(x, y);
        imageScale = 1;
    }
    
    public PlayerFollower(int x, int y, Player p) {
        this(x, y);
        thisPlayer = p;
        switch(p.playerNumber) {
            case 1:
                imageName = "playerOne.png";
                break;
            case 2:
                imageName = "playerTwo.png";
                break;
        }
    }
    
    public void moveToSpot() {
        x = thisPlayer.getXPosition();
        y = thisPlayer.getYPosition() - thisPlayer.getImage().getHeight();
    }
    
    public void endGame() {
        ((Map) getWorld()).removeObject(this);
    }
}
