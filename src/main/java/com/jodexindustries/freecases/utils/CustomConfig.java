package com.jodexindustries.freecases.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomConfig {
    private File configFile;
    private File dataFile;
    private YamlConfiguration config;
    private YamlConfiguration data;
    private final File dataFolder;
    private final Logger logger;

    public CustomConfig(Logger logger, File dataFolder) {
        this.logger = logger;
        this.configFile = new File(dataFolder, "Config.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.dataFolder = dataFolder;
    }
    public void setup() {
        configFile = new File(dataFolder, "Config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        dataFile = new File(dataFolder, "Data.yml");
        data = YamlConfiguration.loadConfiguration(dataFile);
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public FileConfiguration getDataFile() {
        return data;
    }
    public void saveData() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Couldn't save Data.yml");
        }
    }
}
