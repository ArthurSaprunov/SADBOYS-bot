package be.skydragonsz.discord.command;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class HelpCommand extends Command{

    private static final String NO_NAME = "No name provided for this command. Sorry!";
    private static final String NO_DESCRIPTION = "No description has been provided for this command. Sorry!";
    private static final String NO_USAGE = "No usage instructions have been provided for this command. Sorry!";

    private TreeMap<String, Command> commands;

    public HelpCommand()
        {
            commands = new TreeMap<>();
        }

        public Command registerCommand(Command command)
        {
            commands.put(command.getAliases().get(0), command);
            return command;
        }

        @Override
        public void onCommand(MessageReceivedEvent event, String[] args)
            //TODO Remove this so it also sends it to public servers and not only private messages!
        {   //Why should this even be private, isn't it allowed to put it in the channel?
            if(!event.isFromType(ChannelType.PRIVATE))
            {

            }
            sendPrivate(event.getAuthor().openPrivateChannel().complete(), args);
        }

        @Override
        public List<String> getAliases()
        {
            return Arrays.asList("help", "commands");
        }

        @Override
        public String getDescription()
        {
            return "Command that helps use all other commands!";
        }

        @Override
        public String getName()
        {
            return "Help Command";
        }

        @Override
        public List<String> getUsageInstructions()
        {
            return Collections.singletonList(
                    ".help   **OR**  .help *<command>*\n"
                            + ".help - returns the list of commands along with a simple description of each.\n"
                            + ".help <command> - returns the name, description, aliases and usage information of a command.\n"
                            + "   - This can use the aliases of a command as input as well.\n"
                            + "__Example:__ .help ann");
        }

        private void sendPrivate(PrivateChannel channel, String[] args)
        {
            if (args.length < 2)
            {
                StringBuilder s = new StringBuilder();
                for (Command c : commands.values())
                {
                    String description = c.getDescription();
                    description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;

                    s.append("**").append(c.getAliases().get(0)).append("** - ");
                    s.append(description).append("\n");
                }

                channel.sendMessage(new MessageBuilder()
                        .append("The following commands are supported by the bot\n")
                        .append(s.toString())
                        .build()).queue();
            }
            else
            {
                String command = args[1].charAt(0) == '.' ? args[1] : "." + args[1];    //If there is not a preceding . attached to the command we are search, then prepend one.
                for (Command c : commands.values())
                {
                    if (c.getAliases().contains(command))
                    {
                        String name = c.getName();
                        String description = c.getDescription();
                        List<String> usageInstructions = c.getUsageInstructions();
                        name = (name == null || name.isEmpty()) ? NO_NAME : name;
                        description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                        usageInstructions = (usageInstructions == null || usageInstructions.isEmpty()) ? Collections.singletonList(NO_USAGE) : usageInstructions;

                        //TODO: Replace with a PrivateMessage
                        channel.sendMessage(new MessageBuilder()
                                .append("**Name:** " + name + "\n")
                                .append("**Description:** " + description + "\n")
                                .append("**Alliases:** " + StringUtils.join(c.getAliases(), ", ") + "\n")
                                .append("**Usage:** ")
                                .append(usageInstructions.get(0))
                                .build()).queue();
                        for (int i = 1; i < usageInstructions.size(); i++)
                        {
                            channel.sendMessage(new MessageBuilder()
                                    .append("__" + name + " Usage Cont. (" + (i + 1) + ")__\n")
                                    .append(usageInstructions.get(i))
                                    .build()).queue();
                        }
                        return;
                    }
                }
                channel.sendMessage(new MessageBuilder()
                        .append("The provided command '**" + args[1] + "**' does not exist. Use .help to list all commands.")
                        .build()).queue();
            }
        }
}
