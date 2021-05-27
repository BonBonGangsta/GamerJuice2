///*
// * i certify that this is my original work
// * Byron De Paz PID 3999061
// */
//package Nim;
//
//import java.io.IOException;
//import java.util.Random;
//import java.util.Scanner;
//import javax.swing.JOptionPane;
//
//public class MarblesNim
//{
//
//     //public static void main(String[] args) throws IOException
//     {
//        boolean keepPlaying = true; // i need a switch for contiuning playing
//        Player player1 = null; // holder for the first player.
//        Player player2 = null; // place holder for the second player.
//
//        // while keep playing is true do..
//        while(keepPlaying)
//        {
//          // the code for this confirmation can be seen in JOptionPane javadoc
//          // ask the user if they want to play a game with a Yes or no option.
//          int response = JOptionPane.showConfirmDialog(null,
//                  "Want to play a game of Nim?", "Confirm",
//                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//          // if no, then stop.
//          if (response == JOptionPane.NO_OPTION)
//          {
//            // No was clicked in the dialog box
//            System.out.println("If you change your mind, please rerun the app");
//            keepPlaying = false;
//          }
//          // if they "accidently" closed the dialog box, stop.
//          else if (response == JOptionPane.CLOSED_OPTION)
//          {
//            // they closed the box
//            System.out.println("Looks like you closed the dialog box, please"
//                   + " run the program again.");
//            keepPlaying = false;
//          }
//          // they actually want to play a game.
//          else if (response == JOptionPane.YES_OPTION)
//          {
//              // initialize the game of Nim.
//              Nim game = new Nim();
//              // print the starting of the game.
//              System.out.println(game.startGame());
//              // get the first player and pass on the current number of objects
//              // in the pile
//              player1 = getPlayers("player 1", game.remainingObjects());
//              System.out.println(game.PlayersName(player1) +
//                      " has entered the arena");
//              // do the same for player 2.
//              player2 = getPlayers("player 2" , game.remainingObjects());
//              System.out.println(game.PlayersName(player2) + " has entered"
//                      + " the arena.");
//              // ask if the user wants to switch the player order.
//              switchPlayers(game, player1, player2);
//              // get the winner by having Nim player the game a return the
//              // winner.
//              Player winner = game.playTheGame();
//              // print the winner's total number of objects taken.
//              System.out.println(game.PlayersName(winner) + " won " +
//                      "by taking " + game.totalObjectsPlayerTaken(winner) +
//                      " total number of marbles.");
//              // print out the loser
//              System.out.println(game.PlayersName(game.hasNotPlayed()) +
//                      " loses with a total number of " +
//                      game.totalObjectsPlayerTaken(game.hasNotPlayed()) +
//                      " marbles.");
//          }
//        }
//        }
//
//     /**
//      * Ask the user if they want to swap the order of the players.
//      * @param game the game that will have their players swapped.
//      * @param one player one.
//      * @param two player two.
//      */
//     public static void switchPlayers(Nim game, Player one, Player two)
//     {
//         // create a switch to know if the user want's to swap.
//         boolean switchPlayers = false;
//         // ask the user and take the string as a response
//         String swap = JOptionPane.showInputDialog("The default order of" +
//                 " playing is player 1 first and player 2 second, do you"
//                 + " want to swap their order? Yes / No ");
//         // if the user response with anything that resembles yes or y
//         if(swap.equalsIgnoreCase("yes") || swap.equalsIgnoreCase("y"))
//         {
//             // set the players in the order requested.
//             game.PlayerOrder(two,one);
//             JOptionPane.showMessageDialog(null, "The order has been switched");
//         }
//         else
//         {
//             // if they put anything else, then defualt it is.
//         game.PlayerOrder(one,two);
//         JOptionPane.showMessageDialog(null, "The order has not been affected");
//         }
//     }
//
//     /**
//      * ask the user who the players will be and create them.
//      * @param player the default strings for player 1 and player 2
//      * @param pileNum the number of current objects in the pile.
//      * @return a Player of either Hum, AAC, or SCP
//      */
//     public static Player getPlayers(String player, int pileNum)
//     {
//         // create a temporary player
//         Player tempPlay = null;
//         // create a switch to see if the valid player is created.
//         boolean validPlayer = false;
//         // while valid player is false, do...
//         while (!validPlayer)
//         {
//         // ask the user who the player will be
//         String input = JOptionPane.showInputDialog("Who will " + player + "?"
//         + " Human, AboveAverageComputer or SuperComputer");
//         // if the user chooses human or HUM as the race is called.
//         if(input.equalsIgnoreCase("human") || input.equalsIgnoreCase("hum"))
//         {
//            // ask for the name.
//            String name = getNameOfPlayer();
//            // if the name is empty or null create a human player with the
//            // default name.
//            if(name.equals("") || name == null)
//            {
//                validPlayer = true;
//                return tempPlay = new Human();
//            }
//            else
//            {
//                // create the human player with the given name.
//                validPlayer = true;
//                return tempPlay = new Human(name);
//            }
//         }
//         // if the user chooses aboveaveragecomputer or AAC as the race states
//         else if (input.equalsIgnoreCase("aboveaveragecomputer")||
//                 input.equalsIgnoreCase("AAC"))
//         {
//             // ask for the name
//            String name = getNameOfPlayer();
//            // no name means, everything to default.
//            if(name.equals("") || name == null)
//            {
//                validPlayer = true;
//                return tempPlay = new AboveAverageComputer();
//            }
//            else
//            {
//                // create the AAC with the given name.
//                validPlayer = true;
//                return tempPlay = new AboveAverageComputer(name);
//            }
//         }
//         // if the user chooses a supercomptuer or SCP
//         else if (input.equalsIgnoreCase("supercomputer")
//                 || input.equalsIgnoreCase("SCP"))
//         {
//             //again ask for the name
//             String name = getNameOfPlayer();
//            // no name means default name.
//            if(name.equals("") || name == null)
//            {
//                validPlayer = true;
//                // pass the number of objects in the pile as a parameter
//                // so that the supercomputer can create the key it needs to
//                // win *wink* *wink*
//                return tempPlay = new SuperComputerPlayer(pileNum);
//            }
//            else // create it with the given name
//            {
//                validPlayer = true;
//                return tempPlay = new SuperComputerPlayer(name, pileNum);
//            }
//         }
//     }
//         return tempPlay; // return the valid player
//     }
//
//     /**
//      * ask the user for a name for the player.
//      * @return the string of the given name.
//      */
//     public static String getNameOfPlayer()
//     {
//         String input = JOptionPane.showInputDialog("What name will you give"
//         + " the player?");
//         return input;
//     }
//}
