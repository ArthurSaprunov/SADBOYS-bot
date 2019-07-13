package be.skydragonsz.discord.command.administration;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.reddit.RedditFetcher;
import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class RestartReddit extends Command {
    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        ThreadConstants.reddit.stop();
        ThreadConstants.reddit = new Thread(new RedditFetcher());
        ThreadConstants.reddit.start();

        event.getChannel().sendMessage("Restarting Reddit Thread").queue();

    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("restart-reddit","rr");
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
