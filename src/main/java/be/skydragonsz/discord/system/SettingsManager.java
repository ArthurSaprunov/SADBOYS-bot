package be.skydragonsz.discord.system;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsManager {
    private Logger logger = LoggerFactory.getLogger(SettingsManager.class);

    private static SettingsManager instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Settings settings;
    private final Path configFile = new File(".").toPath().resolve("config.json");

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public static void reloadInstance(){
        instance.saveSettings();
        instance = new SettingsManager();
        instance.loadSettings();
    }

    private SettingsManager() {
        if (!configFile.toFile().exists()) {

            logger.info("Creating default settings");
            logger.info("You will need to edit the config.json with your login information.");
            this.settings = getDefaultSettings();
            saveSettings();
            System.exit(0);
        }
        loadSettings();
    }

    public void loadSettings() {
        try {
            checkBadEscapes(configFile);

            BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
            this.settings = gson.fromJson(reader, Settings.class);
            checkOldSettingsFile();
            logger.info("Settings loaded");
        } catch (IOException e) {
            logger.debug("Error Loading Settings",e);
            e.printStackTrace();
        }
    }

    public Settings getSettings() {
        return settings;
    }

    public void saveSettings() {
        String jsonOut = gson.toJson(this.settings);
        try {
            BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8);
            writer.append(jsonOut);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Settings getDefaultSettings() {
        Settings newSettings = new Settings();

        newSettings.setBotToken("");
        newSettings.setOwnerId("");
        newSettings.setGoogleApiKey("");
        newSettings.setPrefix(".");
        newSettings.setUseBetaBuilds(new Boolean(false));

        return newSettings;
    }

    private void checkOldSettingsFile()
    {
        boolean modified = false;
        Settings defaults = getDefaultSettings();
        if (settings.getBotToken() == null)
        {
            settings.setBotToken(defaults.getBotToken());
            modified = true;
        }
        if (settings.getOwnerId() == null)
        {
            settings.setOwnerId(defaults.getOwnerId());
            modified = true;
        }
        if (settings.getGoogleApiKey() == null)
        {
            settings.setGoogleApiKey(defaults.getGoogleApiKey());
            modified = true;
        }

        if (settings.getPrefix() == null)
        {
            settings.setPrefix(defaults.getPrefix());
            modified = true;
        }
        if (settings.getUseBetaBuilds() == null)
        {
            settings.setUseBetaBuilds(defaults.getUseBetaBuilds());
            modified = true;
        }

        if (modified) saveSettings();
    }

    private void checkBadEscapes(Path filePath) throws IOException
    {
        final byte FORWARD_SOLIDUS = 47;    //  /
        final byte BACKWARDS_SOLIDUS = 92;  //  \

        boolean modified = false;
        byte[] bytes = Files.readAllBytes(filePath);
        for (int i = 0; i < bytes.length; i++)
        {
            if (bytes[i] == BACKWARDS_SOLIDUS)
            {
                modified = true;
                bytes[i] = FORWARD_SOLIDUS;
            }
        }

        if (modified)
        {
            Files.write(filePath, bytes);
        }
    }
}
