package com.jodexindustries.freecases.utils;

import com.jodexindustries.freecases.commands.MainCommand;
import com.jodexindustries.freecases.events.EventListener;
import com.jodexindustries.freecases.bootstrap.FreeCases;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;

public class Utils {
    public static FreeCases plugin;
    public static CustomConfig load(FreeCases freeCases) {
        plugin = freeCases;
        CustomConfig config = new CustomConfig(freeCases.getLogger(), freeCases.getDataFolder());
        if (!(new File(freeCases.getDataFolder(), "Data.yml")).exists()) {
            freeCases.saveResource("Data.yml", false);
        }
        if (!(new File(freeCases.getDataFolder(), "Config.yml")).exists()) {
            freeCases.saveResource("Config.yml", false);
        }
        config.setup();
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(freeCases), freeCases.getPlugin());
        if(Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(freeCases).register();
        }
        freeCases.getAPI().getSubCommandManager().registerSubCommand("free", new MainCommand(freeCases));
        return config;
    }
    public static String setPlaceholders(OfflinePlayer player, String text) {
        return plugin.getPlugin().getServer().getPluginManager().getPlugin("PlaceholderAPI") == null ? text : PlaceholderAPI.setPlaceholders(player, text);
    }
}
