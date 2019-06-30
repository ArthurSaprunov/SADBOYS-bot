package be.skydragonsz.discord.command.audio;

import be.skydragonsz.discord.audio.GuildMusicManager;
import be.skydragonsz.discord.audio.MusicManager;
import be.skydragonsz.discord.audio.TrackScheduler;
import be.skydragonsz.discord.command.Command;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public abstract class MusicCommand extends Command {
    protected Guild guild;
    protected GuildMusicManager mng;
    protected AudioPlayer player;
    protected TrackScheduler scheduler;

    protected void onCommand(MessageReceivedEvent event){
        this.guild = event.getGuild();
        this.mng = MusicManager.getInstance().getGuildMusicManager(guild);
        this.player = mng.player;
        this.scheduler = mng.scheduler;

    }

}
