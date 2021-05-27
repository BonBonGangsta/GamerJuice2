/*
 * i certify that this is my original work
 * Byron De Paz PID 3999061
 */
package Nim;
import java.util.Random;

/**
 * The AboveAverageComputer will remove a random number between 1 and half 
 * of the number left in the pile.
 */
public class AboveAverageComputer implements Player {
    private String name ; // name of the AboveAverageComputer player.
    private int marblesTaken ; // total number of marbles taken.
    private final String race = "AAC"; //AboveAverageComputer Race
    /**
     * creates an AboveAverageComputer player with a default name of 
     * "Hal 9000".
     */
    public AboveAverageComputer()
    {
        name = "Hal 9000" ; // I'm sorry Greg, but I can't let you do that.
    }
    
    /**
     * Creates an AboveAverageComputer with a given name.
     * @param name name to be given to the computer player.
     */
    public AboveAverageComputer(String name)
    {
        this.name = name ;
    }
    
    /**
     * The AboveAverageComputer will randomly remove a number of objects
     * from the pile between 1 and half of the objects in the pile.
     * @param pile number of objects in the pile.
     * @return a random number between 1 and pile/2.
     */
    public int move(int pile)
    {
        int marbles = 1; // mimnum number of objects that can be removed.
        // if the pile is 2 just return 1 and win the game.
        if (pile > 3)
        {
            // create a random number generator called answer.
            Random answer = new Random();
            // generate the upper bound of for the random # number which is
            // half of the number in the pile.
            int maxRemoval = (int) (pile / 2) ;
            // the minimum is 1 and half the pile.
            marbles = answer.nextInt(maxRemoval - 1) + 1 ;
            // add the number generated to the number to the number
            // to the total taken.
            marblesTaken += marbles ;
            // return it.
            return marbles; 
        }
        // returns 1 since that is the mininum that can be taken.
        return marbles;     
    }
    
    /**
     * returns the computer's name.
     * @return String of the name.
     */
    public String playerName()
    {
        return name;
    }
    
    /**
     * return the race of the Player.
     * @return "AAC" for AboveAverageComputer.
     */
    public String getRace(){
        return race ;
    }
    
    /**
     * return the total number of Objects taken.
     * @return total number of objects.
     */
    public int objectsTaken()
    {
        return marblesTaken;
    }
    
    /**
     * add onto the number of objects taken. this is will be used when the 
     * player is the loser as they are forced to take the last object in Nim.
     * @param x number of marbles to be added to the marbles taken.
     */
    public void addToObjects (int x)
    {
        marblesTaken += x;
    }
}
