package be.skydragonsz.discord.events;

import be.skydragonsz.discord.reddit.RedditFetcher;
import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.CloseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reconnect extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Reconnect.class);

    @Override
    public void onReconnect(ReconnectedEvent event) {
        logger.info("Successfully reconnected to Discord");
        ThreadConstants.reddit = new RedditFetcher();
        ThreadConstants.reddit.start();
    }






}
