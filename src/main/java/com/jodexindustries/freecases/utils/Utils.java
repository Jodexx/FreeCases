package com.jodexindustries.freecases.utils;

import com.jodexindustries.donatecase.api.SubCommandManager;
import com.jodexindustries.freecases.commands.MainCommand;
import com.jodexindustries.freecases.events.EventListener;
import com.jodexindustries.freecases.bootstrap.FreeCases;
import org.bukkit.Bukkit;

public class Utils {
    public static void load(FreeCases freeCases) {
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(freeCases), freeCases.getPlugin());
        if(Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder(freeCases).register();
        }
        SubCommandManager.registerSubCommand("free", new MainCommand(freeCases));
    }
}
