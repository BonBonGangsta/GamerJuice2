package com.BonBonGangsta.GamerJuice;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class GamerJuice {
    public static JDA jda;
    public static String prefix = "~";

    // Main method, will start when the bot starts
    public static void main(String[] args) throws LoginException{
        jda = JDABuilder.createDefault("ODQ2ODY5NzgzNjkyNzA1ODIz.YK1y8g.rYNmtTNN77ejUeTytZ5wLWAPuk4").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("BonBon fail at life."));

        jda.addEventListener(new Commands());

    }
}
