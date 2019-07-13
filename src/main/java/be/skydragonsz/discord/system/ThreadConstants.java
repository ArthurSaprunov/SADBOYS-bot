package be.skydragonsz.discord.system;

import be.skydragonsz.discord.reddit.RedditFetcher;

public class ThreadConstants {
    public static Thread reddit = new Thread(new RedditFetcher());



}
