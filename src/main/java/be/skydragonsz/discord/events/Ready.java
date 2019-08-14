package be.skydragonsz.discord.events;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ready extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Ready.class);

    @Override
    public void onReady(ReadyEvent event) {
        logger.info("Connected to Discord");
    }
}
