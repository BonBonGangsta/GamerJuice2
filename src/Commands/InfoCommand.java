package Commands;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.Command;
import event.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class InfoCommand extends Command {

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
                "\n" + GamerJuice.prefix + "RandoDog will give you a caption with a random image of a dog", false);
        info.addField("Creator", "@BonBonGangsta", false);
        info.addField("Note", "If you see this, you owe my creator a Redbull (12 oz)", false);
        info.setColor(0xFF1493);
        event.reply(info.build());
        info.clear();
    }

}
