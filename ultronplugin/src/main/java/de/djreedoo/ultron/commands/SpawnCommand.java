package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SpawnCommand implements CommandExecutor {
    private main plugin;
    public SpawnCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
        Player p =(Player) sender;

        File file = new File("plugins//ultronplugin//spawn.yml");
        if(!file.exists()){
            Bukkit.getConsoleSender().sendMessage("§4Spawn.yml exestiert nicht");
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Location loc = p.getLocation();
        double x = cfg.getDouble("X");
        double y = cfg.getDouble("Y");
        double z = cfg.getDouble("Z");
        double yaw = cfg.getDouble("Yaw");
        double pitch = cfg.getDouble("Pitch");
        String worldname = cfg.getString("Worldname");

        World welt = Bukkit.getWorld(worldname);

        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        loc.setWorld(welt);

        p.teleport(loc);
        p.sendMessage("§6Teleportiere zu Spawn");

        return true;
        }
        return  false;
    }
}
