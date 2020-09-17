package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    private main plugin;
    public GamemodeCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("gm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            String gm0 = main.getInstance().getConfig().getString("config.gm.gm0msg");
            String gm1 = main.getInstance().getConfig().getString("config.gm.gm1msg");
            String gm2 = main.getInstance().getConfig().getString("config.gm.gm2msg");
            String gm3 = main.getInstance().getConfig().getString("config.gm.gm3msg");
            Player p =(Player)sender;
            Player t =Bukkit.getPlayer(args[1]);
            if(args[0].equalsIgnoreCase("0")){
                if(p.hasPermission("ultron.gm.0")){
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', gm0));
                    if(p.hasPermission("ultron.gm.other")){
                        t.setGameMode(GameMode.SURVIVAL);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', gm0));
                    }else
                        p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.other §6Fehlt");
                }else
                    p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.gm.0 §6Fehlt");
            }

            if(args[0].equalsIgnoreCase("1")){
                if(p.hasPermission("ultron.gm.1")){
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', gm1));
                    if(p.hasPermission("ultron.gm.other")){
                        t.setGameMode(GameMode.CREATIVE);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', gm1));
                    }else
                        p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.other §6Fehlt");
                }else
                    p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.gm.1 §6Fehlt");
            }

            if(args[0].equalsIgnoreCase("2")){
                if(p.hasPermission("ultron.gm.2")){
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', gm2));
                    if(p.hasPermission("ultron.gm.other")){
                        t.setGameMode(GameMode.SPECTATOR);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', gm2));
                    }else
                        p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.other §6Fehlt");
                }else
                    p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.gm.2 §6Fehlt");
            }

            if(args[0].equalsIgnoreCase("3")){
                if(p.hasPermission("ultron.gm.3")){
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', gm3));
                    if(p.hasPermission("ultron.gm.other")){
                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', gm3));
                    }else
                        p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.other §6Fehlt");
                }else
                    p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.gm.3 §6Fehlt");
            }
        }
        return false;
    }
}