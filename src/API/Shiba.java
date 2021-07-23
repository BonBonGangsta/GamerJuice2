package API;

import com.BonBonGangsta.GamerJuice.GamerJuice;
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

public class Shiba extends APIs {
    private static URL apiURL = null;
    static {
        try{
            apiURL = new URL("http://shibe.online/api/shibes?count=1&urls=true");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private String inline = "";

    public Shiba() throws MalformedURLException{
        super("Shiba" , apiURL);
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
            GamerJuice.debug(inline);
            // since this API only returns one string of for the image of the Shiba of the day or random shiba,
            // we will just remove the first 2 number and the last two
            String message = inline.substring(2,inline.length()-2);
            GamerJuice.debug("Execting API call command: Shiba image given is linked here " + message);


            event.reply(message);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
