package be.skydragonsz.discord.events;

import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.CloseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Disconnect extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Disconnect.class);

    @Override
    public void onDisconnect(DisconnectEvent event) {
        CloseCode reason = event.getCloseCode();
        logger.info("Disconnected from Discord, reason: {}", reason.getMeaning());
    }
}
