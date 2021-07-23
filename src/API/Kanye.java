package API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.APIs;
import event.commandEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Kanye extends APIs {

    private static URL apiURL = null;

    static {
        try {
            apiURL = new URL("https://api.kanye.rest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    ;
    private String inline ="";


    public Kanye() throws MalformedURLException {
        super("Kanye", apiURL);
    }

    @Override
    public void execute(commandEvent event){
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

            // while there is text in the Scanner, create a large string of all the text
            while(scanner.hasNext()){
                inline += scanner.nextLine();
            }
            // close the scanner file to free up memory
            scanner.close();

            // create a Object mapper to parse through the text that we received and create a tree
            // then from the tree we can conver to a JSON Node and from JSON node to the data we need.
            ObjectMapper mapper = new ObjectMapper();
            JsonNode kanyeJsonNode = mapper.readTree(inline);
            String message = kanyeJsonNode.get("quote").asText();
            // finally respond with the quote from Yezzy himself
            event.reply(message + " -Kanye");

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
