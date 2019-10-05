package be.skydragonsz.discord.command.util;

import be.skydragonsz.discord.Sadboys;
import be.skydragonsz.discord.command.Command;
import be.skydragonsz.discord.system.ThreadConstants;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class EvalCommand extends Command {
    private ScriptEngine engine;

    public EvalCommand()
    {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try
        {
            engine.eval("var imports = new JavaImporter(" +
                    "java.io," +
                    "java.lang," +
                    "java.util," +
                    "Packages.net.dv8tion.jda.api," +
                    "Packages.net.dv8tion.jda.api.entities," +
                    "Packages.net.dv8tion.jda.api.entities.impl," +
                    "Packages.net.dv8tion.jda.api.managers," +
                    "Packages.net.dv8tion.jda.api.managers.impl," +
                    "Packages.net.dv8tion.jda.api.utils);");
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args)
    {
        if(!isOwner(event)) return;

        try
        {
            engine.put("reddit", ThreadConstants.reddit);
            engine.put("event", event);
            engine.put("message", event.getMessage());
            engine.put("channel", event.getChannel());
            engine.put("args", args);
            engine.put("shard", Sadboys.shardManager);
            engine.put("api", event.getJDA());
            if (event.isFromType(ChannelType.TEXT))
            {
                engine.put("guild", event.getGuild());
                engine.put("member", event.getMember());
            }

            Object out = engine.eval(
                    "(function() {" +
                            "with (imports) { " +
                            event.getMessage().getContentDisplay().substring(args[0].length()) +
                            "}" +
                            "})();");

            sendMessage(event, out == null ? "Executed without error." : out.toString());

        }
        catch (Exception ex)
        {
            sendMessage(event, ex.getMessage());
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("eval");
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
