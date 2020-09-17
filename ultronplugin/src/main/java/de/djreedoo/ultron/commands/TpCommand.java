package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {
    private main plugin;

    public TpCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (p.hasPermission("ultron.tp")) {
                    Player t = Bukkit.getPlayer(args[1]);
                    p.teleport(t.getLocation());
                }
            }
        }
        return false;
    }
}
