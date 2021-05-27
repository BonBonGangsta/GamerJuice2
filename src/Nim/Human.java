/*
 * i certify that this is my original work
 * Byron De Paz PID 3999061
 */
package Nim;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * creates a human player to play a game of Nim.
 * @author byrondepaz
 */
public class Human implements Player {
    private String name; // name of player
    private int marblesTaken; // total number of marbles taken in a game
    private final String race = "HUM"; // the best race, the human race
    
    /**
     * creates a human player with a default name of John Doe.
     */
    public Human()
    {
        // if no name wants to be given, we have a back up
        this.name = "John Doe" ;
    }
    
    /**
     * Creates a human player with a given name.
     * @param name name of the player.
     */
    public Human(String name)
    {
        this.name = name;
    }

    /**
     * return the player name.
     * @return String with the human player's name
     */
    public String playerName() 
    {
        return name;
    }
    
    /**
     * calculates the maximum number that can be removed from the pile.
     * @param pile number of objects left in the pile.
     * @return maximum number that can be removed which is #left / 2.
     */
    public int move(int pile)
    {
       int answer = 1; // minimum that can be removed
       if(pile > 2) 
       {
       String input; 
       int maxRemoval = (int) (pile / 2); //force int
       // ask the user for a value between 1 and the maximum removal
       input = JOptionPane.showInputDialog("please enter the number of objects"
                  + " you would like to remove from the pile? 1 through "
                   + maxRemoval + "." + "Current pile: " 
                    + pile );
       // scan that bad boy
       Scanner scan = new Scanner(input);
       // if the scan has an int check if it's valid loop until a valid
       // answer is given.
       if(scan.hasNextInt())
       {
           //check if it's valid by assigning the int
           answer = scan.nextInt();
           // check if it's between 1 and the max removal inclusive
           if(answer >= 1 && answer <= maxRemoval)
           {
               // add to the marbles taken
               this.addToMarbles(answer);
               // return the answer
               return answer;
           }
       }
       // if the number is not valid let the user know
        JOptionPane.showInputDialog("I need a number between 1 and " +
                   maxRemoval);
       }
       // else return the default answer of 1
       this.addToMarbles(answer); // add 1 to the marbles taken
       return answer; // return 1
    }
    
    public String getRace()
    {
        return race; // get the race
    }
    
    /**
     * Add the number objects passed , to the counter.
     * @param marbles number of objects in the pile chosen to remove.
     */
    private void addToMarbles(int marbles)
    {
        marblesTaken += marbles ;
    }
    
    /**
     * return the number of objects taken.
     * @return 
     */
    public int objectsTaken()
    {
        return marblesTaken;
    }
    
    /**
     * add onto the number of objects taken. this is will be used when the 
     * player is the loser as they are forced to take the last object in Nim.
     * @param x 
     */
    public void addToObjects (int x)
    {
        marblesTaken += x;
    }
}
