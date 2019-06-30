package be.skydragonsz.discord.command.audio;


import be.skydragonsz.discord.audio.MusicManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Reset extends MusicCommand {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);

        synchronized (MusicManager.getInstance().musicManagers)
        {
            scheduler.queue.clear();
            player.destroy();
            guild.getAudioManager().setSendingHandler(null);
            MusicManager.getInstance().musicManagers.remove(guild.getId());
        }

        mng = MusicManager.getInstance().getGuildMusicManager(guild);
        guild.getAudioManager().setSendingHandler(mng.sendHandler);
        event.getChannel().sendMessage("The player has been completely reset!").queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("reset");
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
