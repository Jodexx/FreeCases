package com.jodexindustries.freecases.utils;

import com.jodexindustries.freecases.bootstrap.FreeCases;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class Placeholder extends PlaceholderExpansion {
    private final FreeCases freeCases;
    public Placeholder(FreeCases freeCases) {
        this.freeCases = freeCases;
    }


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
        return "1.0.5";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("time")) {
            long timeStamp = (CooldownManager.getCooldown(player.getUniqueId()) + (freeCases.getAddonConfig().getConfig().getLong("TimeToPlay") * 1000L) ) - System.currentTimeMillis();
            long time = Duration.ofMillis(timeStamp).toSeconds();
            String result = Utils.formatTime(time);
            if(time > 0) {
                return result;
            } else {
                return ChatColor.translateAlternateColorCodes('&', freeCases.getAddonConfig().getConfig().getString("Received"));
            }
        }

        return null;
    }
}
