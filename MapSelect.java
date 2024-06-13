import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The background for the character select screen.
 * 
 * @author Devin Joe
 * @version 2024-05-24
 */
public class MapSelect extends GameScreen
{
    private String characterOne;
    private String characterTwo;
    private String map;
    
    private String[] controlsOne = null;
    private String[] controlsTwo = null;
    
    public MapSelect(String characterOne, String characterTwo) {
        super();
        goToScreen();
        
        this.characterOne = characterOne;
        this.characterTwo = characterTwo;
        
        setBackground(new GreenfootImage("map_select.png"));
        getBackground().scale(getWidth(), getHeight());
    }
    
    public MapSelect(String characterOne, String characterTwo, String[] controlsOne, String[] controlsTwo) {
        this(characterOne, characterTwo);
        
        this.controlsOne = controlsOne;
        this.controlsTwo = controlsTwo;
    }
    
    public void goToScreen() {
        Selectable map1Select = new Selectable("map1");
        addObject(map1Select, 180, 230);
        displayText("Map 1", 180, 160, 0.3);
        
        Selectable map2Select = new Selectable("map2");
        addObject(map2Select, 330, 230);
        displayText("Map 2", 330, 160, 0.3);
    }
    
    public void selectMap(String s) {
        map = s;
        Icon mapIcon = new Icon(map, 5);
        addObject(mapIcon, 480, 350);
        
        GameButton gameButton = new GameButton();
        addObject(gameButton, 480, 350);
    }
    
    public void leaveScreen() {
        if (controlsOne != null && controlsTwo != null) {
            if (map.equals("map1")) {
                Greenfoot.setWorld(new Map1(characterOne, characterTwo, controlsOne, controlsTwo));
            }
            else if (map.equals("map2")) {
                Greenfoot.setWorld(new Map2(characterOne, characterTwo, controlsOne, controlsTwo));
            }
            else {
                Greenfoot.setWorld(new Map1(characterOne, characterTwo, controlsOne, controlsTwo));
            }
        }
        else {
            if (map.equals("map1")) {
                Greenfoot.setWorld(new Map1(characterOne, characterTwo));
            }
            else if (map.equals("map2")) {
                Greenfoot.setWorld(new Map2(characterOne, characterTwo));
            }
            else {
                Greenfoot.setWorld(new Map1(characterOne, characterTwo));
            }
        }
        
    }    
}
