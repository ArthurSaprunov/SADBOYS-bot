package be.skydragonsz.discord.command.administration;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class RestartReddit extends Command {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        if (!isOwner(event)) return;
        ThreadConstants.reddit.restart();
        event.getChannel().sendMessage("Restarting Reddit Thread").queue();
        logger.info("Restarting Reddit Thread!");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("restart-threads","rr");
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
