/*
package Util;

import com.BonBonGangsta.GamerJuice.BridgeOfDeath;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class GameUtil {
    private static final HashMap<Long, BridgeOfDeath> games = new HashMap<>();

    public static void setGame(long userId, BridgeOfDeath game) {
        games.put(userId, game);
    }

    public static boolean hasGame(long userId) {
        return games.containsKey(userId);
    }

    public static BridgeOfDeath getGame(long userId) {
        return games.get(userId);
    }

    public static void removeGame(long userId) {
        games.remove(userId);
    }

    public static void sendInitialQuestion(MessageChannel channel, User user) {
        BridgeOfDeath theGame = GameUtil.getGame(user.getIdLong());

    }

}
*/
