package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetSpawnCommand implements CommandExecutor {
    private main plugin;

    public SetSpawnCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setspawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p= (Player)sender;
            if (p.hasPermission("ultron.setspawn")) {
            File file = new File("plugins//ultronplugin//spawn.yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage("§6Datei wurde nicht erstellt");
                }
            }

            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Location loc = p.getLocation();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            double yaw = loc.getYaw();
            double pitch = loc.getPitch();
            String worldname = loc.getWorld().getName();

            cfg.set("X", x);
            cfg.set("Y", y);
            cfg.set("Z", z);
            cfg.set("Yaw", yaw);
            cfg.set("Pitch", pitch);
            cfg.set("Worldname", worldname);

            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage("§6Spawn wurde gesetzt");

            return true;
            } else
                p.sendMessage("§6Dafür hast du keine Rechte");
        }
        return false;
    }
}
