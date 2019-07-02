import net.dv8tion.jda.core.EmbedBuilder;

import java.sql.Timestamp;
import java.sql.Time;

public class Testing {
    public static void main(String[] args){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("test").setDescription("Hey friends, &amp;#x200B; Season 2 starts tomorrow! We're planning for the update to go live around 10:00am PST \\[we'll let you know if that changes\\]. We'll post update with patch notes once we've confirmed it's live. If you missed the trailers and info we've been dropping over the last few days here's a quick recap: &amp;#x200B; * [How Daily and Weekly Challenges Work with Season 2.](https://www.reddit.com/r/apexlegends/comments/c6msb8/how_daily_and_weekly_challenges_work_with_season/) * [How Ranked Leagues Will Work in Season 2.](https://www.reddit.com/r/apexlegends/comments/c7w3iq/how_ranked_leagues_will_work_in_season_2_of_apex/) * [Season 2 CG Battle Charge Launch Trailer.](https://www.youtube.com/watch?time_continue=1&amp;v=ThSKocfe5QE) * [Season 2 Battle Charge Gameplay Trailer.](https://www.youtube.com/watch?v=zsUd40fvFm8) * [Meet Wattson Trailer.](https://www.youtube.com/watch?v=BuqnLGbjhkY) &amp;#x200B; We also wanted to give you all a heads up before Season 2 starts tomorrow that the update will be a pretty big download. Below are the estimates for the update you'll need to download tomorrow for Season 2: &amp;#x200B; * **X1 and PS4: 20+ GB** * **PC: 18+ GB** &amp;#x200B; **Why is this patch so large?** As we continue to work on Apex, we add content and code to the game. We also find improvements that make existing content fit in less space or get processed faster, which means the way we store the content changes, making it effectively 'new' content. When we ship a patch, usually we deliver this new content in additional files that sit alongside the files we originally shipped with, so updates are as small as possible for our players. However, the cost for this is that outdated content remains alongside the new content, growing over time. New players still have to download it to start playing, and all players must keep it on their disks. To pay down this cost, periodically we make a fresh build of the game, with outdated content removed, so the game is faster for new players to download, more efficient, and takes up less hard drive space for everybody. &amp;#x200B; **Will all future updates huge downloads like this?** The short answer is \"no\" but there will occasionally be cases in the future where large updates will need to happen. When that's the case we'll let you know. &amp;#x200B; We'll be back tomorrow morning with patch notes and the launch of Season 2!");
        eb.setAuthor("est");


        Timestamp timestamp = Timestamp.valueOf("2019-06-26T17:38:53.716Z".replace("T", " ").replace("Z",""));
        System.out.println(timestamp.getTime());
    }
}
