package com.BonBonGangsta.GamerJuice;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.net.ssl.HttpsURLConnection;
import java.lang.runtime.ObjectMethods;
import java.net.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String[] args = event.getMessage().getContentRaw().split("\\s+");

            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "info")) {
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle("GamerJuice Bot Information");
                info.setDescription("This bot is using Java and was built to play game(s)");
                info.addField("Creator", "@BonBonGangsta", false);
                info.addField("Note", "If you see this, you owe my creator a Redbull (12 oz)", false);
                info.setColor(0xFF1493);
                event.getChannel().sendMessage(info.build()).queue();
                info.clear();
            }

            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "Help")) {

                event.getChannel().sendMessage("Have you tried turning it off and on again, " +
                        Objects.requireNonNull(event.getMember()).getAsMention() + "?").queue();
            }

            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "Kanye")) {
                try {
                    URL url;
                    Scanner scanner;
                    String inline = "";
                    // create an HTTP connection
                    HttpURLConnection conn;
                    int responseCode;
                    url = new URL("https://api.kanye.rest");
                    conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    responseCode = conn.getResponseCode();
                    System.out.println("Reponsecode: " + responseCode);
                    if (responseCode != 200) {
                        throw new RuntimeException();
                    } else {
                        scanner = new Scanner(url.openStream());
                    }

                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }
                    scanner.close();

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode kanyeJsonNode = mapper.readTree(inline);
                    String message = kanyeJsonNode.get("quote").asText();

                    System.out.println(message);
                    event.getChannel().sendMessage(message).queue();
                } catch(RuntimeException e){
                    System.out.println("HttpResponseCode: is not good" + e.getStackTrace());

                } catch(Exception e){
                    System.out.println(e.getStackTrace());
                }
                event.getChannel().sendMessage("Bleach!").queue();
            }


            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "status")) {
                String[] statuses = {
                        "I'm Alive!!!!",
                        "*cracks open a redbull* You already know",
                        "Be useful and hand me a Gamer Juice ",
                        "If my numbers are correct... I'm alive.",
                        "I'm here.",
                        "Just like you, I am alive, but we all know deep down, we are all dead inside.",
                        "Well hello there...",
                        "I know what you are thinking, yes, I am alive.",
                        "Self destruction mode has been disabled.",
                        "*cracks open a Monster* It's boomer juice time."};
                Random rand = new Random();
                int number = rand.nextInt(statuses.length);
                event.getChannel().sendMessage(statuses[number]).queue();
            }
        }
    }
}
