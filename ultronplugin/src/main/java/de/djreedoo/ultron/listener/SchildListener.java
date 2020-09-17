package de.djreedoo.ultron.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

public class SchildListener implements Listener {
    @EventHandler
    public void farmwelt(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[Farmwelt]")) {
            e.setLine(0, "§2Farmwelt");
            e.setLine(1, "§6Teleportiere dich");
            e.setLine(2, "§bViel spaß ");
        }
    }

    @EventHandler
    public void nether(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[Nether]")) {
            e.setLine(0, "§5Nether");
            e.setLine(1, "§6Teleportiere dich");
            e.setLine(2, "§bViel spaß ");
        }
    }

    @EventHandler
    public void end(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[End]")) {
            e.setLine(0, "§8End");
            e.setLine(1, "§6Teleportiere dich");
            e.setLine(2, "§bViel spaß ");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§2Farmwelt")) {
                File file = new File("plugins//ultronplugin//farmwelt.yml");
                if (!file.exists()) {
                    Bukkit.getConsoleSender().sendMessage("§4farmwelt.yml exestiert nicht");
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
                p.sendMessage("§6Teleportiere zur §2Farmwelt");
            }
        }

        //Nether methode
        if (!(e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§5Nether")) {
                File file = new File("plugins//ultronplugin//nether.yml");
                if (!file.exists()) {
                    Bukkit.getConsoleSender().sendMessage("§4nether.yml exestiert nicht");
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
                p.sendMessage("§6Teleportiere zum §5Nether");
            }
        }

        //end methode
        if (!(e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§8End")) {
                File file = new File("plugins//ultronplugin//end.yml");
                if (!file.exists()) {
                    Bukkit.getConsoleSender().sendMessage("§4end.yml exestiert nicht");
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
                p.sendMessage("§6Teleportiere zum §8End");
            }
        }
    }
}
