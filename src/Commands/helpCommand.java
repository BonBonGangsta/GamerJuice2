package Commands;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.Command;
import event.commandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Random;

public class helpCommand extends Command {

    public helpCommand(){
        super("help");
    }

    @Override
    public void execute(commandEvent event){
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
                "*cracks open a Monster* It's boomer juice time.",
                "Sometimes I wonder if we were but on this earth just to bother Discord bots..."};
        Random rand = new Random();
        int number = rand.nextInt(statuses.length);
        event.reply(statuses[number]);
    }

}
