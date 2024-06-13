import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A sus map.
 * 
 * @author Devin Joe
 * @version 2024-06-13
 */
public class Map3 extends Map
{       
    public Map3(String characterOne, String characterTwo) {
        super(characterOne, characterTwo);
        
        GreenfootImage image = new GreenfootImage("map3.jpg");
        image.scale(image.getWidth() / 4, image.getHeight() / 4);
        setBackground(image);
        buildMap();
        arrayCounter = 0;
    }
    
    public Map3(String characterOne, String characterTwo, String[] controlsOne, String[] controlsTwo) {
        super(characterOne, characterTwo, controlsOne, controlsTwo);
        //super(characterOne, characterTwo);
        
        GreenfootImage image = new GreenfootImage("map3.jpg");
        image.scale(image.getWidth(), image.getHeight());
        setBackground(image);
        buildMap();
        arrayCounter = 0;
    }
    
    private void buildMap() {
        buildObject("grass_block", 500, 300, "base");
        buildObject("grass_block", 400, 300, "base");
        buildObject("grass_block", 300, 300, "base");
        buildObject("grass_block", 200, 300, "base");
        buildObject("grass_block", 100, 300, "base");
        buildObject("grass_block", 0, 300, "base");
        buildObject("grass_block", -100, 300, "base");
        buildObject("grass_block", -200, 300, "base");
        buildObject("grass_block", -300, 300, "base");
        buildObject("grass_block", -400, 300, "base");
        buildObject("grass_block", -500, 300, "base");
    }
}