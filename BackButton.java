import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsButton here.
 * 
 * @author Devin Joe
 * @version 2024-06-12
 */
public class BackButton extends Button
{
    World controlScreen;
    
    public BackButton(World controlScreen) {
        super();
        this.controlScreen = controlScreen;
    }
    
    public void onPress() {
        ((ControlScreen) controlScreen).leaveScreen();
        // ((StartScreen) startScreen).goToControls();
    }
}
