import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlScreen extends GameScreen
{
    public String[] playerOneControls = {"W", "S", "A", "D", "Q", "E", "F"};
    public String[] playerTwoControls = {"I", "K", "J", "L", "U", "O", "H"};
    // 0 up, 1 down, 2 left, 3 right, 4 normal, 5 special, 6 alternate
    ControlButton c;
    
    boolean selectingOldControl = false;
    boolean selectingNewControl = false;
    
    String changingLetter = null;
    
    public ControlScreen() {
        super();
        goToScreen();
        
        setBackground(new GreenfootImage("controls_screen.png"));
        getBackground().scale(getWidth(), getHeight());
    }
    
    public ControlScreen(String[] controlsOne, String[] controlsTwo) {
        this();
        
        playerOneControls = controlsOne;
        playerTwoControls = controlsTwo;
        textPlayerOne();
        textPlayerTwo();
    }
    
    public void goToScreen() {
        textPlayerOne();
        textPlayerTwo();
        buttonMake();
    }
    
    private void buttonMake() {
        showText("Click to Change Control:", 480, 150);
        c = new ControlButton();
        addObject(c, 480, 200);
        
        BackButton b = new BackButton(this);
        addObject(b, 480, 400);
    }
    
    private void textPlayerOne() {
        showText("Player One", 240, 50);
        
        showText("Up Key: " + playerOneControls[0], 240, 150);
        showText("Down Key: " + playerOneControls[1], 240, 200);
        showText("Left Key: " + playerOneControls[2], 240, 250);
        showText("Right Key: " + playerOneControls[3], 240, 300);
        
        showText("Normal Attack: " + playerOneControls[4], 240, 350);
        showText("Special Attack: " + playerOneControls[5], 240, 400);
        showText("Alternate Attack: " + playerOneControls[6], 240, 450);
    }
    
    private void textPlayerTwo() {
        showText("Player Two", 720, 50);
        
        showText("Up Key: " + playerTwoControls[0], 720, 150);
        showText("Down Key: " + playerTwoControls[1], 720, 200);
        showText("Left Key: " + playerTwoControls[2], 720, 250);
        showText("Right Key: " + playerTwoControls[3], 720, 300);
        
        showText("Normal Attack: " + playerTwoControls[4], 720, 350);
        showText("Special Attack: " + playerTwoControls[5], 720, 400);
        showText("Alternate Attack: " + playerTwoControls[6], 720, 450);
    }
    
    public void act(){
        // Temporary ways
        if (Greenfoot.isKeyDown("6")) {
            leaveScreen();
        }
        checkOldControl();
        checkNewControl();
    }
    
    public void changeControlOne() {
        showText("Press Button of Control:", 480, 150);
        selectingOldControl = true;
    }
    
    public void checkOldControl() {
        if (selectingOldControl) {
            for (String s : playerOneControls) {
                if (Greenfoot.isKeyDown(s)) {
                    changingLetter = s;
                    changeControlTwo();
                    return;
                }
            }
            for (String s : playerTwoControls) {
                if (Greenfoot.isKeyDown(s)) {
                    changingLetter = s;
                    changeControlTwo();
                    return;
                }
            }
        }
    }
    
    public void changeControlTwo() {
        selectingOldControl = false;
        selectingNewControl = true;
        showText("Press New Button:", 480, 150);
    }
    
    public void checkNewControl() {
        if (selectingNewControl) {
            String key = Greenfoot.getKey();
            if (key != null && Greenfoot.isKeyDown(key)) {
                for (String s : playerOneControls) {
                    if (key.toUpperCase().equals(s)) {
                        showText("Keybind Already Exists, Try Again", 480, 150);
                        return;
                    }
                }
                for (String s : playerTwoControls) {
                    if (key.toUpperCase().equals(s)) {
                        showText("Keybind Already Exists, Try Again", 480, 150);
                        return;
                    }
                }
                replaceKey(key);
            }
        }
    }
    
    public void replaceKey(String k) {
        if (!changingLetter.equals(null)) {
            for (int i = 0; i < playerOneControls.length; i++) {
                if (playerOneControls[i].equals(changingLetter)) {
                    playerOneControls[i] = k.toUpperCase();
                    selectingNewControl = false;
                    goToScreen();
                    changingLetter = null;
                    return;
                }
            }
            for (int i = 0; i < playerTwoControls.length; i++)  {
                if (playerTwoControls[i].equals(changingLetter)) {
                    playerTwoControls[i] = k.toUpperCase();
                    selectingNewControl = false;
                    goToScreen();
                    changingLetter = null;
                    return;
                }
            }
        }
    }
    
    public void leaveScreen() {
        Greenfoot.setWorld(new StartScreen(playerOneControls, playerTwoControls));
    }
}
