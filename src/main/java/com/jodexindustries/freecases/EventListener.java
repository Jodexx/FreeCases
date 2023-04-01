package com.jodexindustries.freecases;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {
    private final Plugin plugin;
    public EventListener(Plugin plugin) {
        this.plugin = plugin;
    }
    Utils utils = new Utils();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
            if (!utils.CheckUsage(player.getName())) {
                CooldownManager cooldownManager = new CooldownManager();
                cooldownManager.setCooldown(player.getUniqueId(), CustomConfig.getConfig().getInt("TimeToPlay"));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
                        cooldownManager.setCooldown(player.getUniqueId(), --timeLeft);
                        if (timeLeft == 0) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 20, 20);
            }
    }
}
