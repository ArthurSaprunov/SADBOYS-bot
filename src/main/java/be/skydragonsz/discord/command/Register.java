package be.skydragonsz.discord.command;

import be.skydragonsz.discord.exeptions.APIExeption;
import net.dv8tion.jda.core.JDA;

public class Register {
    public static JDA api;

    public static void googleAPI() throws APIExeption{
        if(api == null) throw new APIExeption("API is empty!");
        api.addEventListener();
    }

    public static void funCommands() throws APIExeption{
        if(api == null) throw new APIExeption("API is empty!");
        api.addEventListener();
    }
}
