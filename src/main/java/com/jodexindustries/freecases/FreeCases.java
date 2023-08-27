package com.jodexindustries.freecases;

import com.jodexindustries.donatecase.api.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FreeCases extends JavaPlugin {
    static FreeCases plugin;
    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new EventListener(plugin), this);
        if (!(new File(this.getDataFolder(), "Data.yml")).exists()) {
            this.saveResource("Data.yml", false);
        }
        if (!(new File(this.getDataFolder(), "Config.yml")).exists()) {
            this.saveResource("Config.yml", false);
        }
        CustomConfig.setup();
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
        }
        SubCommandManager.registerSubCommand("free", new CommandEX());
    }
    public static FreeCases getInstance() {
        return plugin;
    }
}
