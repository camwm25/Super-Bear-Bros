import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsButton here.
 * 
 * @author Devin Joe
 * @version 2024-06-12
 */
public class SettingsButton extends Button
{
    public void onPress() { 
        ((StartScreen) getWorld()).goToSettings();
        ((StartScreen) getWorld()).removeObject(this);
    }
}
