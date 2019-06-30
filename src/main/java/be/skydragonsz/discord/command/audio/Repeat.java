package be.skydragonsz.discord.command.audio;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Repeat extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        scheduler.setRepeating(!scheduler.isRepeating());
        event.getChannel().sendMessage("Player was set to: **" + (scheduler.isRepeating() ? "repeat" : "not repeat") + "**").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("repeat");
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
