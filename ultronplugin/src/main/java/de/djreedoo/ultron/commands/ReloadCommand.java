package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private main plugin;
    public ReloadCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("ultron").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("ultron.reload")) {
                main.getInstance().reloadConfig();
                sender.sendMessage("§6Config wurde aktualisiert");
            } else {
                sender.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.reload §6Fehlt");
                return true;

            }
        } else {
            sender.sendMessage("§6Bitte verwende §c/ultron reload");
        }
        return true;
    }

}
