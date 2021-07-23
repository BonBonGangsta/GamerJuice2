package listener;

import API.Kanye;
import API.Shiba;
import Commands.statusCommand;
import Commands.infoCommand;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.APIs;
import entity.Command;
import event.commandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final HashMap<String, APIs> apis = new HashMap<>();

    public CommandListener() throws MalformedURLException {
        List<Command> botCommands = new ArrayList<>(Arrays.asList(new infoCommand(), new statusCommand()));
        List<APIs> apiCommands = new ArrayList<>(Arrays.asList(new Kanye(), new Shiba()));
        for(Command command: botCommands) commands.put(command.getName().toLowerCase(), command);
        for(APIs api: apiCommands) apis.put(api.getName().toLowerCase(), api);
        System.out.println("[INFO] loaded " + commands.size() + " commands.");
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        User user = event.getAuthor();
        Message message = event.getMessage();
        TextChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        String rawMessage = message.getContentRaw();
        String[] args = rawMessage.split("\\s+");
        if (args.length > 0){
            boolean isMention = rawMessage.equals("<@" + event.getJDA().getSelfUser().getId() + ">") ||
                    rawMessage.equals("<@!" + event.getJDA().getSelfUser().getId() + ">");
            String prefix = GamerJuice.prefix;
            String arg = args[0].toLowerCase();
            GamerJuice.debug(arg.toString());
            boolean isCommand = false;
            boolean isAPICall = false;
            if(isMention) isCommand = true;
            else{
                String CommandName = arg.substring(prefix.length()).toLowerCase();
                isCommand = commands.containsKey(CommandName);
                isAPICall = apis.containsKey(CommandName);
                if (isCommand || isAPICall) arg = CommandName;
            }
            if (isCommand || isAPICall){
                GamerJuice.debug("Command recieved: " + arg);
                Command command = null;
                APIs apiCall;
                if(isCommand){
                    if (isMention) command = commands.get("info");
                    else command = commands.get(arg);
                    GamerJuice.debug("Execting command: " + arg);
                    command.execute(new commandEvent(event, Arrays.copyOfRange(rawMessage.split("\\s+"),1, args.length)));
                }
                else if(isAPICall){
                    apiCall = apis.get(arg);
                    GamerJuice.debug("Execting API call command: " + arg);
                    apiCall.execute(new commandEvent(event, Arrays.copyOfRange(rawMessage.split("\\s+"),1, args.length)));
                }
                
                if (command == null){
                    GamerJuice.debug("Executing null command: " + arg);
                }
                
            }
        }




    }

}
