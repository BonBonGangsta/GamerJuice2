
package Nim;

/**
 * Pile is a pile of marbles, who's whole purpose in life is to keep track of
 * how many marbles are left and to remove marbles.
 */
public class Pile {
    private int totalMarbles; // total number of marbles in the pile
    
    /**
     * Create a pile of Marbles with an initial amount.
     * @param marbles initial amount of marbles to be used.
     */
    public Pile(int marbles)
    {
        totalMarbles = marbles;
    }
    
    /**
     * return the number of marbles left in the pile.
     * @return number of marbles left in the pile.
     */
    public int numMarbles()
    {
        return totalMarbles;
    }
    
    /**
     * Remove a certain amount of marbles from the pile.
     * @param numToRemove number of marbles to be removed.
     */
    public void removeMarbles(int numToRemove)
    {
        totalMarbles -= numToRemove;
    }
}
