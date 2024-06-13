import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsButton here.
 * 
 * @author Devin Joe
 * @version 2024-06-12
 */
public class ControlsButton extends Button
{
    World settingsScreen;
    
    public ControlsButton(World settingsScreen) {
        super();
        this.settingsScreen = settingsScreen;
    }
    
    public void onPress() {
        ((SettingsScreen) settingsScreen).goToControls();
        // ((StartScreen) startScreen).goToControls();
    }
}
