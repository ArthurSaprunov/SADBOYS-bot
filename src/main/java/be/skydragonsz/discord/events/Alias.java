package be.skydragonsz.discord.events;

import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.util.JSONService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
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
import java.util.*;

public class Alias extends ListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(Alias.class);
    private final Path aliasFile = new File(".").toPath().resolve("alias.json");

    @Override
    public void onReady(ReadyEvent event) {
        loadCommands(event);
    }

    @SuppressWarnings("Duplicates")
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
                if(tempObject.has("text")){
                    final String tempString = tempObject.getString("text");
                    event.getJDA().addEventListener(new Command() {
                        @Override
                        public void onCommand(MessageReceivedEvent event, String[] args) {
                            String text = tempString;
                            List<Member> members = event.getMessage().getMentionedMembers();
                            text = text.replace("%u", event.getAuthor().getName());
                            if(!members.isEmpty()) text = text.replace("%m", members.get(0).getUser().getName());
                            else text = text.replace("%m", event.getAuthor().getName());
                            event.getChannel().sendMessage(text).queue();
                            event.getMessage().delete().queue();
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
                    continue;
                }

                if(tempObject.has("image")) {

                    event.getJDA().addEventListener(new Command() {
                        @Override
                        public void onCommand(MessageReceivedEvent event, String[] args) {
                            if(event.getAuthor().isBot()) return;
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
                    continue;
                }
            }

        } catch (Exception ex) {
            logger.warn("Something went wrong",ex);
        }
    }


    public void reloadCommand(Event event){
        for(int i = 0; i < event.getJDA().getEventManager().getRegisteredListeners().size(); i++){
            if(event.getJDA().getEventManager().getRegisteredListeners().get(i).getClass().toString().contains("Alias$")){ //Its an dupe alias command
                event.getJDA().getEventManager().unregister(event.getJDA().getEventManager().getRegisteredListeners().get(i));
            }
        }
        loadCommands(event);
    }
}
