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
    
    private Icon[] iconList = new Icon[2];
    
    private double camX = 0;
    private double camY = 0;
    private double camZoom = 1;
    private double camXVelocity = 0;
    private double camYVelocity = 0;
    
    private int camFocus = 0;
    
    private Player player1;
    private Player player2;
    
    private int winner;
    
    private int playerOneHealth;
    private int playerTwoHealth;
    
    Ground[] groundArray = new Ground[25];
    int arrayCounter;
    
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map(String characterOne, String characterTwo)
    {    
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels.
        super();
        this.characterOne = characterOne;
        this.characterTwo = characterTwo;
        
        playerOneHealth = 200;
        playerTwoHealth = 200;
        
        winner = 0;
        
        goToScreen(); 
        
        setPaintOrder(Icon.class, Player.class, Lightsaber.class, Hammer.class, Beans.class, Hitbox.class, Ground.class);
    }
    
    public void changeHealth(int damage, int playerNumber) {
        switch (playerNumber) {
            case 1:
            playerOneHealth -= damage;
            showText(String.valueOf(playerOneHealth), 100, 150);
            break;
            
            case 2:
            playerTwoHealth -= damage;
            showText(String.valueOf(playerTwoHealth), 860, 150);
            break;
            
        }
    }
    
    public void act() {
        updateCamVelocity();
        updateCamZoom();
        moveCam();
        checkDead();
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
            camXVelocity = ((player1.getXPosition() + player2.getXPosition()) / 4 - camX) / 20;
            camYVelocity = ((player1.getYPosition() + player2.getYPosition()) / 4 - camY) / 20;
        }
    }
    
    public void updateCamZoom() {
        double playerDistance = Math.pow(player1.getXPosition() - player2.getXPosition(), 2) +
                                Math.pow(player1.getYPosition() - player2.getYPosition(), 2);
        double desiredCamZoom = Math.pow(playerDistance / 500000 + 0.5, -0.5);
        
        camZoom += (desiredCamZoom - camZoom) / 20;
    }
    
    public void moveCam() {
        camX += camXVelocity;
        camY += camYVelocity;
    }
    
    public void goToScreen()
    {
        switch (characterOne) {
            case "bear":
                player1 = new Bear("WASD", -200, 200);
                showText("BEAR", 100, 50);
                break;
            case "bill":
                player1 = new Bill("WASD", -200, 200);
                showText("BILL", 100, 50);
                break;
            case "gordonsmom":
                player1 = new GordonsMom("WASD", -200, 200);
                showText("GORDON'S MOM", 100, 50);
                break;
            default:
                player1 = new Bear("WASD", -200, 200);
        }
        
        Icon one = new Icon(characterOne, 5);
        addObject(one, 100, 100);
        iconList[0] = one;
        showText("" + 200, 100, 150);
        
        switch (characterTwo) {
            case "bear":
                player2 = new Bear("IJKL", 200, 200);
                showText("BEAR", 860, 50);
                break;
            case "bill":
                player2 = new Bill("IJKL", 200, 200);
                showText("BILL", 860, 50);
                break;
            case "gordonsmom":
                player2 = new GordonsMom("IJKL", 200, 200);
                showText("GORDON'S MOM", 860, 50);
                break;
            default:
                player2 = new Bear("IJKL", 200, 200);
        }
        
        Icon two = new Icon(characterTwo, 5);
        addObject(two, 860, 100);
        iconList[1] = two;
        showText("" + 200, 860, 150);
        
        addObject(player1, -300, 0);
        addObject(player2, 300, 0);
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
    
    public void makeBlocksDisappear() {
        for (Ground g : groundArray) {
            removeObject(g);
        }
    }
    
    public void setCamFocus(int playerNumber) {
        camFocus = playerNumber;
    }
}
