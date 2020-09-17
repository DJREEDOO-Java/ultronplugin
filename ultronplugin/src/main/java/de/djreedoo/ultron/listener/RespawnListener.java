package de.djreedoo.ultron.listener;

import java.io.File;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		
		// Die Spawn Datei
		File file = new File("plugins//ultronplugin//spawn.yml");
		
		// Prüfen ob die Datei nicht existiert
		if(!file.exists()){
			Bukkit.getConsoleSender().sendMessage("§4Dies spawndatei exestiert nicht");
			return;
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
		
		e.setRespawnLocation(loc);
		
	}

}
