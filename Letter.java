import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Letter here.
 * 
 * @author Devin Joe
 * @version 2024-06-12
 */
public class Letter extends Actor
{
    private int x;
    private int y;
    
    public Letter(String character, int x, int y, double size) {
        setImage(new GreenfootImage("font/" + character + ".png"));
        getImage().scale((int) (getImage().getWidth() * size), (int) (getImage().getHeight() * size));
        this.x = x;
        this.y = y;
        setLocation(x, y);
    }
    
    public void show(World world) {
        world.addObject(this, x, y);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
