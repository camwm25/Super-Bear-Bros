import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingsScreen extends GameScreen
{
    World previousScreen;
    
    public SettingsScreen(World previousScreen) {
        super();
        goToScreen();
        
        this.previousScreen = previousScreen;
        
        setBackground(new GreenfootImage("settings_screen.png"));
        getBackground().scale(getWidth(), getHeight());
    }
    
    public void goToScreen() {
        ControlsButton controlsButton = new ControlsButton(this);
        addObject(controlsButton, 480, 270);
    }

    public void goToControls() {
        ((StartScreen) previousScreen).goToControls();
    }
    
    public void leaveScreen() {
        Greenfoot.setWorld(previousScreen);
    }
}
