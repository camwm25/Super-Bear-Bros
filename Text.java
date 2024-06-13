import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    private final int spacing = 5;
    private ArrayList<Letter> letterList;
    // private double size;
    
    private String displayText;
    
    public Text(String text, int x, int y, double size) {
        letterList = new ArrayList<>();
        displayText = text.toLowerCase();
        
        int initialX = x;
        int currentX = x;
        for (int i = 0; i < displayText.length(); i++) {
            String currentCharacter = "" + displayText.charAt(i);
            if (currentCharacter.equals(" ")) currentCharacter = "space";
            if (currentCharacter.equals("'")) currentCharacter = "apostrophe";
            if (currentCharacter.equals(".")) currentCharacter = "period";
            if (currentCharacter.equals("-")) currentCharacter = "hyphen";
            if (currentCharacter.equals(":")) currentCharacter = "colon";
            letterList.add(new Letter(currentCharacter, currentX, y, size));
            currentX += size * spacing + letterList.get(letterList.size() - 1).getImage().getWidth();
        }
        
        for (Letter l : letterList) {
            l.shiftLeft((currentX - initialX) / 2);
        }
    }
    
    public void show(World world) {
        for (Letter l : letterList) {
            l.show(world);
        }
    }
    
    public void hide(World world) {
        for (Letter l : letterList) {
            world.removeObject(l);
        }
        world.removeObject(this);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
