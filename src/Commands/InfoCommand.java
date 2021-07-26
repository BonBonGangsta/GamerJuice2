package Commands;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.Command;
import event.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class InfoCommand extends Command {

    public static final String gitHubURL = "https://github.com/BonBonGangsta/GamerJuice2.git";
    public static final String twitchTVURL = "https://www.twitch.tv/BonBonGangsta";
    public static final String linkedIn = "www.linkedin.com/in/byron-de-paz-054315b0";
    public InfoCommand(){
        super("info");
    }

    @Override
    public void execute(CommandEvent event){

        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("GamerJuice Bot Information");
        info.setDescription("This bot is using Java and was built to play game(s)");
        info.addField("Commands available currently:",
                "\n" + GamerJuice.prefix + "Kanye will trigger a quote from Yeezus himself." +
                "\n" + GamerJuice.prefix + "Shiba will give you a random shiba image to brighten your day" +
                "\n" + GamerJuice.prefix + "RandoDog will give you a caption with a random image of a dog" +
                "\n" + GamerJuice.prefix + "Game, will allow you to play a game." +
                        "\n\tcurrently the only game can be triggered by saying 'I wish to cross the bridge of Death'\n\tafter the Game command", false);
        info.addField("Creator", "@BonBonGangsta", false);
        info.addField("Note", "If you see this, you owe my creator a Redbull (12 oz)", false);
        info.addField("Github", gitHubURL, false);
        info.addField("TwitchTv", twitchTVURL, false);
        info.addField("LinkedIn", linkedIn, false);
        info.setColor(0xFF1493);
        event.reply(info.build());
        info.clear();
    }
}
