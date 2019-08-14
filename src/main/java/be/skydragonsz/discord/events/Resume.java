package be.skydragonsz.discord.events;

import be.skydragonsz.discord.reddit.RedditFetcher;
import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.core.events.ResumedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resume extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Resume.class);

    @Override
    public void onResume(ResumedEvent event){
        logger.info("Successfully resumed to Discord");
        ThreadConstants.reddit = new RedditFetcher();
        ThreadConstants.reddit.start();
    }
}
