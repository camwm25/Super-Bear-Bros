import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The background for the character select screen.
 * 
 * @author Devin Joe
 * @version 2024-01-09
 */
public class CharacterSelect extends GameScreen
{
    private String characterOne;
    private String characterTwo;
    private int currentCharacter;
    
    private int numOfCharacters = 2;
    
    private Selectable[] selectableList = new Selectable[numOfCharacters];
    private NonSelectable[] nonSelectableList = new NonSelectable[2];
    
    public CharacterSelect() {
        super();
        goToScreen();
        
        setBackground(new GreenfootImage("character_select.png"));
        getBackground().scale(getWidth(), getHeight());
    }
    
    public void goToScreen() {
        currentCharacter = 1;
        
        Selectable bearSelect = new Selectable("bear");
        addObject(bearSelect, 480, 230);
        selectableList[0] = bearSelect;
        
        showText("BEAR", 480, 160);
        
        Selectable billSelect = new Selectable("bill");
        addObject(billSelect, 280, 230);
        selectableList[1] = billSelect;
        
        showText("BILL", 280, 160);
        
        Selectable gordonsmomSelect = new Selectable("gordonsmom");
        addObject(gordonsmomSelect, 680, 230);
        selectableList[1] = gordonsmomSelect;
        
        showText("GORDON'S MOM", 680, 160);
        
        showText("Player One", 200, 500);
        showText("Player Two", 760, 500);
    }
    
    public void selectPlayer(String s) {
        if (currentCharacter == 2) {
            characterTwo = s;
            currentCharacter = 3;
            
            NonSelectable two = new NonSelectable(s, 5);
            addObject(two, 760, 430);
            nonSelectableList[1] = two;
            
            GameButton gameButton = new GameButton();
            addObject(gameButton, 480, 350);
            // add button so you can play the game
        }
        else if (currentCharacter == 1){
            characterOne = s;
            currentCharacter = 2;
            NonSelectable one = new NonSelectable(s, 5);
            nonSelectableList[0] = one;
            addObject(one, 200, 430);
            //add text for player one
        }
        
    }
    
    public void leaveScreen() {
        makeTextDisappear();
        makeNonSelectablesDisappear();
        makeSelectablesDisappear();
        
        Greenfoot.setWorld(new Map1(characterOne, characterTwo));
    }
    
    public void makeTextDisappear() {
        showText("", 480, 300);
        showText("", 200, 500);
        showText("", 760, 500);
        showText("", 480, 160);
        showText("", 480, 100);
    }
    
    public void makeNonSelectablesDisappear() {
        for (int i = 0; i < nonSelectableList.length; i++) {
            NonSelectable n = nonSelectableList[i];
            removeObject(n);
        }
    }
    
    public void makeSelectablesDisappear() {
        for (int i = 0; i < selectableList.length; i++) {
            Selectable s = selectableList[i];
            removeObject(s);
        }
    }
    
}