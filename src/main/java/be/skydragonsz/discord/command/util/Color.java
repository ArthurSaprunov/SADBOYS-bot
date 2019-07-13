package be.skydragonsz.discord.command.util;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.util.ColorConverter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class Color extends Command {
    private Logger logger = LoggerFactory.getLogger(Color.class);

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        try {
            if (args.length == 1) {
                sendMessage(event, "Color needs to be specified!");
                return;
            }
            if (args.length == 2) sendMessage(event, "Color is: " + ColorConverter.convertToDecimal(args[1]));
            if (args.length == 4) {
                int r = Integer.valueOf(args[1]);
                int g = Integer.valueOf(args[2]);
                int b = Integer.valueOf(args[3]);
                sendMessage(event, "Color is: " + ColorConverter.convertToDecimal(new java.awt.Color(r, g, b)));
            }
            return; //If nothing matches
        } catch (Exception ex) {
            logger.error("Failed to get color!");
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("color");
    }

    @Override
    public String getDescription() {
        return "Testing Color from the util!";
    }

    @Override
    public String getName() {
        return "Color tester";
    }

    @Override
    public List<String> getUsageInstructions() {
        return null;
    }
}
