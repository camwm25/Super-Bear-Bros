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
        moveToSpot();
    }
    
    public PlayerFollower(int x, int y) {
        super(x, y);
    }
    
    public PlayerFollower(int x, int y, Player p) {
        super(x, y);
        thisPlayer = p;
        switch(p.playerNumber) {
            case 1:
                setImage("playerOne.png");
                break;
            case 2:
                setImage("playerTwo.png");
                break;
        }
    }
    
    public void moveToSpot() {
        setLocation(thisPlayer.getX(), thisPlayer.getY() + 50);
    }
    
    public void endGame() {
        ((Map) getWorld()).removeObject(this);
    }
}
