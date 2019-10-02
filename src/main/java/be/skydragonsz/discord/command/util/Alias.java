package be.skydragonsz.discord.command.util;

import be.skydragonsz.discord.command.AliasCommand;
import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.util.JSONService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Alias extends Command {

    private final Path aliasFile = new File(".").toPath().resolve("alias.json");

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        try {
            BufferedReader reader = Files.newBufferedReader(aliasFile, StandardCharsets.UTF_8);

            String tempJson = JSONService.readAll(reader);
            if(tempJson.isEmpty()) tempJson = "{}";

            JSONObject object = new JSONObject(tempJson);
            String command = "";

            JSONObject tempObject = new JSONObject();
            for(int i = 0; i < args.length; i++){
                if(args[i].equalsIgnoreCase("-i")) {
                    if(!args[i+1].isEmpty()){
                        tempObject.put("image",args[i+1]);
                        continue;
                    }
                }
                if(args[i].equalsIgnoreCase("-c")){
                    if(!args[i+1].isEmpty()) {
                        if(object.has(args[i+1])) return;
                        command = args[i+1];
                        continue;
                    }
                }

                if(args[i].equalsIgnoreCase("-e")){
                    if(!args[i+1].isEmpty()) {
                        tempObject.put("echo",args[i+1]);
                        continue;
                    }
                }
            }
            logger.debug(tempObject.toString(2));
            object.put(command,tempObject);



            BufferedWriter writer = null;
            writer = Files.newBufferedWriter(aliasFile, StandardCharsets.UTF_8);
            writer.append(object.toString(2));
            writer.close();

            event.getChannel().sendMessage("Succesfully made a new command!").queue();
            final String tempCommand = command;
            event.getJDA().addEventListener(new Command() {
                @Override
                public void onCommand(MessageReceivedEvent event, String[] args) {
                    String image = tempObject.getString("image");
                    if(image.startsWith("<") || image.endsWith(">")) image = image.replace("<","").replace(">","");
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setImage(image);
                    event.getChannel().sendMessage(embed.build()).queue();
                }

                @Override
                public List<String> getAliases() {
                    return Arrays.asList(tempCommand);
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
            });
        } catch (IOException e) {
            logger.debug("Failed to write to file {}",e);
        }




    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("alias","cc");
    }

    @Override
    public String getDescription() {
        return "Makes a new command!";
    }

    @Override
    public String getName() {
        return "Alias";
    }

    @Override
    public List<String> getUsageInstructions() {
        return null;
    }
}
