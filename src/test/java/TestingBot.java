import be.skydragonsz.discord.system.Settings;
import be.skydragonsz.discord.system.SettingsManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class TestingBot {
    public static void main(String[] args){

        Settings settings = SettingsManager.getInstance().getSettings();

        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(settings.getBotToken());

        try {
            JDA api = jdaBuilder.build();
            api.addEventListener(new TestingCommand());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

class TestingCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String content = "Hey friends, &amp;#x200B; Season 2 starts tomorrow! We're planning for the update to go live around 10:00am PST [we'll let you know if that changes]. We'll post update with patch notes once we've confirmed it's live. If you missed the trailers and info we've been dropping over the last few days here's a quick recap: &amp;#x200B; * [How Daily and Weekly Challenges Work with Season 2.](https://www.reddit.com/r/apexlegends/comments/c6msb8/how_daily_and_weekly_challenges_work_with_season/) * [How Ranked Leagues Will Work in Season 2.](https://www.reddit.com/r/apexlegends/comments/c7w3iq/how_ranked_leagues_will_work_in_season_2_of_apex/) * [Season 2 CG Battle Charge Launch Trailer.](https://www.youtube.com/watch?time_continue=1&amp;v=ThSKocfe5QE) * [Season 2 Battle Charge Gameplay Trailer.](https://www.youtube.com/watch?v=zsUd40fvFm8) * [Meet Wattson Trailer.](https://www.youtube.com/watch?v=BuqnLGbjhkY) &amp;#x200B; We also wanted to give you all a heads up before Season 2 starts tomorrow that the update will be a pretty big download. Below are the estimates for the update you'll need to download tomorrow for Season 2: &amp;#x200B; * **X1 and PS4: 20+ GB** * **PC: 18+ GB** &amp;#x200B; **Why is this patch so large?** As we continue to work on Apex, we add content and code to the game. We also find improvements that make existing content fit in less space or get processed faster, which means the way we store the content changes, making it effectively 'new' content. When we ship a patch, usually we deliver this new content in additional files that sit alongside the files we originally shipped with, so updates are as small as possible for our players. However, the cost for this is that outdated content remains alongside the new content, growing over time. New players still have to download it to start playing, and all players must keep it on their disks. To pay down this cost, periodically we make a fresh build of the game, with outdated content removed, so the game is faster for new players to download, more efficient, and takes up less hard drive space for everybody. &amp;#x200B; **Will all future updates huge downloads like this?** The short answer is \\\"no\\\" but there will occasionally be cases in the future where large updates will need to happen. When that's the case we'll let you know. &amp;#x200B; We'll be back tomorrow morning with patch notes and the launch of Season 2!";
        content = content.replaceAll("&amp;#x200B;", "\n\n").replaceAll("\\*\\*", "");
        content = content.replaceAll("\\*","\n");
        int index = content.indexOf("\n",1500);
        String extra = content.substring(index);
        content = content.substring(0,index);
        if(event.getMessage().getContentRaw().equals(".test")){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(content).addField("",extra,false);
            eb.setAuthor("Reddit","https://www.reddit.com/r/apexlegends/comments/c806wk/in_preparation_for_season_2_tomorrow/");
            event.getChannel().sendMessage(eb.build()).queue();
        }
    }
}
