package com.jodexindustries.freecases.commands;

import com.jodexindustries.donatecase.api.Case;
import com.jodexindustries.donatecase.api.data.SubCommand;
import com.jodexindustries.donatecase.api.data.SubCommandType;
import com.jodexindustries.freecases.bootstrap.FreeCases;
import com.jodexindustries.freecases.utils.CooldownManager;
import com.jodexindustries.freecases.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements SubCommand {
    private final FreeCases freeCases;
    public MainCommand(FreeCases freeCases) {
        this.freeCases = freeCases;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Don't use console!");
                return;
            }
            Player player = (Player) sender;
            if (sender.hasPermission("freecases.use")) {
                if (!freeCases.getAddonConfig().getDataFile().getStringList("Used").contains(player.getName())) {
                    int time = CooldownManager.getCooldown(player.getUniqueId());
                    String secondChar = freeCases.getAddonConfig().getConfig().getString("Second");
                    String minuteChar = freeCases.getAddonConfig().getConfig().getString("Minute");
                    String hourChar = freeCases.getAddonConfig().getConfig().getString("Hour");
                    int hours = time / 3600;
                    int minutes = (time / 60) - hours * 60;
                    int seconds = time % 60 % 60;
                    String hour = hours + hourChar;
                    String minute = minutes + minuteChar;
                    String second = seconds + secondChar;
                    if (seconds == 0) {
                        second = "";
                    }
                    if (hours == 0) {
                        hour = "";
                        if (minutes == 0) {
                            minute = "";
                        }
                    }
                    String timeRep = hour + minute + second;
                    String caseName = freeCases.getAddonConfig().getConfig().getString("Casename");
                    if (time == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                freeCases.getAddonConfig().getConfig().getString("Done")));
                        Case.addKeys(caseName, sender.getName(), 1);
                        List<String> players = freeCases.getAddonConfig().getDataFile().getStringList("Used");
                        players.add(player.getName());
                        freeCases.getAddonConfig().getDataFile().set("Used", players);
                        freeCases.getAddonConfig().saveData();
                    } else {
                        sender.sendMessage(Utils.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&',
                                freeCases.getAddonConfig().getConfig().getString("Wait").replaceAll("%time%", timeRep))));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            freeCases.getAddonConfig().getConfig().getString("AlreadyReceived")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        freeCases.getAddonConfig().getConfig().getString("PermissionsNeed")));
            }
        } else {
            if(args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("freecases.reload")) {
                    freeCases.getAddonConfig().setup();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', freeCases.getAddonConfig().getConfig().getString("ConfigReloaded")));
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender commandSender, String[] strings) {
        if(strings.length == 1) {
            List<String> list = new ArrayList<>();
            if(commandSender.hasPermission("freecases.reload")) {
                list.add("reload");
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public SubCommandType getType() {
        return SubCommandType.PLAYER;
    }
    @Override
    public String getDescription() {
        return "Get free case";
    }
}
