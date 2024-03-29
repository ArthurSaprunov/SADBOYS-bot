package be.skydragonsz.discord.reddit;

import be.skydragonsz.discord.Sadboys;
import be.skydragonsz.discord.system.Settings;
import be.skydragonsz.discord.system.SettingsManager;
import be.skydragonsz.discord.util.JSONService;
import net.dv8tion.jda.core.EmbedBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

public class AwwAnime extends Thread{
    private Logger logger = LoggerFactory.getLogger(AwwAnime.class);
    private static Settings settings = SettingsManager.getInstance().getSettings();

    private long timer = 60000;
    private long currentTime;
    private long timeKeeper;

    private boolean isRunning;

    private String url = "https://www.reddit.com/r/awwnime/new/.json";
    //private String url = "https://www.reddit.com/new/.json";

    public AwwAnime(){
        isRunning = true;

    }

    public void terminate(){
        isRunning = false;
    }

    @Override
    public void run(){
        timeKeeper = System.currentTimeMillis()-timer;
        logger.info("Starting Thread!");
        while (isRunning){
            try{
                currentTime = System.currentTimeMillis();
                if(currentTime > timeKeeper + timer){
                    timeKeeper = currentTime;
                    fetchAnimeImages();
                }


            }catch (Exception ex){
                logger.warn("Failed to run timekeeper",ex);
                terminate();
            }
        }

    }

    private void fetchAnimeImages(){
        JSONObject wholeJson = JSONService.readJsonFromUrl(this.url);
        JSONArray tempObj = wholeJson.getJSONObject("data").getJSONArray("children");
        JSONObject obj = tempObj.getJSONObject(0).getJSONObject("data");



        Timestamp tempTimer = new Timestamp(65000);
        Timestamp fetched = new Timestamp(0);
        fetched.setTime(Long.parseLong(obj.getLong("created_utc")+"000"));
        fetched.setTime(fetched.getTime()+tempTimer.getTime());
        Timestamp current = new Timestamp(System.currentTimeMillis());

        if(fetched.after(current)) {
            String tempString = obj.getString("url");
            System.out.println(tempString);

            if(tempString.endsWith("jpg")||tempString.endsWith("png")||tempString.endsWith("gif")) {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setImage(tempString).setAuthor("Posted by: " +obj.getString("author"),"https://www.reddit.com"+obj.getString("permalink"));
                Sadboys.getAPI().getGuildById("516615730796691466").getTextChannelById("592740635258257423").sendMessage(eb.build()).queue(); //Testing
                //Sadboys.getAPI().getGuildById("232779792074801152").getTextChannelById("594476955446018049").sendMessage(eb.build()).queue(); //SADBOYS

            }


        }



    }





}
