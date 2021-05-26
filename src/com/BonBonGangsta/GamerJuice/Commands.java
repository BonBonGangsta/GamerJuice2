package com.BonBonGangsta.GamerJuice;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(GamerJuice.prefix + "info")){
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("GamerJuice Bot Information");
            info.setDescription("This bot is using Java and was built to play game(s)");
            info.addField("Creator","@BonBonGangsta", false);
            info.addField("Note", "If you see this, you owe my creator a Redbull (12 oz)",false);
            info.setColor(0xFF1493);
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }

        if (args[0].equalsIgnoreCase(GamerJuice.prefix + "Help")){

            event.getChannel().sendMessage("I swear to god " + event.getMember().getAsMention() + ", I will rearrange your organs.").queue();
        }

        if( args[0].equalsIgnoreCase(GamerJuice.prefix + "pickup")){
            Document doc = null;
            try {
                doc = Jsoup.connect("http://www.pickuplinegen.com/").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element content = doc.getElementById("content");
            //System.out.println(content.text());
            event.getChannel().sendMessage(content.text() + " 👀 🍆 💦").queue();

        }
    }
}
