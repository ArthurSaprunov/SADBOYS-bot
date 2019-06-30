package be.skydragonsz.discord.command.audio;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Pause extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        if (player.getPlayingTrack() == null)
        {
            event.getChannel().sendMessage("Cannot pause or resume player because no track is loaded for playing.").queue();
            return;
        }

        player.setPaused(!player.isPaused());
        if (player.isPaused())
            event.getChannel().sendMessage("The player has been paused.").queue();
        else
            event.getChannel().sendMessage("The player has resumed playing.").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("pause");
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
