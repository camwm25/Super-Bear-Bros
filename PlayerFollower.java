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
    int imageScale = 1;
    /**
     * Act - do whatever the PlayerFollower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveToSpot();
        //scale();
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
        int yCoord = thisPlayer.getY() - (int)(100*((Map) getWorld()).getCamZoom());
        yCoord +=  (int)(50*(((Map) getWorld()).getCamZoom()-1)*((Map) getWorld()).getCamZoom()-1);
        setLocation(thisPlayer.getX(), yCoord);
    }
    
    public void scale() {
        Map world = (Map) getWorld();
        GreenfootImage imageFollower = new GreenfootImage(imageName);
        imageFollower.scale((int) (5*imageFollower.getWidth() * imageScale * world.getCamZoom()) + 1,
                    (int) (5*imageFollower.getHeight() * imageScale * world.getCamZoom()) + 1);
        setImage(imageFollower);
    }
    
    public void endGame() {
        ((Map) getWorld()).removeObject(this);
    }
}
