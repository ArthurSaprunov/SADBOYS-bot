package be.skydragonsz.discord.command.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Restart extends MusicCommand{
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);
        AudioTrack track = player.getPlayingTrack();
        if (track == null)
            track = scheduler.lastTrack;

        if (track != null)
        {
            event.getChannel().sendMessage("Restarting track: " + track.getInfo().title).queue();
            player.playTrack(track.makeClone());
        }
        else
        {
            event.getChannel().sendMessage("No track has been previously started, so the player cannot replay a track!").queue();
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("restart");
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
