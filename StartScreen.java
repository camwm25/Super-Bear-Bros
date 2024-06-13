import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The start screen: the initial screen that opens when Run is clicked.
 * 
 * @author Devin Joe
 * @version 2024-01-09
 */
public class StartScreen extends GameScreen
{
    private String[] controlsOne = null;
    private String[] controlsTwo = null;
    
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
    }
    
    public StartScreen(String[] controlsOne, String[] controlsTwo)
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        this();
        
        this.controlsOne = controlsOne;
        this.controlsTwo = controlsTwo;
    }

    public void act()
    {
        // Temporary ways
        if (Greenfoot.isKeyDown("5")) {
            goToControls();
        }
        if (Greenfoot.isKeyDown("2")) {}
    }

    public void goToScreen() {
        StartButton startButton = new StartButton();
        addObject(startButton, 480, 270);
        SettingsButton settingsButton = new SettingsButton();
        addObject(settingsButton, 60, 470);
        
        displayText("fork", 480, 300, 0.5);
    }
    
    public void goToSettings() {
        Greenfoot.setWorld(new SettingsScreen(this));
    }

    public void leaveScreen() {
        if (controlsOne != null && controlsTwo != null) {
            Greenfoot.setWorld(new CharacterSelect(controlsOne, controlsTwo));
        }
        else {
            Greenfoot.setWorld(new CharacterSelect());
        }
        showText("", 480, 200); // to remove old start game text
    }
    
    public void goToControls() {
        if (controlsOne != null && controlsTwo != null) {
            Greenfoot.setWorld(new ControlScreen(controlsOne, controlsTwo));
        }
        else {
            Greenfoot.setWorld(new ControlScreen());
        }
        showText("", 480, 200);
    }
}
