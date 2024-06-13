import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A space map.
 * 
 * @author Devin Joe
 * @version 2024-05-24
 */
public class Map2 extends Map
{       
    public Map2(String characterOne, String characterTwo) {
        super(characterOne, characterTwo);
        
        GreenfootImage image = new GreenfootImage("map2.jpg");
        image.scale(image.getWidth(), image.getHeight());
        setBackground(image);
        buildMap();
        arrayCounter = 0;
    }
    
    public Map2(String characterOne, String characterTwo, String[] controlsOne, String[] controlsTwo) {
        super(characterOne, characterTwo, controlsOne, controlsTwo);
        //super(characterOne, characterTwo);
        
        GreenfootImage image = new GreenfootImage("map2.jpg");
        image.scale(image.getWidth(), image.getHeight());
        setBackground(image);
        buildMap();
        arrayCounter = 0;
    }
    
    private void buildMap() {
        buildObject("space_block", 512, 300, "base");
        buildObject("space_block", 448, 300, "base");
        buildObject("space_block", 384, 300, "base");
        buildObject("space_block", 320, 300, "base");
        buildObject("space_block", 256, 300, "base");
        buildObject("space_block", 192, 300, "base");
        buildObject("space_block", 128, 300, "base");
        buildObject("space_block", 64, 300, "base");
        buildObject("space_block", 0, 300, "base");
        buildObject("space_block", -64, 300, "base");
        buildObject("space_block", -128, 300, "base");
        buildObject("space_block", -192, 300, "base");
        buildObject("space_block", -256, 300, "base");
        buildObject("space_block", -320, 300, "base");
        buildObject("space_block", -384, 300, "base");
        buildObject("space_block", -448, 300, "base");
        buildObject("space_block", -512, 300, "base");
        
        buildObject("tile1_b0", -220, 100);
        buildObject("tile1_b2", -110, 100);
        
        buildObject("tile1_b0", 110, 100);
        buildObject("tile1_b2", 220, 100);
        
        int numStars = 300;
        for (int i = 0; i <= numStars; i++) {
            addStar(Math.random() * (25000 - 2000 * i / numStars) - (25000 - 2000 * i / numStars)/2,
                     Math.random() * (15000 - 1000 * i / numStars) - (15000 - 1000 * i / numStars)/2 + 2000,
                     10 * (1 - (double) i / numStars));
        }
    }
}