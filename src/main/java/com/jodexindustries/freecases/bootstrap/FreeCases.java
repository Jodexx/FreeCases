package com.jodexindustries.freecases.bootstrap;

import com.jodexindustries.freecases.utils.CustomConfig;
import org.bukkit.plugin.Plugin;

public interface FreeCases {
    CustomConfig getAddonConfig();
    Plugin getPlugin();
}
