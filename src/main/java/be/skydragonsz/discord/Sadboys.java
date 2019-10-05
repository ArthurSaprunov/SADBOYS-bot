package be.skydragonsz.discord;

import be.skydragonsz.discord.system.Register;
import be.skydragonsz.discord.system.Settings;
import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.security.auth.login.LoginException;


public class Sadboys {
    private static Logger logger = LoggerFactory.getLogger(Sadboys.class);

    private static Settings settings;
    public static ShardManager shardManager;


    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        t.setName("SADBOYS");
        setupBot();
    }

    private static void setupBot() {
        try {
            settings = SettingsManager.getInstance().getSettings();

            DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
            builder.setToken(settings.getBotToken());

            Register.registerBasicCommands(builder);
            Register.registerEvents(builder);
            Register.funCommands(builder);
            Register.googleAPI(builder);

            shardManager = builder.build();

        } catch (IllegalArgumentException e) {
            logger.error("Illegal Argument was passed thru.",e);
            System.exit(0);
        } catch (LoginException e) {
            logger.error("The botToken provided in the Config.json was incorrect.");
            logger.error("Did you modify the Config.json after it was created?",e);
            System.exit(0);
        }
    }
}
