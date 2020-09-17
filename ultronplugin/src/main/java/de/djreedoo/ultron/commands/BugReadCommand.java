package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BugReadCommand implements CommandExecutor {
    private main plugin;

    public BugReadCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("bugs").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("ultron.bugs")) {
                if (args.length == 0) {
                    String msg = BugCommand.cfg.getString("bug.msg");
                    p.sendMessage("§6" + msg);
                }
            } else
                p.sendMessage("§6Dazu hast du keine Rechte! Premission: &cultron.bugs &6Fehlt");
        }
        return false;
    }
}
