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
        
        buildObject("tile1_b0", -300, 100);
        buildObject("tile1_b2", -190, 100);
        
        buildObject("tile1_b0", -355, -100);
        buildObject("tile1_b2", -245, -100);
        
        buildObject("tile1_b0", -55, 0);
        buildObject("tile1_b2", 55, 0);
        
        buildObject("tile1_b0", 190, 100);
        buildObject("tile1_b2", 300, 100);
        
        buildObject("tile1_b0", 245, -100);
        buildObject("tile1_b2", 355, -100);
        
        int numStars = 300;
        for (int i = 0; i <= numStars; i++) {
            addStar(Math.random() * (25000 - 2000 * i / numStars) - (25000 - 2000 * i / numStars)/2,
                     Math.random() * (15000 - 1000 * i / numStars) - (15000 - 1000 * i / numStars)/2 + 2000,
                     10 * (1 - (double) i / numStars));
        }
    }
}