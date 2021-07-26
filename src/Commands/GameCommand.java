/*
package Commands;

import Util.GameUtil;
import com.BonBonGangsta.GamerJuice.BridgeOfDeath;
import com.BonBonGangsta.GamerJuice.GamerJuice;
import entity.Command;
import event.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class GameCommand extends Command {
    public GameCommand(String name){
        super(name);
    }

    @Override
    public void execute(CommandEvent event){
        User user = event.getAuthor();
        String[] args = event.getArgs();
        String prefix = GamerJuice.prefix;
        BridgeOfDeath game;
        if (!GameUtil.hasGame(user.getIdLong())) {
            game = new BridgeOfDeath(user);
            GameUtil.setGame(user.getIdLong(), game);
        } else game = GameUtil.getGame(user.getIdLong());

        String userInput = this.getName().toLowerCase();
        GamerJuice.debug("Processing game input: " + userInput);
        if (userInput.equalsIgnoreCase("I wish to cross the bridge of death")) {
            if (!BridgeOfDeath.gameActive) {

            } else {
                event.reply(user.getAsMention() + ", you already have an active game.\nUse `" + prefix
                        + "stop` to stop your current game first.");
            }
        }
        Guild guild = event.getGuild();
        TextChannel channel = event.getTextChannel();
        BridgeOfDeath.playGame(event, userInput);
        if (userInput.equals("stop")) {
            GameUtil.removeGame(user.getIdLong());
        }
    }
}
*/
