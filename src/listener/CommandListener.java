package listener;

import API.Kanye;
import API.Shiba;
import API.RandoDog;
import Commands.StatusCommand;
import Commands.InfoCommand;
import com.BonBonGangsta.GamerJuice.BridgeOfDeath;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.APIs;
import entity.Command;
import event.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final HashMap<String, APIs> apis = new HashMap<>();
    ArrayList<BridgeOfDeath> activeGames = new ArrayList<>();

    public CommandListener() throws MalformedURLException {
        List<Command> botCommands = new ArrayList<>(Arrays.asList(new InfoCommand(), new StatusCommand()));
        List<APIs> apiCommands = new ArrayList<>(Arrays.asList(new Kanye(), new Shiba(), new RandoDog()));
        for(Command command: botCommands) commands.put(command.getName().toLowerCase(), command);
        for(APIs api: apiCommands) apis.put(api.getName().toLowerCase(), api);
        System.out.println("[INFO] loaded " + commands.size() + " commands.");
    }

    public void gameProcess(Message message){
        for(BridgeOfDeath game: activeGames){
            if(game.getPlayer() != message.getAuthor()) continue;
            String choice = message.getContentRaw();
            //message.getChannel().sendMessage("choice give is : " + choice).queue();
            boolean gameWon = false;
            switch (game.getGameState()){
                case GAME_CHOOSE:
                    if(choice.equalsIgnoreCase("I wish to cross the bridge of death")){
                       message.getChannel().sendMessage("He who wishes to cross the bridge Death, must answer " +
                               "me these questions three\nWhat is your name?").queue();
                       game.setGameState(BridgeOfDeath.States.SECOND_QUESTION);
                    }
                    else{
                        message.getChannel().sendMessage(game.crossBridgeFailed()).queue();
                        activeGames.remove(game);
                    }
                    break;
                case SECOND_QUESTION:
                    if(choice.equalsIgnoreCase(game.getPlayer().getName()) ||
                            choice.equalsIgnoreCase(game.getPlayer().getAsTag()) ||
                            choice.equalsIgnoreCase(game.getPlayer().getId())||
                            choice.equalsIgnoreCase(game.getPlayer().getAsMention())){
                        message.getChannel().sendMessage("What is your favorite Color?").queue();
                        game.setGameState(BridgeOfDeath.States.FINAL_QUESTION);
                    }
                    else{
                        message.getChannel().sendMessage(game.crossBridgeFailed()).queue();
                        activeGames.remove(game);
                    }
                    break;
                case FINAL_QUESTION:
                    boolean colorMatch = false;
                    for (String color: game.colors){
                        if (color.equalsIgnoreCase(choice)) {
                            colorMatch = true;
                            break;
                        }
                    }
                    if (colorMatch){
                        game.generateTriviaQuestion();
                        message.getChannel().sendMessage(game.getTriviaQuestion()).queue();
                        game.setGameState(BridgeOfDeath.States.GAME_END);
                    }
                    else{
                        message.getChannel().sendMessage(game.crossBridgeFailed()).queue();
                        activeGames.remove(game);
                    }
                    break;
                case GAME_END:
                    if(choice.equalsIgnoreCase(game.getTriviaAnswer())){
                        gameWon = true;
                        message.getChannel().sendMessage("Be on your way").queue();
                        message.getChannel().sendMessage(game.crossBridgeSuccessful()).queue();
                        activeGames.remove(game);
                    }
                    else{
                        message.getChannel().sendMessage(game.crossBridgeFailed()).queue();
                        activeGames.remove(game);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + game.getGameState());
            }
        }
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if(event.getAuthor().isBot()){return;}
        User user = event.getAuthor();
        Message message = event.getMessage();
        gameProcess(message);
        TextChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        String rawMessage = message.getContentRaw();
        String[] args = rawMessage.split("\\s+");
        if (args.length > 0){
            boolean isMention = rawMessage.equals("<@" + event.getJDA().getSelfUser().getId() + ">") ||
                    rawMessage.equals("<@!" + event.getJDA().getSelfUser().getId() + ">");
            String prefix = GamerJuice.prefix;
            String arg = args[0].toLowerCase();
            GamerJuice.debug(arg);
            boolean isCommand;
            boolean isAPICall = false;
            boolean isGameCall = arg.substring(prefix.length()).equalsIgnoreCase("Game");
            if(isMention) isCommand = true;
            else{
                String CommandName = arg.substring(prefix.length()).toLowerCase();
                isCommand = commands.containsKey(CommandName);
                isAPICall = apis.containsKey(CommandName);
                if (isCommand || isAPICall) arg = CommandName;
            }
            if (isCommand || isAPICall || isGameCall){
                GamerJuice.debug("Command recieved: " + arg);
                Command command = null;
                APIs apiCall;
                if(isCommand){
                    if (isMention) command = commands.get("info");
                    else command = commands.get(arg);
                    GamerJuice.debug("Execting command: " + arg);
                    command.execute(new CommandEvent(event, Arrays.copyOfRange(rawMessage.split("\\s+"),1, args.length)));
                }
                else if(isAPICall){
                    apiCall = apis.get(arg);
                    GamerJuice.debug("Execting API call command: " + arg);
                    apiCall.execute(new CommandEvent(event, Arrays.copyOfRange(rawMessage.split("\\s+"),1, args.length)));
                }
                else if(isGameCall){
                    GamerJuice.debug("Game has been called, I need to ask what game!");
                    event.getChannel().sendMessage("What Game would you like to play?").queue();
                    BridgeOfDeath newGame = new BridgeOfDeath(event.getAuthor());
                    activeGames.add(newGame);
                    //event.getChannel().sendMessage("Game has been added with player " + newGame.getPlayer().getName()).queue();
                }
                
                if (command == null){
                    GamerJuice.debug("Executing null command: " + arg);
                }
                
            }
        }




    }

}
