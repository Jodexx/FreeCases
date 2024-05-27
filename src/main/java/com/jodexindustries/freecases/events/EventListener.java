package com.jodexindustries.freecases.events;

import com.jodexindustries.freecases.bootstrap.FreeCases;
import com.jodexindustries.freecases.utils.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    private final FreeCases freeCases;

    public EventListener(FreeCases freeCases) {
        this.freeCases = freeCases;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
            if (!freeCases.getAddonConfig().getDataFile().getStringList("Used").contains(player.getName()) ||
                    !freeCases.getAddonConfig().getConfig().getBoolean("GetOneTime")) {
                CooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
            }
    }
}
