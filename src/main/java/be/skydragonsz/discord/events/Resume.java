package be.skydragonsz.discord.events;

import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resume extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Resume.class);

    @Override
    public void onResume(ResumedEvent event){
        try{
            logger.info("Successfully resumed to Discord");
            ThreadConstants.reddit.restart();
        }catch (NullPointerException ex){
            logger.warn("There was somewhere a null: {}",ex);
        }catch (Exception ex){
            logger.error("Error in {}: {}",Resume.class.getSimpleName(),ex);
        }
    }
}
