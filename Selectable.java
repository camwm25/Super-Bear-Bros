import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * For the character select screen - so the player can select a character.
 * 
 * Cam Welch Morgan
 * 2024-01-15
 */
public class Selectable extends SelectScreeners
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
    
    public Selectable(String character) {
        images[0] = new GreenfootImage("" + character + "Select.png");
        for (GreenfootImage image : images) {
            image.scale(image.getWidth() / 5, image.getHeight() / 5);
        }
        setImage(images[0]);
        characterName = character;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            onPress();
        }
    }
    
    public void onPress() {
        ((CharacterSelect) getWorld()).selectPlayer(characterName);
        // will send character name to map2
    }
}
