package com.BonBonGangsta.GamerJuice;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class BridgeOfDeath {
    long gameMessageID;
    long channelID;
    User user;
    String[] colors = ["red" , "blue" , "pink", ""]
    public boolean gameActive = false;

    public long lastAction;

    public BridgeOfDeath(User user){
        this.user = user;
    }

   public void setGameMessage(Message gameMessage){
        this.gameMessageID = gameMessage.getIdLong();
        this.channelID = gameMessage.getChannel().getIdLong();
   }

   public void newBridgeOfDeath(MessageChannel channel){
        if(!gameActive){
            gameActive = true;
        }
   }
}
