package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    private main plugin;
    public DiscordCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("discord").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            String prefix = main.getInstance().getConfig().getString("config.discord.prefix");
            String link = main.getInstance().getConfig().getString("config.discord.link");

            Player p =(Player)sender;
            if(args.length == 0){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" &6Discord Link: "+link));
            }else
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" §6Bitte verwende §c/discord"));
        }
        return false;
    }
}
