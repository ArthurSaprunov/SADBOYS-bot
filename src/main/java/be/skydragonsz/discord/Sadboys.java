package be.skydragonsz.discord;

import be.skydragonsz.discord.events.Reconnect;
import be.skydragonsz.discord.system.Register;
import be.skydragonsz.discord.exeptions.APIExeption;
import be.skydragonsz.discord.system.Settings;
import be.skydragonsz.discord.system.SettingsManager;
import be.skydragonsz.discord.system.ThreadConstants;
import com.sun.org.apache.regexp.internal.RE;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class Sadboys {
    private static Logger logger = LoggerFactory.getLogger(Sadboys.class);

    private static Settings settings;
    private static JDA api;


    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        t.setName("SADBOYS");
        setupBot();

    }

    private static void setupBot() {
        try {
            settings = SettingsManager.getInstance().getSettings();

            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT)
                    .setToken(settings.getBotToken())
                    .setAudioEnabled(false)
                    .setAutoReconnect(true);

            api = jdaBuilder.build();


            Register.api = api;
            Register.resiterBasicCommands();
            Register.registerEvents();
            Register.funCommands();
            if (settings.getGoogleApiKey() != null) {
                Register.googleAPI();
            }


            ThreadConstants.reddit.start();
        } catch (APIExeption e) {
            logger.warn("API was not registerd!", e);
            System.exit(0);
        } catch (IllegalArgumentException e) {
            logger.error("No login details provided! Please provide a botToken in the config.",e);

            System.exit(0);
        } catch (LoginException e) {
            logger.error("The botToken provided in the Config.json was incorrect.");
            logger.error("Did you modify the Config.json after it was created?",e);
            System.exit(0);
        }
    }


    public static JDA getAPI() {
        return api;
    }
}
