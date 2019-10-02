package be.skydragonsz.discord.events;

import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.CloseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Disconnect extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Disconnect.class);

    @Override
    public void onDisconnect(DisconnectEvent event) {
        try {
            CloseCode reason = event.getCloseCode();
            logger.info("Disconnected from Discord, reason: {}", reason.getMeaning());
            ThreadConstants.reddit.terminate();
        }catch (NullPointerException ex){
            logger.warn("There was somewhere a null: {}",ex);
        }catch (Exception ex){
            logger.error("Error in {}: {}",Disconnect.class.getSimpleName(),ex);
        }

    }
}
