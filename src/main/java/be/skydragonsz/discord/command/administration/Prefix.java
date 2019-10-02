package be.skydragonsz.discord.command.administration;


import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Prefix extends Command {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        if (!isOwner(event)) return;
        if (args.length == 1) return;
        SettingsManager.getInstance().getSettings().setPrefix(args[1]);
        SettingsManager.reloadInstance();
        EmbedBuilder eb = new EmbedBuilder().addField("The new prefix is changed to:", args[1], false);
        event.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("prefix", "setprefix");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getUsageInstructions() {
        return null;
    }
}
