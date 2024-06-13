import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All maps.
 * 
 * @author Devin Joe
 * @version 2024-01-09
 */
public abstract class Map extends GameScreen
{
    private String characterOne;
    private String characterTwo;
    
    public String[] playerOneControls = {"W", "S", "A", "D", "Q", "E", "F"};
    public String[] playerTwoControls = {"I", "K", "J", "L", "U", "O", "H"};
    
    private Icon[] iconList = new Icon[2];
    
    private double camX = 0;
    private double camY = 0;
    private double camZoom = 1;
    private double camXVelocity = 0;
    private double camYVelocity = 0;
    
    private int camFocus = 0;
    
    private Player player1;
    private Player player2;
    
    private PlayerFollower follower1;
    private PlayerFollower follower2;
    
    private int winner;
    
    private int playerOneHealth;
    private int playerTwoHealth;
    
    Ground[] groundArray = new Ground[25];
    int arrayCounter;
    
    private final double shakingThreshold = 1;
    
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map(String characterOne, String characterTwo)
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        this(characterOne, characterTwo, 
            new String[] {"W", "S", "A", "D", "Q", "E", "F"}, 
            new String[] {"I", "K", "J", "L", "U", "O", "H"});
    }
    
    public Map(String characterOne, String characterTwo, String[] controlsOne, String[] controlsTwo)
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        
        playerOneControls = controlsOne;
        playerTwoControls = controlsTwo;
        this.characterOne = characterOne;
        this.characterTwo = characterTwo;
        
        
        playerOneHealth = 200;
        playerTwoHealth = 200;
        
        winner = 0;
        
        goToScreen(); 
        
        setPaintOrder(PlayerFollower.class, Letter.class, Icon.class, InvisibleObject.class, 
            Player.class, PlayerFollower.class, Lightsaber.class, Hammer.class, 
            Window.class, Beans.class, Ground.class, Cloud.class);
    }
    
    public void changeHealth(int damage, int playerNumber) {
        switch (playerNumber) {
            case 1:
            playerOneHealth -= damage;
            displayText(String.valueOf(playerOneHealth), 100, 150, 0.5);
            break;
            
            case 2:
            playerTwoHealth -= damage;
            displayText(String.valueOf(playerTwoHealth), 860, 150, 0.5);
            break;
            
        }
    }
    
    public void act() {
        GameScreen.music.playLoop();
        updateCamVelocity();
        updateCamZoom();
        moveCam();
        tester();
        checkDead();
    }
    
    public void tester() {
        //showText(String.valueOf(player1.getStun()), 60, 400);
        //showText(String.valueOf(player2.getStun()), 900, 400);
    }
    
    public void checkDead() {
        if (playerTwoHealth <= 0 && playerOneHealth <= 0) {
            winner = 0;
            leaveScreen();
        }
        else if (playerOneHealth <= 0) {
            winner = 2;
            leaveScreen();
        }
        else if (playerTwoHealth <= 0) {
            winner = 1;
            leaveScreen();
        }
    }
    
    public void updateCamVelocity() {
        if (Greenfoot.isKeyDown("1") || camFocus == 1) {
            camXVelocity = (player1.getXPosition() - camX) / 20;
            camYVelocity = (player1.getYPosition() - camY) / 20;
        }
        else if (Greenfoot.isKeyDown("2") || camFocus == 2) {
            camXVelocity = (player2.getXPosition() - camX) / 20;
            camYVelocity = (player2.getYPosition() - camY) / 20;
        }
        else {
            camXVelocity = ((player1.getXPosition() + 20*player1.getXVelocity() + player2.getXPosition() + 20*player2.getXVelocity()) / 4 - camX) / 20;
            camYVelocity = ((player1.getYPosition() + 20*player1.getYVelocity() + player2.getYPosition() + 20*player2.getYVelocity()) / 4 - camY) / 20;
        }
        
        // Prevent shaking
        if (-shakingThreshold < camXVelocity && camXVelocity < shakingThreshold) {
            camXVelocity = 0;
        }
        if (-shakingThreshold < camYVelocity && camYVelocity < shakingThreshold) {
            camYVelocity = 0;
        }
    }
    
    public void updateCamZoom() {
        double playerDistanceSquared = Math.pow(player1.getXPosition() - player2.getXPosition(), 2) +
                                Math.pow(player1.getYPosition() - player2.getYPosition(), 2);
        double desiredCamZoom = 1 / (playerDistanceSquared / 1000000 + 1.2) + 0.25;
        
        // test
        // desiredCamZoom *= 1;
        
        
        camZoom += (desiredCamZoom - camZoom) / 10;
    }
    
    public void moveCam() {
        camX += camXVelocity;
        camY += camYVelocity;
    }
    
    public void goToScreen()
    {
        switch (characterOne) {
            case "bear":
                //player1 = new Bear("WASD", -200, 200);
                player1 = new Bear(1, -200, 200, playerOneControls);
                displayText("BEAR", 100, 50, 0.3);
                break;
            case "bill":
                //player1 = new Bill("WASD", -200, 200);
                player1 = new Bill(1, -200, 200, playerOneControls);
                displayText("BILL", 100, 50, 0.3);
                break;
            case "gordonsmom":
                //player1 = new GordonsMom("WASD", -200, 200);
                player1 = new GordonsMom(1, -200, 200, playerOneControls);
                displayText("GORDON'S MOM", 100, 50, 0.3);
                break;
            case "polarbear":
                //player1 = new PolarBear("WASD", -200, 200);
                player1 = new PolarBear(1, -200, 200, playerOneControls);
                displayText("POLAR BEAR", 100, 50, 0.3);
                break;
            default:
                //player1 = new Bear("WASD", -200, 200);
                player1 = new Bear(1, -200, 200, playerOneControls);
        }
        
        Icon one = new Icon(characterOne, 5);
        addObject(player1, -300, 0);
        follower1 = new PlayerFollower(player1.getX(), player1.getY(), player1);
        addObject(follower1, player1.getX(), player1.getY()+50);
        addObject(one, 100, 100);
        iconList[0] = one;
        showText("" + 200, 100, 150);
        
        switch (characterTwo) {
            case "bear":
                //player2 = new Bear("IJKL", 200, 200);
                player2 = new Bear(2, 200, 200, playerTwoControls);
                displayText("BEAR", 860, 50, 0.3);
                break;
            case "bill":
                //player2 = new Bill("IJKL", 200, 200);
                player2 = new Bill(2, 200, 200, playerTwoControls);
                displayText("BILL", 860, 50, 0.3);
                break;
            case "gordonsmom":
                //player2 = new GordonsMom("IJKL", 200, 200);
                player2 = new GordonsMom(2, 200, 200, playerTwoControls);
                displayText("GORDON'S MOM", 860, 50, 0.3);
                break;
            case "polarbear":
                //player2 = new PolarBear("IJKL", 200, 200);
                player2 = new PolarBear(2, 200, 200, playerTwoControls);
                displayText("POLAR BEAR", 860, 50, 0.3);
                break;
            default:
                //player2 = new Bear("IJKL", 200, 200);
                player2 = new Bear(2, 200, 200, playerTwoControls);
        }
        
        Icon two = new Icon(characterTwo, 5);
        addObject(player2, 300, 0);
        follower2 = new PlayerFollower(player2.getX(), player2.getY(), player2);
        addObject(two, 860, 100);
        addObject(follower2, player2.getX(), player2.getY()+50);
        iconList[1] = two;
        showText("" + 200, 860, 150);        
    }
    
    public void playerOne(String s) {
        characterOne = s;
    }
    
    public void playerTwo(String s) {
        characterTwo = s;
    }
    
    public void makeLiterallyEverythingOnScreenDisappear() {
        makeBlocksDisappear();
        makePlayersDisappear();
        makeFollowersDisappear();
    }
    
    public void makePlayersDisappear() {
        player1.removeProjectiles();
        player2.removeProjectiles();
        removeObject(player1);
        removeObject(player2);
    }
    
    public void leaveScreen(){
        makeLiterallyEverythingOnScreenDisappear();
        
        Greenfoot.setWorld(new EndScreen(characterOne, characterTwo, winner));
    }
    
    public double getCamX() {
        return camX;
    }
    
    public double getCamY() {
        return camY;
    }
    
    public double getCamZoom() {
        return camZoom;
    }
    
    public Player getPlayer(int number) {
        if (number == 2) {
            return player2;
        }
        else {
            return player1;
        }
    }
    
    protected void buildObject(String objectName, int x, int y, String type) {
        Ground g = new Ground(objectName, x, y, type);
        groundArray[arrayCounter] = g;
        arrayCounter++;
        addObject(g, 0, 0);
    }
    
    protected void buildObject(String objectName, int x, int y) {
        Ground g = new Ground(objectName, x, y);
        groundArray[arrayCounter] = g;
        arrayCounter++;
        addObject(g, 0, 0);
    }
    
    protected void addCloud(double x, double y, double z) {
        Cloud c = new Cloud(x, y, z);
        addObject(c, 0, 0);
    }
    
    protected void addStar(double x, double y, double z) {
        Star s = new Star(x, y, z);
        addObject(s, 0, 0);
    }
    
    public void makeBlocksDisappear() {
        for (Ground g : groundArray) {
            removeObject(g);
        }
    }
    
    public void makeFollowersDisappear() {
        follower1.endGame();
        follower2.endGame();
    }
    
    public void setCamFocus(int playerNumber) {
        camFocus = playerNumber;
    }
    
}
