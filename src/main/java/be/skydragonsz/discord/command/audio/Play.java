package be.skydragonsz.discord.command.audio;


import be.skydragonsz.discord.audio.MusicManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;


public class Play extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);
        if (args.length == 1) //It is only the command to start playback (probably after pause)
        {
            if (player.isPaused())
            {
                player.setPaused(false);
                event.getChannel().sendMessage("Playback as been resumed.").queue();
            }
            else if (player.getPlayingTrack() != null)
            {
                event.getChannel().sendMessage("Player is already playing!").queue();
            }
            else if (scheduler.queue.isEmpty())
            {
                event.getChannel().sendMessage("The current audio queue is empty! Add something to the queue first!").queue();
            }
        }
        else    //Commands has 2 parts, .play and url.
        {
            MusicManager.getInstance().loadAndPlay(mng, event.getChannel(), args[1], false);
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("play");
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
