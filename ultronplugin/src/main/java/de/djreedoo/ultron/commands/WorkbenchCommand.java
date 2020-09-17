package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {
    private main plugin;

    public WorkbenchCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("werkbank").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.hasPermission("ultron.werkbank")) {
                    p.openWorkbench(null, true);
                }else
                    p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.werkbank §6Fehlt");
            }
        }
        return false;
    }
}
