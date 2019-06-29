import be.skydragonsz.discord.reddit.AwwAnime;

public class TestAwwAnime {
    public static void main(String[] args){
        Thread awwAnimeThread = new Thread(new AwwAnime());
        awwAnimeThread.start();
    }
}
