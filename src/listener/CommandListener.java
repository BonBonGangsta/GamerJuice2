package listener;

import Commands.helpCommand;
import Commands.infoCommand;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.Command;
import event.commandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandListener {
    private static final HashMap<String, Command> commands = new HashMap<>();

    public CommandListener(){
        List<Command> botCommands = new ArrayList<>(Arrays.asList(new infoCommand(), new helpCommand()));
        for(Command command: botCommands) commands.put(command.getName().toLowerCase(), command);
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
            boolean isCommand;
            if(isMention) isCommand = true;
            else{
                String CommandName = arg.substring(prefix.length()).toLowerCase();
                isCommand = commands.containsKey(CommandName);
                if (isCommand) arg = CommandName;
            }
            if (isCommand){
                GamerJuice.debug("Command recieved: " + arg);
                Command command = commands.get(arg);
                if (isMention) command = commands.get("info");
                if (command == null){
                    GamerJuice.debug("Executing command: " + arg);
                    return;
                }
                GamerJuice.debug("Execting command: " + arg);
                command.execute(new commandEvent(event, Arrays.copyOfRange(rawMessage.split("\\s+"),1, args.length)));
            }
        }




    }
}
