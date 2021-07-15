/*
 * i certify that this is my original work
 * Byron De Paz PID 3999061
 */
package Nim;
import java.util.ArrayList;
/**
 * creates a supercomputer to play a game.
 * @author bydepa
 */
public class SuperComputerPlayer implements Player {
    private final String name ; // string of name.
    private int marblesTaken ; // total number of objects taken from the pile.
    private final ArrayList<Integer> key; // Array list of key answers.
    private final String race = "SCP"; // SuperComputer Race
    
    /**
     * Creates a supercomputer player with the best default name, then 
     * generates the array for the key values.
     * @param upperLimit initial number of object in the pile.
     */
    public SuperComputerPlayer( int upperLimit)
    {
        // set the best name ever.
        this.name = "N00BSLAY3R";
        // initialize the array with the key values needed.
        key = new ArrayList<Integer>() ;
        // fill in the values.
        generateKey(upperLimit);
    }
    
    /**
     * create a supercomputer player with a name and generate the array for
     * the array for the key values.
     * @param name string of the name.
     * @param upperLimit initial number of object in the pile.
     */
    public SuperComputerPlayer(String name, int upperLimit)
    {
        // set the name.
        this.name = name ;
        // initialize the arraylist.
        key = new ArrayList<Integer>() ;
        // fill in the values.
        generateKey(upperLimit);
        
    }
    
    /**
     * remove the right amount of marbles so that the remaining pile is a 
     * value of 2 to the nth power - 1.
     * @param pile number objects in the pile currently
     * @return the number of marbles to remove in order to make the remaining
     * pile 2^n - 1.
     */
    public int move(int pile)
    {
        int marbles = 1; // marbles that will be taken at the end.
        boolean matchFound = false; // switch to leave loop
        int lastKey = 1 ; // last key will be set to 1 for when the pile is 2.
        if (pile > 2)
        {
            // if the pile is not 3, 2, or 1 and the match is false
            // start at the first index
                for(int i = 0; i < key.size() && !matchFound; i++)
                {
                   int keyValue = key.get(i);
                
                    if (keyValue >= pile)
                    {
                        // if true, then assign the value in the previous index
                        matchFound = true;
                        lastKey = key.get(i-1);
                    }
                }
                // The number of marbles to take is the difference between
            // the # in the pile and the key value.
            // Note: the goal is to have the remaining pile be a value
            // that is 2 to the nth power minus 1.
                marbles = pile - lastKey ;
                // add to the total number of piles.
                marblesTaken += marbles ;
                return marbles;
            }
        // if the pile is 2, remove one marble and return the move as a 1.
        marblesTaken += marbles;
        return marbles;
    }
    
    /**
     * return the string with the number.
     * @return  name of the player.
     */
    public String playerName()
    {
      return name;  
    }
    
    /**
     * return the race of the Player
     * @return "SCP" for SuperComputerPlayer
     */
    public String getRace()
    {
        return race;
    }
    
    /**
     * Fill the array with values of 2 to the nth power minus 1.
     * @param upperLimit is the number of objects in the original pile.
     */
    private void generateKey(int upperLimit)
    {
        // switch to escape the for loop
        boolean meetKey = false;
        // loop until a value of 2^n - 1 is found that is higher than the
        // upperlimit
        for(int i=0; !meetKey; i++)
        {
            // assignt the key value to 2^i - 1
            int keyValue = (int)(Math.pow(2,i)) - 1 ;
            key.add(keyValue); // add it to the array
            // check if it's higher than the upper limit
            if(keyValue >= upperLimit)
            {
                // if it is, the condition is met and the loop will term.
                meetKey = true ;
            }
        }
    }
    
    /**
     * return the total number of Objects taken.
     * @return total number of Objects taken throughout a game.
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
