import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlScreen extends GameScreen
{
    public String upKeyOne = "W";
    public String downKeyOne = "S";
    public String leftKeyOne = "A";
    public String rightKeyOne = "D";
    
    public String normalAttackOne = "Q";
    public String specialAttackOne = "E";
    public String alternateAttackOne = "F";
    
    public String upKeyTwo = "I";
    public String downKeyTwo = "K";
    public String leftKeyTwo = "J";
    public String rightKeyTwo = "K";
    
    public String normalAttackTwo = "U";
    public String specialAttackTwo = "O";
    public String alternateAttackTwo = "H";
    public ControlScreen() {
        super();
        goToScreen();
        
        setBackground(new GreenfootImage("bathroom-tile.png"));
        getBackground().scale(getWidth(), getHeight());
    }
    
    public void goToScreen() {
        textPlayerOne();
        textPlayerTwo();
    }
    
    private void textPlayerOne() {
        showText("Player One", 240, 50);
        
        showText("Up Key: " + upKeyOne, 240, 150);
        showText("Down Key: " + downKeyOne, 240, 200);
        showText("Left Key: " + leftKeyOne, 240, 250);
        showText("Right Key: " + rightKeyOne, 240, 300);
        
        showText("Normal Attack: " + normalAttackOne, 240, 350);
        showText("Special Attack: " + specialAttackOne, 240, 400);
        showText("Alternate Attack: " + alternateAttackOne, 240, 450);
    }
    
    private void textPlayerTwo() {
        showText("Player Two", 720, 50);
        
        showText("Up Key: " + upKeyTwo, 720, 150);
        showText("Down Key: " + downKeyTwo, 720, 200);
        showText("Left Key: " + leftKeyTwo, 720, 250);
        showText("Right Key: " + rightKeyTwo, 720, 300);
        
        showText("Normal Attack: " + normalAttackTwo, 720, 350);
        showText("Special Attack: " + specialAttackTwo, 720, 400);
        showText("Alternate Attack: " + alternateAttackTwo, 720, 450);
    }
    
    public void act()
    {
        // Temporary ways
        if (Greenfoot.isKeyDown("6")) {
            leaveScreen();
        }
    }
    
    public void leaveScreen() {
        Greenfoot.setWorld(new StartScreen());
    }
}
