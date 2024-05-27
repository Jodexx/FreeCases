package com.jodexindustries.freecases.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.freecases.utils.CustomConfig;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Logger;

public interface FreeCases {
    CustomConfig getAddonConfig();
    Plugin getPlugin();
    CaseManager getAPI();
    Logger getLogger();
    File getDataFolder();
    void saveResource(String resourcePath, boolean replace);
}
