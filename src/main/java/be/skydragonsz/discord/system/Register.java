package be.skydragonsz.discord.system;

import be.skydragonsz.discord.command.AliasCommand;
import be.skydragonsz.discord.command.HelpCommand;
import be.skydragonsz.discord.command.administration.Prefix;
import be.skydragonsz.discord.command.administration.RestartReddit;
import be.skydragonsz.discord.command.administration.StopBot;
import be.skydragonsz.discord.command.util.Alias;
import be.skydragonsz.discord.command.util.Color;
import be.skydragonsz.discord.events.Disconnect;
import be.skydragonsz.discord.events.Ready;
import be.skydragonsz.discord.events.Reconnect;
import be.skydragonsz.discord.events.Resume;

import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Register {
    private static Settings settings = SettingsManager.getInstance().getSettings();
    private static Logger logger = LoggerFactory.getLogger(Register.class);

    public static void registerBasicCommands(DefaultShardManagerBuilder builder) {
        HelpCommand help = new HelpCommand();
        try {
            builder.addEventListeners(Arrays.asList(
                    help,
                    help.registerCommand(new Prefix()),
                    help.registerCommand(new StopBot()),
                    help.registerCommand(new RestartReddit())
            ));
        }catch (Exception ex){
            logger.debug("WUT",ex);
        }

    }

    public static void registerEvents(DefaultShardManagerBuilder builder){
        try{
            builder.addEventListeners(Arrays.asList(
                    new Ready(),
                    new Reconnect(),
                    new Disconnect(),
                    new Resume()
            ));
        }catch (Exception ex){
            logger.debug("WUT",ex);
        }
    }

    public static void googleAPI(DefaultShardManagerBuilder builder){
        if(settings.getGoogleApiKey() == null) return;

    }

    public static void funCommands(DefaultShardManagerBuilder builder) {
        try{
            builder.addEventListeners(Arrays.asList(
                    new Color(),
                    new AliasCommand(),
                    new Alias()
            ));
        }catch (Exception ex){
            logger.debug("WUT",ex);
        }
    }
}