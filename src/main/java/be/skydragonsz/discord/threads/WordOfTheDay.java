package be.skydragonsz.discord.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordOfTheDay extends Thread {
    private Logger logger = LoggerFactory.getLogger(WordOfTheDay.class);

    private long timer = 60000 *60 *24; //only once a day.
    private long currentTime;
    private long timeKeeper;

    private boolean isRunning;

    public WordOfTheDay() {
        isRunning = true;
    }

    public void terminate() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        timeKeeper = System.currentTimeMillis();
        logger.info("Starting {} Thread!",WordOfTheDay.class.getSimpleName());
        while (isRunning){
            try{
                currentTime = System.currentTimeMillis();
                if (currentTime > timeKeeper + timer) {
                    timeKeeper = currentTime;
                    JapaneseWord();
                }

            }catch (NullPointerException ex) {
                logger.warn("Some value were null", ex);
            }catch (Exception ex){
                terminate();
            }
        }
    }

    private void JapaneseWord(){

    }


}
