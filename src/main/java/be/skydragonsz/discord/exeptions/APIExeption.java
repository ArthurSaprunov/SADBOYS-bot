package be.skydragonsz.discord.exeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIExeption extends Exception{
    private static Logger logger = LoggerFactory.getLogger(APIExeption.class);

    public APIExeption(String exception){
        logger.error(exception);
    }
}
