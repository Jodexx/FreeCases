package com.jodexindustries.freecases;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CustomConfig {
    private static File file;
    private static File data;
    private static YamlConfiguration config;
    private static YamlConfiguration dataconfig;

    public CustomConfig() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FreeCases").getDataFolder(), "Config.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FreeCases").getDataFolder(), "Config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        data = new File(Bukkit.getServer().getPluginManager().getPlugin("FreeCases").getDataFolder(), "Data.yml");
        dataconfig = YamlConfiguration.loadConfiguration(data);
    }
    public static FileConfiguration getConfig() {
        return config;
    }
    public static FileConfiguration getData() {
        return dataconfig;
    }
    public static void saveData() {
        try {
            dataconfig.save(data);
        } catch (IOException e) {
            FreeCases.getInstance().getLogger().log(Level.WARNING, "Couldn't save Data.yml");
        }
    }
}
