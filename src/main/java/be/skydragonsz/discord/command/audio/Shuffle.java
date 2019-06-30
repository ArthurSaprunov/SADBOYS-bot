package be.skydragonsz.discord.command.audio;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Shuffle extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        if (scheduler.queue.isEmpty())
        {
            event.getChannel().sendMessage("The queue is currently empty!").queue();
            return;
        }

        scheduler.shuffle();
        event.getChannel().sendMessage("The queue has been shuffled!").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("shuffle");
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
