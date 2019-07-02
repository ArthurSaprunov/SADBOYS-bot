package be.skydragonsz.discord.command;

import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class Command extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Command.class);

    protected String prefix = SettingsManager.getInstance().getSettings().getPrefix();
    protected String ownerId = SettingsManager.getInstance().getSettings().getOwnerId();

    public abstract void onCommand(MessageReceivedEvent event, String[] args);
    public abstract List<String> getAliases();
    public abstract String getDescription();
    public abstract String getName();
    public abstract List<String> getUsageInstructions();

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        try {
            prefix = SettingsManager.getInstance().getSettings().getPrefix(); //Because I made it so you can change the config and reload it so it needs to trigger on every command send!
            ownerId = SettingsManager.getInstance().getSettings().getOwnerId();
            if (event.getMessage().getContentRaw().isEmpty()) return; //For some fucking reason it can return an empty message??? How I don't even know!
            if (event.getAuthor().isBot() && respondToBots()) return;
            if (!prefix.equals(String.valueOf(event.getMessage().getContentRaw().toLowerCase().charAt(0)))) return;
            if (containsCommand(event.getMessage())) onCommand(event, commandArgs(event.getMessage()));
        }catch (StringIndexOutOfBoundsException ex){
            logger.warn("StringIndexOutOfBoundsException (how is this even triggering?)",ex);
        }
    }

    protected boolean containsCommand(Message message)
    {
        return getAliases().contains(commandArgs(message)[0].toLowerCase().substring(1));
    }

    protected String[] commandArgs(Message message)
    {
        return commandArgs(message.getContentDisplay());
    }

    protected String[] commandArgs(String string)
    {
        return string.split("\\s+");
    }

    protected Message sendMessage(MessageReceivedEvent e, Message message)
    {
        return e.getChannel().sendMessage(message).complete();
    }

    protected Message sendMessage(MessageReceivedEvent e, String message)
    {
        return sendMessage(e, new MessageBuilder().append(message).build());
    }

    protected boolean respondToBots()
    {
        return false;
    }

    protected boolean isOwner(MessageReceivedEvent event){
        return event.getAuthor().getId().equals(ownerId);
    }
}
