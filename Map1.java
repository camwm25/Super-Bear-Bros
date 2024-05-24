import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The map.
 * 
 * @author Devin Joe
 * @version 2024-01-31
 */
public class Map1 extends Map
{       
    public Map1(String characterOne, String characterTwo) {
        super(characterOne, characterTwo);
        
        GreenfootImage image = new GreenfootImage("map1.jpg");
        image.scale(image.getWidth() / 6, image.getHeight() / 6);
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
        
        for (int i = 50; i >= 0; i--) {
            addCloud(Math.random() * 10000 - 5000,
                     Math.random() * 300 - 500,
                     i / 10.0);
        }
        /**
        addObject(new Ground("grass_block", 500, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", 400, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", 300, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", 200, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", 100, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", 0, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", -100, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", -200, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", -300, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", -400, 300, "base"), 0, 0);
        addObject(new Ground("grass_block", -500, 300, "base"), 0, 0);
        
        addObject(new Ground("tile1_b0", -300, 100), 0, 0);
        addObject(new Ground("tile1_b2", -190, 100), 0, 0);
        
        addObject(new Ground("tile1_b0", -355, -100), 0, 0);
        addObject(new Ground("tile1_b2", -245, -100), 0, 0);
        
        addObject(new Ground("tile1_b0", -55, 0), 0, 0);
        addObject(new Ground("tile1_b2", 55, 0), 0, 0);
        
        addObject(new Ground("tile1_b0", 190, 100), 0, 0);
        addObject(new Ground("tile1_b2", 300, 100), 0, 0);
        
        addObject(new Ground("tile1_b0", 245, -100), 0, 0);
        addObject(new Ground("tile1_b2", 355, -100), 0, 0);
        **/
    }
}