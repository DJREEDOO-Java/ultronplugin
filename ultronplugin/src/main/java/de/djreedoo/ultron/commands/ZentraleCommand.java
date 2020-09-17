package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import de.djreedoo.ultron.util.ZentraleItems;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.IOException;

public class ZentraleCommand implements CommandExecutor, Listener {
    private main plugin;

    public ZentraleCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("zentrale").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                ZentraleItems.buch(p);
            } else if (args[0].equalsIgnoreCase("setfarmwelt")) {
                if (p.hasPermission("ultron.setfarmwelt")) {
                    File file = new File("plugins//ultronplugin//farmwelt.yml");
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
                    p.sendMessage("§6Farmwelt spawn wurde gesetzt");
                } else
                    p.sendMessage("no perms");
            } else if (args[0].equalsIgnoreCase("setnether")) {
                if (p.hasPermission("ultron.setnether")) {
                    //Methode
                    File file = new File("plugins//ultronplugin//nether.yml");
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
                    p.sendMessage("§6Spawn-nether wurde gesetzt");
                } else
                    p.sendMessage("no perms");
            } else if (args[0].equalsIgnoreCase("setend")) {
                if (p.hasPermission("ultron.setend")) {
                    //methode
                    File file = new File("plugins//ultronplugin//end.yml");
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
                    p.sendMessage("§6Spawn-end wurde gesetzt");
                } else
                    p.sendMessage("no perms");
            } else if (args[0].equalsIgnoreCase("setcb")){
                if(p.hasPermission("ultron.setcb")){
                    File file = new File("plugins//ultronplugin//citybuild.yml");
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
                    p.sendMessage("§6CityBuild-Spawn wurde gesetzt");
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase("§8Server-Center")) {

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getType() == Material.BOOK) {
                    event.setCancelled(true);
                    p.closeInventory();
                    ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bm = (BookMeta) writtenBook.getItemMeta();
                    bm.setTitle("Commands");
                    bm.setAuthor("DJREEDOO");
                    bm.setPages("§1/zentrale\n§2/msg /r oder /reply\n§3/discord\n§4/emails / /emailsend\n§5/rucksack\n " +
                            "§6/sbtoggle\n§7/tpa / tpaccept\n§8/werkbank\n\n§5Owner Commands\n§1/gm 0,1,2,3\n§2/heal\n§3/ultron reload\n" +
                            "§4/reload §5uvm...", "§8Dieses Plugin wird von §2DJREEDOO §8programmiert das heist das es noch nicht fertig ist falls fehler passieren bitte benutzt /bug um es zu reporten Danke\n" +
                            "es wurde erst kürzlich dazu programmiert");
                    writtenBook.setItemMeta(bm);
                    p.openBook(writtenBook);
                }
            }
        }

        if (event.getView().getTitle().equalsIgnoreCase("§8Server-Center")) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getType() == Material.WOODEN_HOE) {
                    event.setCancelled(true);
                    p.closeInventory();
                    File file = new File("plugins//ultronplugin//farmwelt.yml");
                    if (!file.exists()) {
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
                    p.sendMessage("§6Teleportiere zur Farmwelt");
                }
            }
        }

        if (event.getView().getTitle().equalsIgnoreCase("§8Server-Center")) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getType() == Material.STONE) {
                    p.closeInventory();
                    p.sendMessage("§6In Entwicklung");
                }
            }
        }
        if (event.getView().getTitle().equalsIgnoreCase("§8Server-Center")) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
                    event.setCancelled(true);
                    p.closeInventory();
                    File file = new File("plugins//ultronplugin//citybuild.yml");
                    if (!file.exists()) {
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
                    p.sendMessage("§6Teleportiere zum CityBuild");
                }
            }
        }
    }
}
