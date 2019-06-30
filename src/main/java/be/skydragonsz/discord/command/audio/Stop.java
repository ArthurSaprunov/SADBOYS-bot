package be.skydragonsz.discord.command.audio;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Stop extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);
        scheduler.queue.clear();
        player.stopTrack();
        player.setPaused(false);
        event.getChannel().sendMessage("Playback has been completely stopped and the queue has been cleared.").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("stop");
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
