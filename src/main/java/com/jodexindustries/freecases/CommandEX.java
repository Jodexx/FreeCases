package com.jodexindustries.freecases;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandEX implements CommandExecutor {
    CooldownManager cooldownManager = new CooldownManager();
    Utils utils = new Utils();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        if(args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Don't use console!");
                return false;
            }
            Player player = (Player) sender;
            if (sender.hasPermission("freecases.use")) {
                if (!utils.CheckUsage(player.getName())) {
                    int time = cooldownManager.getCooldown(player.getUniqueId());
                    String secondchar = CustomConfig.getConfig().getString("Second");
                    String minutechar = CustomConfig.getConfig().getString("Minute");
                    String hourchar = CustomConfig.getConfig().getString("Hour");
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
                    String casename = CustomConfig.getConfig().getString("Casename");
                    if (time == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                CustomConfig.getConfig().getString("Done")));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "donatecase givekey " + sender.getName() + " "  + casename + " 1");
                        List<String> players = CustomConfig.getData().getStringList("Used");
                        players.add(player.getName());
                        CustomConfig.getData().set("Used", players);
                        CustomConfig.saveData();
                    } else {
                        sender.sendMessage(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&',
                                CustomConfig.getConfig().getString("Wait").replaceAll("%time%", timerep))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            CustomConfig.getConfig().getString("AlreadyReceived")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        CustomConfig.getConfig().getString("PermissionsNeed")));
            }
        } else {
            if(args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("freecases.reload")) {
                    CustomConfig.setup();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.getConfig().getString("ConfigReloaded")));
                }
            }
        }
        return false;
    }
}
