package com.jodexindustries.freecases;

import com.jodexindustries.donatecase.api.Case;
import com.jodexindustries.donatecase.api.SubCommand;
import com.jodexindustries.donatecase.api.SubCommandType;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandEX implements SubCommand {
    CooldownManager cooldownManager = new CooldownManager();
    Utils utils = new Utils();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Don't use console!");
                return;
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
                        Case.addKeys(casename, sender.getName(), 1);
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
    }

    @Override
    public List<String> getTabCompletions(CommandSender commandSender, String[] strings) {
        return new ArrayList<>();
    }

    @Override
    public SubCommandType getType() {
        return SubCommandType.PLAYER;
    }
}
