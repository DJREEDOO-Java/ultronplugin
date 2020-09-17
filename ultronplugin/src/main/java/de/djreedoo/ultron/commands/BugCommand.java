package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BugCommand implements CommandExecutor {
    private static File file = new File("plugins/ultronplugin/bug.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private main plugin;

    public BugCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("bug").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    s.append(args[i]).append(" ");

                String msg = s.toString().trim();

                p.sendMessage("§6Bug wurde gespeichert vielen dank!");
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.isOp()) {
                        all.sendMessage(ChatColor.GREEN + p.getName() + " §6Hat einen bug gemeldet: §8==> " + ChatColor.LIGHT_PURPLE + msg);
                    }
                }
                cfg.set("bug.msg", p.getName() + " " + msg);
                try {
                    cfg.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
