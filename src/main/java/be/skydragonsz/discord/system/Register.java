package be.skydragonsz.discord.system;

import be.skydragonsz.discord.command.util.AliasCommand;
import be.skydragonsz.discord.command.util.EvalCommand;
import be.skydragonsz.discord.events.Alias;
import be.skydragonsz.discord.command.HelpCommand;
import be.skydragonsz.discord.command.administration.PrefixCommand;
import be.skydragonsz.discord.command.administration.RestartRedditCommand;
import be.skydragonsz.discord.command.administration.StopBotCommand;
import be.skydragonsz.discord.command.util.ColorCommand;
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
                    new EvalCommand(),
                    help.registerCommand(new PrefixCommand()),
                    help.registerCommand(new StopBotCommand()),
                    help.registerCommand(new RestartRedditCommand())
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
                    new Resume(),
                    new Alias()
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
                    new ColorCommand(),
                    new AliasCommand()
            ));
        }catch (Exception ex){
            logger.debug("WUT",ex);
        }
    }
}