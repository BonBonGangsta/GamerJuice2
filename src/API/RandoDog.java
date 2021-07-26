package API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.APIs;
import event.CommandEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class RandoDog extends APIs {
    private static URL apiURL = null;
    private final String[] captions = {"It is too dangerous, take this Doggo with you", "Thanks fur the memories" ,
    "Dogs are my favorite people.", "I will always woof you." , "Anything is paws-ible with a dog by your side."
    ,"The more people I meet, the more I love my dog.", "Dogs are the universe's way of apologizing for your relatives.",
    "cuteness overload, am I right?"};
    static{
        try{
            apiURL = new URL("https://random.dog/woof.json");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    public RandoDog() throws MalformedURLException{
        super("RandoDog", apiURL);
    }

    @Override
    public void execute(CommandEvent event){
        try{
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
            if(responseCode != 200 ) {throw new RuntimeException();}
            else{
                // create an open Stream to be scanned through,
                // this will be the JSON text, however, we will need to scan through it first before
                // converting it to a JSON object and then retrieving our data.
                scanner = new Scanner(apiURL.openStream());
            }
            String inline ="";
            // while there is text in the Scanner, create a large string of all the text
            while(scanner.hasNext()){
                inline += scanner.nextLine();
            }
            // close the scanner file to free up memory
            scanner.close();

            // create a Object mapper to parse through the text that we received and create a tree
            // then from the tree we can convert to a JSON Node and from JSON node to the data we need.
            ObjectMapper mapper = new ObjectMapper();
            JsonNode randoDoggoJsonNode = mapper.readTree(inline);
            String imageURL = randoDoggoJsonNode.get("url").asText();
            // finally respond with a message and the image
            Random rand = new Random();
            int number = rand.nextInt(captions.length);
            event.reply(captions[number]);
            event.reply(imageURL);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
