package be.skydragonsz.discord.command;

import be.skydragonsz.discord.command.administration.Prefix;
import be.skydragonsz.discord.command.administration.RestartReddit;
import be.skydragonsz.discord.command.administration.StopBot;
import be.skydragonsz.discord.command.util.Color;
import be.skydragonsz.discord.exeptions.APIExeption;
import net.dv8tion.jda.core.JDA;

public class Register {
    public static JDA api;
    private static HelpCommand help = new HelpCommand();

    public static void resiterBasicCommands() {
        api.addEventListener(help);
        api.addEventListener(help.registerCommand(new Prefix()));
        api.addEventListener(help.registerCommand(new StopBot()));
        api.addEventListener(help.registerCommand(new RestartReddit()));
    }

    public static void googleAPI() throws APIExeption {
        if (api == null) throw new APIExeption("API is empty!");
        api.addEventListener();
    }

    public static void funCommands() throws APIExeption {
        if (api == null) throw new APIExeption("API is empty!");
        api.addEventListener(new Color());
    }

}
