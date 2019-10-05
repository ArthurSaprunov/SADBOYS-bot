package be.skydragonsz.discord.threads;

import be.skydragonsz.discord.Sadboys;
import be.skydragonsz.discord.system.ThreadConstants;
import be.skydragonsz.discord.util.JSONService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class RedditFetcher extends Thread {
    private Logger logger = LoggerFactory.getLogger(RedditFetcher.class);

    private long timer = 60000;
    private long currentTime;
    private long timeKeeper;

    public boolean isRunning;

    private String awwnime = "https://www.reddit.com/r/awwnime/new/.json";
    private String moeScape = "https://www.reddit.com/r/Moescape/new/.json";
    private String apexLegends = "https://www.reddit.com/r/apexlegends/new/.json";


    public RedditFetcher() {
        isRunning = true;
    }

    public void terminate() {
        isRunning = false;
    }

    public void restart(){
        terminate();
        ThreadConstants.reddit = new RedditFetcher();
        ThreadConstants.reddit.start();
    }

    @Override
    public void run() {
        isRunning = true;
        timeKeeper = System.currentTimeMillis();
        logger = LoggerFactory.getLogger(RedditFetcher.class);
        Thread.currentThread().setName(RedditFetcher.class.getSimpleName());
        logger.info("Starting {} Thread!",RedditFetcher.class.getSimpleName());

        while (isRunning) {
            try {
                currentTime = System.currentTimeMillis();
                if (currentTime > timeKeeper + timer) {
                    timeKeeper = currentTime;
                    fetchApexLegends();
                    fetchAnimeImages(this.awwnime);
                    fetchAnimeImages(this.moeScape);
                    checkEmbedStatus();
                }


            } catch (NullPointerException ex) {
                logger.warn("Some value were null", ex);
                restart();
            } catch (Exception ex) {
                logger.warn("Failed to run timekeeper", ex);
                User user = Sadboys.shardManager.getUserById("175957746879823873");
                user.openPrivateChannel().queue((channel) ->  channel.sendMessage("Failed to run Reddit Thread, this was the error: \n" + ex).queue());
                restart();
                //terminate();
            }
        }

    }

    private JSONObject getRedditData(String url) {
        JSONObject wholeJson = JSONService.readJsonFromUrl(url);
        JSONArray tempObj = wholeJson.getJSONObject("data").getJSONArray("children");
        JSONObject obj = tempObj.getJSONObject(0).getJSONObject("data");
        return obj;
    }

    @SuppressWarnings("Duplicates")
    private void fetchAnimeImages(String anime) {
        JSONObject obj = getRedditData(anime);

        Timestamp tempTimer = new Timestamp(60000);
        Timestamp fetched = new Timestamp(0);
        fetched.setTime(Long.parseLong(obj.getLong("created_utc") + "000"));
        fetched.setTime(fetched.getTime() + tempTimer.getTime());
        Timestamp current = new Timestamp(System.currentTimeMillis());

        if (fetched.after(current)) {
            String tempString = obj.getString("url");
            if (obj.getBoolean("over_18")) return;

            if (tempString.endsWith("jpg") || tempString.endsWith("png") || tempString.endsWith("gif")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setImage(tempString).setTitle(obj.getString("title")).setAuthor("Posted by: " + obj.getString("author"), "https://www.reddit.com" + obj.getString("permalink"));
                Sadboys.shardManager.getTextChannelById("594476955446018049").sendMessage(eb.build()).queue();
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void fetchApexLegends() {
        JSONObject obj = getRedditData(this.apexLegends);

        Timestamp tempTimer = new Timestamp(60000);
        Timestamp fetched = new Timestamp(0);
        fetched.setTime(Long.parseLong(obj.getLong("created_utc") + "000"));
        fetched.setTime(fetched.getTime() + tempTimer.getTime());
        Timestamp current = new Timestamp(System.currentTimeMillis());

        JSONArray tempArray = obj.getJSONArray("link_flair_richtext");
        if (tempArray.isEmpty()) return;
        if (tempArray.getJSONObject(0).getString("t").equals("Respawn Official")) {
            if (fetched.after(current)) {
                Sadboys.shardManager.getTextChannelById("594418811755823105").sendMessage("https://www.reddit.com" + obj.getString("permalink")).queue();
            }
        }
    }

    private void checkEmbedStatus() {
        MessageHistory history = Sadboys.shardManager.getTextChannelById("594476955446018049").getHistory();
        history.retrievePast(50).complete();
        for (Message message : history.getRetrievedHistory()) {
            for (MessageEmbed embed : message.getEmbeds()) {
                if (JSONService.getResponseCode(embed.getImage().getUrl()) == 404) {
                    message.delete().queue();
                    return;
                }
                if(embed.getImage().getHeight() == 0){
                    message.delete().queue();
                    return;
                }
            }
        }
    }

}
