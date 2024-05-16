import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen that shows up when someone wins.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends GameScreen
{
    private WinnerScreener one;
    private WinnerScreener two;
    
    private String characterOne;
    private String characterTwo;
    
    private int winner;
    
    private int timer;
    
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen(String playerOne, String playerTwo, int winner)
    {
        characterOne = playerOne;
        characterTwo = playerTwo;
        this.winner = winner;
        timer = 0;
        showText("Game Over!", 480, 270);
    }
    
    public void goToScreen() {
        switch (winner) {
            case 0:
                one = new WinnerScreener(characterOne, 1, "Draw", 1);
                two = new WinnerScreener(characterTwo, 1, "Draw", 2);
                break;
                // draw
            case 1:
                one = new WinnerScreener(characterOne, 1, "Winner", 1);
                showText("Winner: Player One!", 240, 50);
                two = new WinnerScreener(characterTwo, 1, "Loser", 2);
                break;
                // player one wins
            case 2:
                one = new WinnerScreener(characterOne, 1, "Loser", 1);
                two = new WinnerScreener(characterTwo, 1, "Winner", 2);
                showText("Winner: Player Two!", 720, 50);
                break;
                //player two wins
        }
        addObject(one, 240, 270);
        addObject(two, 720, 270);
    }
    
    public void leaveScreen() {
        
    }
    
    public void act() {
        timer++;
        if (timer == 120) {
            showText("The Winner Is...", 480, 350);
        }
        if (timer == 180) {
            showText("", 480, 270);
            showText("", 480, 350);
            goToScreen();
        }
    }
}
