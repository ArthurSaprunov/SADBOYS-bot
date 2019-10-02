package be.skydragonsz.discord.events;

import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reconnect extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Reconnect.class);

    @Override
    public void onReconnect(ReconnectedEvent event) {
        try{
            logger.info("Successfully reconnected to Discord");
            ThreadConstants.reddit.restart();
        }catch (NullPointerException ex){
            logger.warn("There was somewhere a null: {}",ex);
        }catch (Exception ex){
            logger.error("Error in {}: {}",Reconnect.class.getSimpleName(),ex);
        }
    }






}
