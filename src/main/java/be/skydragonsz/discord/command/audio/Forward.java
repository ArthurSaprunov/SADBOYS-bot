package be.skydragonsz.discord.command.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Forward extends MusicCommand {

    public void jump(long position){
        mng.player.getPlayingTrack().setPosition(position*1000);
    }

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        super.onCommand(event);
        long timestamp = Long.parseLong(args[1])*1000; // *1000 Because its calculated in milliseconds and not secconds!
        if(timestamp == 0) return;
        if (args[1].isEmpty()) return;
        mng.player.getPlayingTrack().setPosition(timestamp);
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("Forward", "jump");
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
