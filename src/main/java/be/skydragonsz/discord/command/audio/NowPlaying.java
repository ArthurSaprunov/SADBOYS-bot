package be.skydragonsz.discord.command.audio;


import be.skydragonsz.discord.audio.MusicManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class NowPlaying extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        AudioTrack currentTrack = player.getPlayingTrack();
        if (currentTrack != null)
        {
            String title = currentTrack.getInfo().title;
            String position = MusicManager.getTimestamp(currentTrack.getPosition());
            String duration = MusicManager.getTimestamp(currentTrack.getDuration());

            String nowplaying = String.format("**Playing:** %s\n**Time:** [%s / %s]",
                    title, position, duration);

            event.getChannel().sendMessage(nowplaying).queue();
        }
        else
            event.getChannel().sendMessage("The player is not currently playing anything!").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("np","nowplaying");
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
