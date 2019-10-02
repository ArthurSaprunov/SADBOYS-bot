package be.skydragonsz.discord.command;

import be.skydragonsz.discord.util.JSONService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class AliasCommand extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(AliasCommand.class);
    private final Path aliasFile = new File(".").toPath().resolve("alias.json");

    @Override
    public void onReady(ReadyEvent event) {
        loadCommands(event);
    }

    public void loadCommands(Event event){
        try {
            if(!aliasFile.toFile().exists()) {
                BufferedWriter writer = null;
                writer = Files.newBufferedWriter(aliasFile, StandardCharsets.UTF_8);
                writer.close();
            }
            BufferedReader reader = Files.newBufferedReader(aliasFile, StandardCharsets.UTF_8);
            String tempJson = JSONService.readAll(reader);
            if(tempJson.isEmpty()) tempJson = "{}";
            JSONObject object = new JSONObject(tempJson);

            for(String key : object.keySet()){
                JSONObject tempObject = object.getJSONObject(key);
                if(tempObject.has("image")) {

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
                            return Arrays.asList(key);
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
                }
            }

        } catch (Exception ex) {
            logger.warn("Something went wrong",ex);
        }
    }


}
