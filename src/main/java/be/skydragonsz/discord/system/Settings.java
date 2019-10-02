package be.skydragonsz.discord.system;

public class Settings {
    private String ownerId;
    private String botToken;
    private String googleApiKey;
    private Boolean useBetaBuilds;
    private String prefix;

    public String consoleChannel = "";

    public String dbDb = "saboys";
    public String dbHost = "localhost";
    public String dbPassword;
    public int dbPort = 28015;
    public String dbUser;
    public String dbotsToken;
    public String dbotsorgToken;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getGoogleApiKey() {
        return googleApiKey;
    }

    public void setGoogleApiKey(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

    public Boolean getUseBetaBuilds() {
        return useBetaBuilds;
    }

    public void setUseBetaBuilds(boolean useBetaBuilds) {
        this.useBetaBuilds = useBetaBuilds;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


}
