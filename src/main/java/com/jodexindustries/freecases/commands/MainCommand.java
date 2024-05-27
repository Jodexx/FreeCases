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

import java.time.Duration;
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
                    long timeStamp = (CooldownManager.getCooldown(player.getUniqueId()) + (freeCases.getAddonConfig().getConfig().getLong("TimeToPlay") * 1000L) ) - System.currentTimeMillis();
                    long time = Duration.ofMillis(timeStamp).toSeconds();
                    String caseName = freeCases.getAddonConfig().getConfig().getString("Casename");
                    if (time <= 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                freeCases.getAddonConfig().getConfig().getString("Done")));
                        Case.addKeys(caseName, sender.getName(), 1);
                        List<String> players = freeCases.getAddonConfig().getDataFile().getStringList("Used");
                        players.add(player.getName());
                        if(freeCases.getAddonConfig().getConfig().getBoolean("GetOneTime")) {
                            freeCases.getAddonConfig().getDataFile().set("Used", players);
                            freeCases.getAddonConfig().saveData();
                        } else {
                            CooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());
                        }
                    } else {
                        sender.sendMessage(Utils.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&',
                                freeCases.getAddonConfig().getConfig().getString("Wait").replaceAll("%time%", Utils.formatTime(time)))));
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
