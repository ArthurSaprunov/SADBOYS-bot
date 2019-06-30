package be.skydragonsz.discord.command.administration;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class ReloadConfig extends Command {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        if(!isOwner(event)) return;
        SettingsManager.reloadInstance();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("reload-config");
    }

    @Override
    public String getDescription() {
        return "Reloads the config file!";
    }

    @Override
    public String getName() {
        return "Reload";
    }

    @Override
    public List<String> getUsageInstructions() {
        return null;
    }
}
