package be.skydragonsz.discord.command;

import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public abstract class Command extends ListenerAdapter {
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
        prefix = SettingsManager.getInstance().getSettings().getPrefix(); //Because I made it so you can change the config and reload it so it needs to trigger on every command send!
        ownerId = SettingsManager.getInstance().getSettings().getOwnerId();
        if (event.getAuthor().isBot() && !respondToBots())
            return;
        if (containsCommand(event.getMessage()))
            onCommand(event, commandArgs(event.getMessage()));
    }

    protected boolean containsCommand(Message message)
    {
        if(!prefix.equals(String.valueOf(commandArgs(message)[0].toLowerCase().charAt(0)))) return false;
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
        if(e.isFromType(ChannelType.PRIVATE))
            return e.getPrivateChannel().sendMessage(message).complete();
        else
            return e.getTextChannel().sendMessage(message).complete();
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
