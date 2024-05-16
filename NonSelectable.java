import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For the character select screen - so we can see who the player selects.
 * 
 * Cam Welch Morgan
 * 2024-01-17
 */
public class NonSelectable extends SelectScreeners
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
    
    public NonSelectable(String character, int world) {
        images[0] = new GreenfootImage("" + character + "Select.png");
        for (GreenfootImage image : images) {
            image.scale(image.getWidth() / world, image.getHeight() / world);
        }
        setImage(images[0]);
        characterName = character;
    }
    
    public void act()
    {
        
    }
}
