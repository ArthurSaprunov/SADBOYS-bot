package be.skydragonsz.discord.command.audio;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Volume extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        if (args.length == 1)
        {
            event.getChannel().sendMessage("Current player volume: **" + player.getVolume() + "**").queue();
        }
        else
        {
            try
            {
                int newVolume = Math.max(10, Math.min(100, Integer.parseInt(args[1])));
                int oldVolume = player.getVolume();
                player.setVolume(newVolume);
                event.getChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
            }
            catch (NumberFormatException e)
            {
                event.getChannel().sendMessage("`" + args[1] + "` is not a valid integer. (10 - 100)").queue();
            }
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("volume");
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
