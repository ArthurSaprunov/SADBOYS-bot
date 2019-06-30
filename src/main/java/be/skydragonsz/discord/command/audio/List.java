package be.skydragonsz.discord.command.audio;

import be.skydragonsz.discord.audio.MusicManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Queue;

public class List extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        Queue<AudioTrack> queue = scheduler.queue;
        synchronized (queue)
        {
            if (queue.isEmpty())
            {
                event.getChannel().sendMessage("The queue is currently empty!").queue();
            }
            else
            {
                int trackCount = 0;
                long queueLength = 0;
                StringBuilder sb = new StringBuilder();
                sb.append("Current Queue: Entries: ").append(queue.size()).append("\n");
                for (AudioTrack track : queue)
                {
                    queueLength += track.getDuration();
                    if (trackCount < 10)
                    {
                        sb.append("`[").append(MusicManager.getTimestamp(track.getDuration())).append("]` ");
                        sb.append(track.getInfo().title).append("\n");
                        trackCount++;
                    }
                }
                sb.append("\n").append("Total Queue Time Length: ").append(MusicManager.getTimestamp(queueLength));

                event.getChannel().sendMessage(sb.toString()).queue();
            }
        }
    }

    @Override
    public java.util.List<String> getAliases() {
        return Arrays.asList("list","queue");
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
    public java.util.List<String> getUsageInstructions() {
        return null;
    }
}
