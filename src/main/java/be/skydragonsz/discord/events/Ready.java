package be.skydragonsz.discord.events;

import be.skydragonsz.discord.Sadboys;
import be.skydragonsz.discord.threads.RedditFetcher;
import be.skydragonsz.discord.system.ThreadConstants;
import be.skydragonsz.discord.threads.WordOfTheDay;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ready extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Ready.class);

    @Override
    public void onReady(ReadyEvent event) {
        try{
            logger.info("Connected to Discord");
            Sadboys.setAPI(event.getJDA());
            logger.info("Setting up API");
            ThreadConstants.reddit = new RedditFetcher();
            ThreadConstants.reddit.setDaemon(true);
            ThreadConstants.reddit.start();
            //ThreadConstants.wotd = new WordOfTheDay();
            //ThreadConstants.wotd.start();
        }catch (NullPointerException ex){
            logger.warn("There was somewhere a null: {}",ex);
        }catch (Exception ex){
            logger.error("Error in {}: {}",Ready.class.getSimpleName(),ex);
        }
    }
}
