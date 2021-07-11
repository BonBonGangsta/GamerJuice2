package com.BonBonGangsta.GamerJuice;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
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

                event.getChannel().sendMessage("Have you tried turning it off and on again, " + Objects.requireNonNull(event.getMember()).getAsMention() + "?").queue();
            }

            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "Kanye")){
                URL kanyeQuotes = new URL("http://api.kanye.rest");
                String quote;
                try{
                    JsonObject json = new JsonObject(kanyeQuotes.openStream());
                    quote = (String) json.get("quote");
                } catch (JsonIOException | IOException e){
                    e.printStackTrace();
                }
                event.getChannel().sendMessage(quote +  "- Kanye").queue();
            }


            if (args[0].equalsIgnoreCase(GamerJuice.prefix + "status")){
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
