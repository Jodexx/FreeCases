package com.jodexindustries.freecases;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {
    CooldownManager cooldownManager = new CooldownManager();


    @Override
    public @NotNull String getAuthor() {
        return "_Jodex__";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "FreeCases";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.3";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("time")) {
            String secondchar = ChatColor.translateAlternateColorCodes('&', CustomConfig.getConfig().getString("Second"));
            String minutechar = ChatColor.translateAlternateColorCodes('&', CustomConfig.getConfig().getString("Minute"));
            String hourchar = ChatColor.translateAlternateColorCodes('&', CustomConfig.getConfig().getString("Hour"));
            int time = cooldownManager.getCooldown(player.getUniqueId());
            int hours = time / 3600;
            int minutes = (time / 60) - hours * 60;
            int seconds = time % 60 % 60;
            String hour = hours + hourchar;
            String minute = minutes + minutechar;
            String second = seconds + secondchar;
            if (seconds == 0) {
                second = "";
            }
            if (hours == 0) {
                hour = "";
                if (minutes == 0) {
                    minute = "";
                }
            }
            String timerep = hour + minute + second;
            if(time != 0) {
                return timerep;
            } else {
                return ChatColor.translateAlternateColorCodes('&', CustomConfig.getConfig().getString("Received"));
            }
        }

        return null;
    }
}
