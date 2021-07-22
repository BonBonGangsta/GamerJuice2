package event;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/** the purpose of this Class is to simplify and clean some of the code that we will be constantly using such as
 *  send a reply to the channel which activated the bot, return the args, return the event, getting users from the
 *  events and more
 */
public class commandEvent {

    private final GuildMessageReceivedEvent event;
    private String[] args;

    public commandEvent(GuildMessageReceivedEvent event, String[] args){
        this.event = event;
        this.args = args;
    }

    public String[] getArgs(){
        return this.args;
    }

    public GuildMessageReceivedEvent getEvent(){
        return this.event;
    }

    public void reply(String message){
        sendMessage(event.getChannel(),message);
    }

    public void reply(MessageEmbed embed){
        event.getChannel().sendMessage(embed).queue();
    }

    public void reply(Message message){
        event.getChannel().sendMessage(message).queue();
    }

    public void sendMessage(MessageChannel chan, String message){
        chan.sendMessage(message).queue();
    }

    SelfUser getSelfUser(){
        return event.getJDA().getSelfUser();
    }

    public User getAuthor(){
        return event.getAuthor();
    }

    public Guild getGuild(){
        return event.getGuild();
    }

    public JDA getJDA(){
        return event.getJDA();
    }

    public Member getMember(){
        return event.getMember();
    }

    public Message getMessage(){
        return event.getMessage();
    }

    public TextChannel getTextChannel(){
        return event.getChannel();
    }
}
