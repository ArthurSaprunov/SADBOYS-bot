package be.skydragonsz.discord.command.audio;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.util.Arrays;
import java.util.List;

public class Join extends MusicCommand {


    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        onCommand(event);
        VoiceChannel chan = null;




            try
            {
                if (args.length == 1) //No channel name was provided to search for.
                {
                    //chan = event.getGuild().getVoiceChannels().stream().filter();
                    chan = event.getMember().getVoiceState().getChannel();
                }else chan = guild.getVoiceChannelById(args[1]);
            }
            catch (NumberFormatException ignored) {}

            if (chan == null)
                chan = guild.getVoiceChannelsByName(args[1], true).stream().findFirst().orElse(null);
            if (chan == null)
            {
                event.getChannel().sendMessage("Could not find VoiceChannel by name: " + args[1]).queue();
            }
            else
            {
                guild.getAudioManager().setSendingHandler(mng.sendHandler);

                try
                {
                    guild.getAudioManager().openAudioConnection(chan);
                }
                catch (PermissionException e)
                {
                    if (e.getPermission() == Permission.VOICE_CONNECT)
                    {
                        event.getChannel().sendMessage(event.getJDA().getSelfUser().getName() + " does not have permission to connect to: " + chan.getName()).queue();
                    }
                }
            }

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("join");
    }

    @Override
    public String getDescription() {
        return "Let the bot join the voice channel!";
    }

    @Override
    public String getName() {
        return "Music bot: join command";
    }

    @Override
    public List<String> getUsageInstructions() {
        return null;
    }
}
