package be.skydragonsz.discord.command.administration;


import be.skydragonsz.discord.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class StopBotCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        if (!isOwner(event)) return;
        event.getChannel().sendMessage("Shutting down myself!\nGoodbye!").queue();
        System.exit(0);
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("stop-bot");
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
