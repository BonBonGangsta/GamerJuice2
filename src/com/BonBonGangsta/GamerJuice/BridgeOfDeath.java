package com.BonBonGangsta.GamerJuice;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class BridgeOfDeath {
    private final User user;
    public enum States {GAME_CHOOSE , SECOND_QUESTION , FINAL_QUESTION , GAME_END}
    States gameState;
    public String[] colors = {"red", "green", "blue", "black", "white",
        "yellow", "orange", "brown", "pink", "purple", "gray", "grey"};
    public static boolean gameActive = false;
    private final String loserGif = "https://gfycat.com/brightglitteringcardinal";
    private final String winningGif = "https://gph.is/10jenQX";
    private String triviaQuestion = "";
    private String triviaAnswer ="";

    public BridgeOfDeath(User user){
        this.user = user;
        this.gameState = States.GAME_CHOOSE;
    }
    public User getPlayer(){
        return this.user;
    }

    public States getGameState(){
        return this.gameState;
    }

    public void setGameState(States newState){
        this.gameState = newState;
    }


   public String getTriviaQuestion(){
        return this.triviaQuestion;
   }

   public String getTriviaAnswer(){
        return this.triviaAnswer;
   }

   public String crossBridgeSuccessful(){
        return this.winningGif;
   }

   public String crossBridgeFailed(){
        return this.loserGif;
   }

   public void generateTriviaQuestion() {
        Random randomNumber = new Random();
        int Question3 = randomNumber.nextInt(3) + 1;
        int x = randomNumber.nextInt(99) + 1;
        int y = randomNumber.nextInt(99) + 1;

        if (Question3 == 1) {
            triviaQuestion += "What is " + x + " + " + y + "?";
            triviaAnswer += String.valueOf(x + y);
        } else if (Question3 == 2) {
            triviaQuestion += "What is " + x + " - " + y + "?";
            triviaAnswer += String.valueOf(x - y);
        } else if (Question3 == 3) {
            triviaQuestion += "What year is it?";
            triviaAnswer += String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        } else {
            try {
                URL apiURL = new URL("https://opentdb.com/api.php?amount=1&type=boolean");
                // create an HTTP connection
                HttpURLConnection conn;
                int responseCode;
                conn = (HttpURLConnection) apiURL.openConnection();
                // we are going to sent a GET request to hopefully return JSON string
                conn.setRequestMethod("GET");
                conn.connect();
                // Get the Response code so that we can check if the connection was successful
                responseCode = conn.getResponseCode();
                Scanner scanner;
                if (responseCode != 200) {
                    throw new RuntimeException();
                } else {
                    // create an open Stream to be scanned through,
                    // this will be the JSON text, however, we will need to scan through it first before
                    // converting it to a JSON object and then retrieving our data.
                    scanner = new Scanner(apiURL.openStream());
                }
                String inline = "";
                GamerJuice.debug("inline has been created with the follwing: " + inline);
                // while there is text in the Scanner, create a large string of all the text
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                // close the scanner file to free up memory
                scanner.close();
                GamerJuice.debug("After parsing in the HTML: " + inline);

                // create a Object mapper to parse through the text that we received and create a tree
                // then from the tree we can convert to a JSON Node and from JSON node to the data we need.
                ObjectMapper mapper = new ObjectMapper();
                JsonNode triviaJsonNode = mapper.readTree(inline);
                GamerJuice.debug("JSON node as text: " + triviaJsonNode.asText());
                if(triviaJsonNode.get("type").asText().equalsIgnoreCase("boolean")){
                    triviaQuestion += "True/False: ";
                    triviaQuestion += triviaJsonNode.get("question");
                    triviaAnswer += triviaJsonNode.get("correct_answer");
                }
                GamerJuice.debug("The type of trivia question: " + triviaJsonNode.get("type").asText());
                GamerJuice.debug("The question from the trivia API: " + triviaJsonNode.get("question").asText());
                GamerJuice.debug("The answer from the trivia API: " + triviaJsonNode.get("correct_answer").asText());
                GamerJuice.debug("The final trivia answer as parsed in: " + triviaAnswer);
                GamerJuice.debug("the final question as parsed in: " + triviaQuestion);



            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
