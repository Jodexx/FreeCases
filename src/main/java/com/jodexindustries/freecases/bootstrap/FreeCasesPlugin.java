package com.jodexindustries.freecases.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.donatecase.api.SubCommandManager;
import com.jodexindustries.freecases.utils.CustomConfig;
import com.jodexindustries.freecases.utils.Utils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FreeCasesPlugin extends JavaPlugin implements FreeCases {
    private CustomConfig config;
    private CaseManager api;

    @Override
    public void onEnable() {
        api = new CaseManager(this);
        config = new CustomConfig(getLogger(), getDataFolder());
        if (!(new File(this.getDataFolder(), "Data.yml")).exists()) {
            saveResource("Data.yml", false);
        }
        if (!(new File(this.getDataFolder(), "Config.yml")).exists()) {
            saveResource("Config.yml", false);
        }
        config.setup();
        Utils.load(this);
    }
    @Override
    public void onDisable() {
        api.getSubCommandManager().unregisterSubCommand("free");
    }

    @Override
    public CustomConfig getAddonConfig() {
        return config;
    }


    @Override
    public Plugin getPlugin() {
        return this;
    }

    @Override
    public CaseManager getAPI() {
        return api;
    }
}
