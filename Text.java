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
    private final int spacing = 2;
    private ArrayList<Letter> letterList;
    // private double size;
    
    public Text(String text, int x, int y, double size) {
        letterList = new ArrayList<>();
        int currentX = x;
        for (int i = 0; i < text.length(); i++) {
            String currentCharacter = "" + text.charAt(i);
            if (currentCharacter.equals(" ")) currentCharacter = "space";
            if (currentCharacter.equals("'")) currentCharacter = "apostrophe";
            letterList.add(new Letter(currentCharacter, currentX, y, size));
            currentX += size * spacing + letterList.get(letterList.size() - 1).getImage().getWidth();
        }
    }
    
    public void show(World world) {
        for (Letter l : letterList) {
            l.show(world);
        }
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
