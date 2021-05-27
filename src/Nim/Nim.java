/*
 * i certify that this is my original work
 * Byron De Paz PID 3999061
 */
package Nim;
import java.util.Random;
/**
 * creates a game of Nim, which is a 2 player game where the player to pick up
 * the last object in a Pile loses.
 * @author byrondepaz
 */
public class Nim {
    private Player firstPlayer; // Who's on first
    private Player secondPlayer; // what's on second
    // I don't know is on third...
    private Player hasNotPlayed;  // who was not played after the first round.
    private Player lastToPlay; // the last one to play
    private Pile gamePile; // pile of objects the game has
    private int pileMarbles; // number of objects in the pile
    private final int LOWERLIMIT = 10; // min number of objects needed to play
    private final int UPPERLIMIT = 100; // max ''   ''  ''  ''  ''  ''
    
    /**
     * start the game of Nim, by creating a Pile with a random initial number
     * of objects.
     * @return return that the number of objects in the pile.
     */
    public String startGame(){
        Random pileGenerator = new Random();
        pileMarbles = pileGenerator.nextInt(UPPERLIMIT - LOWERLIMIT)
                   + LOWERLIMIT;
        
        gamePile = new Pile(pileMarbles);     
        return "" + gamePile.numMarbles();
    }
    
    /**
     * conducts the player move based on the player and the number of objects
     * in the pile at the time.
     * @param Player who is up to choose their destiny.
     * @param obInPile number of objects in the pile.
     * @return a string with the player's name and how many they removed
     * from the pile as well as the current number of objects in the pile
     * after the strategic move.
     */
    public String PlayerMove(Player Player, int obInPile)
    {
        // assign the int to the number that the player chooses to remove
        int removal = Player.move(obInPile);
        // remove that number from the pile
        gamePile.removeMarbles(removal);
        // return the results of the strategic move.
        return Player.playerName() +" has removed " + removal 
                  + " objects from the pile."
                  + " the pile is now : " + gamePile.numMarbles();
    }
    
    /**
     * sets the default order of which the players will play, while also
     * assigning them to the Nim class.
     * @param first the player who should be on first.
     * @param second the player to be on second.
     */
    public void PlayerOrder(Player first, Player second){
        firstPlayer = first;
        secondPlayer = second;    
    }
    
    
    /**
     * return the number of objects left in the pile. 
     * @return number of objects in the pile at the current state.
     */
    public int remainingObjects()
    {
        return gamePile.numMarbles();
    }
    
    /**
     * returns the name of the player
     * @param player the player that is having difficulties remembering who they
     * are.
     * @return the name as a string.
     */
    public String PlayersName(Player player)
    {
        return player.playerName();
    }
    
    /**
     * return the player that was the last to successfully play a turn.
     * @return said player.
     */
    public Player lastToPlay()
    {
        return lastToPlay;
    }
    
    /**
     * return the player that was not able to take a turn.
     * @return that player.
     */
    public Player hasNotPlayed()
    {
        return hasNotPlayed;
    }
    
    /**
     * play the game until a someone picks up the last object.
     * @return return the player that won the round.
     */
    public Player playTheGame()
    {
        // assign the last player to play to the first player.
        lastToPlay = firstPlayer;
        // the second player has not played.
        hasNotPlayed = secondPlayer;
        // while the pile is greater or equal to 1 do..
        while(remainingObjects() >= 1)
        {
          // temporary player becomes the person to lastplay who 
          // in first round will be the first person to take a turn.
          Player tempPlayer = lastToPlay;
          // print out the results from that player's move.
          System.out.println(PlayerMove(tempPlayer,remainingObjects()));
          // if the last person to play is the first player
          if (lastToPlay == firstPlayer)
          {
              // assign the last to play to be the second player to the last
              lastToPlay = secondPlayer;
              // for the next round the first player has not played 
              hasNotPlayed = firstPlayer;
          }
          else
          {
              // if the last person is not the first player, then the second
              // player just played so flip their roles.
              lastToPlay = firstPlayer;
              hasNotPlayed = secondPlayer;
          }
        }
        return lastToPlay;
    }
    
    /**
     * return the total number of objects that a player has taken.
     * @param player the player in question
     * @return the number of objects that was taken.
     */
    public int totalObjectsPlayerTaken(Player player)
    {
        return player.objectsTaken();
    }
    
}

