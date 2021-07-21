package com.BonBonGangsta.GamerJuice;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GamerJuice {
    public static JDA jda;
    public static String prefix = "~";

    // Main method, will start when the bot starts
    public static void main(String[] args) throws LoginException{

        /**
         * Get the token from file.
         */
        String token = null;

        try{
            File tokenFile = Paths.get("token.txt").toFile();
            if(!tokenFile.exists()){
                System.out.println("[ERROR] could not find token.txt file");
                System.out.println("Please paste in your bot token:");
                Scanner s = new Scanner(System.in);
                token = s.nextLine();
                System.out.println();
                System.out.println("[INFO] Creating your token.txt file - please wait *cracks open a Redbull*");
                if(!tokenFile.createNewFile()){
                    System.out.println("[ERROR] could not create token.txt -" +
                            " please create this file and past in your token." );
                    s.close();
                    return;
                }
                Files.write(tokenFile.toPath(), token.getBytes());
                s.close();
            }
            token = new String(Files.readAllBytes(tokenFile.toPath()));
        }catch(Exception e){
            e.printStackTrace();
        }
        if(token == null) return;

        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("This final project unfold into chaos."));

        jda.addEventListener(new Commands());

    }
}
