import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The start screen: the initial screen that opens when Run is clicked.
 * 
 * @author Devin Joe
 * @version 2024-01-09
 */
public class StartScreen extends GameScreen
{

    /**
     * Constructor for objects of class Map1.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        super();
        goToScreen();

        setBackground(new GreenfootImage("start.png"));
        getBackground().scale(getWidth(), getHeight());
        prepare();
    }

    public void act()
    {
        // Temporary ways
        if (Greenfoot.isKeyDown("1")) {}
        if (Greenfoot.isKeyDown("2")) {}
    }

    public void goToScreen() {
        StartButton startButton = new StartButton();
        addObject(startButton, 480, 270);
    }

    public void leaveScreen() {
        Greenfoot.setWorld(new CharacterSelect());
        showText("", 480, 200); // to remove old start game text
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
