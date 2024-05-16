import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Things that will show up when someone wins.
 * 
 * Cam Welch Morgan
 * 
 */
public class WinnerScreener extends Actor
{
    /**
     * An array of images that the player will use.
     * For now, it will be a one-element array containing a picture of the
     * character.
     * In the future, we could change the data structure to something more
     * convenient if needed.
     */
    public GreenfootImage[] images = new GreenfootImage[1];
    
    private String characterName;
    private int playerNumber;
    
    public WinnerScreener(String character, int world, String winner, int playerNumber) {
        switch (winner) {
            case "Draw":
                images[0] = new GreenfootImage("" + character + "Draws.png");
                break;
            case "Winner":
                images[0] = new GreenfootImage("" + character + "Wins.png");
                break;
            case "Loser":
                images[0] = new GreenfootImage("" + character + "Loses.png");
                break;
        }
        
        for (GreenfootImage image : images) {
            image.scale(image.getWidth() / world, image.getHeight() / world);
        }
        setImage(images[0]);
        characterName = character;
        
    }
    
    public void act() {
        
    }
    
}
