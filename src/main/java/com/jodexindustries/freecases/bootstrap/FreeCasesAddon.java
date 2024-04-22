package com.jodexindustries.freecases.bootstrap;

import com.jodexindustries.donatecase.api.addon.JavaAddon;
import com.jodexindustries.freecases.utils.CustomConfig;
import com.jodexindustries.freecases.utils.Utils;
import org.bukkit.plugin.Plugin;

import java.io.File;

public final class FreeCasesAddon extends JavaAddon implements FreeCases {
    private CustomConfig config;

    @Override
    public void onEnable() {
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
    public CustomConfig getAddonConfig() {
        return config;
    }
    @Override
    public Plugin getPlugin() {
        return getDonateCase();
    }
}
