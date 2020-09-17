package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class EventCommand implements CommandExecutor {
    private main plugin;
    public EventCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("event").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p =(Player)sender;
            if(args.length == 0){
                FileConfiguration cfg = main.getInstance().getConfig();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cfg.getString("config.event.msg1"))));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cfg.getString("config.event.msg2"))));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cfg.getString("config.event.msg3"))));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cfg.getString("config.event.msg4"))));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(cfg.getString("config.event.msg5"))));
            }else
                p.sendMessage("§6Bitte verwende §c/event");
        }
        return false;
    }
}
