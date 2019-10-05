package be.skydragonsz.discord.command.util;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.events.Alias;
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

public class AliasCommand extends Command {

    private final Path aliasFile = new File(".").toPath().resolve("alias.json");

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args) {
        if(args.length < 2) return; // min is 2 args (list command needs only 2 args)
        try {
            BufferedReader reader = Files.newBufferedReader(aliasFile, StandardCharsets.UTF_8);

            String originalJsonString = JSONService.readAll(reader);
            if(originalJsonString.isEmpty()) originalJsonString = "{}";

            JSONObject object = new JSONObject(originalJsonString);
            String command = "";

            boolean remove = false;
            String removedCommand = "";

            JSONObject tempObject = new JSONObject();
            for(int i = 0; i < args.length; i++){
                if(args[i].equalsIgnoreCase("-r")){
                    if(!args[i+1].isEmpty() && !args[i+1].startsWith("-")) {
                        if(object.has(args[i+1])) object.remove(args[i+1]);
                        remove = true;
                        removedCommand = args[i+1];
                        break;
                    }
                }

                if(args[i].equalsIgnoreCase("-l")){
                    EmbedBuilder embed = new EmbedBuilder();
                    for(String key : object.keySet()){
                        JSONObject temp = object.getJSONObject(key);
                        if(temp.has("description")) embed.addField(key,temp.getString("description"),false);
                        else embed.addField(key,"Has no description",false);
                    }
                    if(embed.isEmpty()) return;
                    event.getChannel().sendMessage(embed.build()).queue();
                    return;

                }

                if(args[i].equalsIgnoreCase("-c")){
                    if(!args[i+1].isEmpty() && !args[i+1].startsWith("-")) {
                        if(object.has(args[i+1])) return;
                        command = args[i+1];
                        continue;
                    }
                }

                if(args[i].equalsIgnoreCase("-i")) {
                    if(!args[i+1].isEmpty() && !args[i+1].startsWith("-")){
                        tempObject.put("image",args[i+1]);
                        continue;
                    }
                }

                if(args[i].equalsIgnoreCase("-d")) {
                    if(!args[i+1].isEmpty() && !args[i+1].startsWith("-")){
                        tempObject.put("description",args[i+1]);
                        continue;
                    }
                }

                if(args[i].equalsIgnoreCase("-t")){
                    if(!args[i+1].isEmpty() && !args[i+1].startsWith("-")) {
                        String text = "";
                        for(int j = i+1; j < args.length; j++){
                            if(args[j].startsWith("\"")){
                                args[j] = args[j].toString().replace("\"","");
                                text += args[j].toString();
                                continue;
                            }
                            if(args[j].endsWith("\"")){
                                args[j] = args[j].toString().replace("\"","");
                                text += " " +args[j].toString();
                                break;
                            }
                            text += " "+ args[j].toString();
                        }
                        tempObject.put("text",text);
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

            if(!remove) event.getChannel().sendMessage("Succesfully made a new command!").queue();
            else event.getChannel().sendMessage("Succesfully deleted " + removedCommand).queue();

            Alias alias = new Alias();
            alias.reloadCommand(event);

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
