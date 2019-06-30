package be.skydragonsz.discord.command;

import be.skydragonsz.discord.command.administration.*;
import be.skydragonsz.discord.command.audio.*;
import be.skydragonsz.discord.exeptions.APIExeption;
import net.dv8tion.jda.core.JDA;

public class Register {
    public static JDA api;
    private static HelpCommand help = new HelpCommand();

    public static void resiterBasicCommands(){
        api.addEventListener(help);
        api.addEventListener(help.registerCommand(new Prefix()));
        api.addEventListener(help.registerCommand(new StopBot()));
    }

    public static void googleAPI() throws APIExeption{
        if(api == null) throw new APIExeption("API is empty!");
        api.addEventListener();
    }

    public static void funCommands() throws APIExeption{
        if(api == null) throw new APIExeption("API is empty!");
        api.addEventListener();
    }

    public static void setupMusic() throws APIExeption {
        if(api == null) throw new APIExeption("API is empty!");
        api.addEventListener(help.registerCommand(new Join()));
        api.addEventListener(help.registerCommand(new Leave()));
        api.addEventListener(help.registerCommand(new Play()));
        api.addEventListener(help.registerCommand(new Stop()));
        api.addEventListener(help.registerCommand(new Pause()));
        api.addEventListener(help.registerCommand(new Skip()));
        api.addEventListener(help.registerCommand(new Volume()));
        api.addEventListener(help.registerCommand(new List()));
        api.addEventListener(help.registerCommand(new NowPlaying()));
        api.addEventListener(help.registerCommand(new Repeat()));
        api.addEventListener(help.registerCommand(new Reset()));
        api.addEventListener(help.registerCommand(new Restart()));
        api.addEventListener(help.registerCommand(new Shuffle()));
        api.addEventListener(help.registerCommand(new Forward()));
    }
}
