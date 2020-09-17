package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class RucksackCommand implements CommandExecutor, Listener {
    public static File file = new File("plugins/LuckySystem/", "cfg.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private main plugin;

    public RucksackCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("rucksack").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                //normaler rucksack
                Inventory inv = Bukkit.createInventory(null, 27, "§6Rucksack");

                Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        int itemmenge = 0;
                        for (ItemStack i : inv.getContents()) {
                            cfg.set(p.getName() + ".inv." + itemmenge, i);
                            itemmenge++;
                            try {
                                cfg.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, 20, 1);
                int k = 0;
                for (ItemStack i : inv.getContents()) {
                    ItemStack t = cfg.getItemStack(p.getName() + ".inv." + k);
                    inv.setItem(k, t);
                    k++;
                }
                p.openInventory(inv);
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (event.getMessage().equals("/rucksack") && !event.getPlayer().isOp()) {
            boolean w = p.getWorld().getName().equalsIgnoreCase("world");
            if (w = true) {
                event.setCancelled(true);
                String msg = "§6Hier darfst du /rucksack nicht benutzen";
                p.sendMessage(msg);
            }
        }
    }
}
