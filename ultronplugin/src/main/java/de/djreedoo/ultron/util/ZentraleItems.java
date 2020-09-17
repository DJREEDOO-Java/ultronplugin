package de.djreedoo.ultron.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ZentraleItems {
    public static void buch(Player p) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8Server-Center");
        ItemStack item = new ItemStack(Material.BOOK);
        ItemStack item2 = new ItemStack(Material.WOODEN_HOE);
        ItemStack item3 = new ItemStack(Material.STONE);
        ItemStack item4 = new ItemStack(Material.DIAMOND_PICKAXE);


        ItemMeta meta = item.getItemMeta();
        ItemMeta meta2 = item.getItemMeta();
        ItemMeta meta3 = item.getItemMeta();
        ItemMeta meta4 = item.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_PURPLE + "Commands");
        meta2.setDisplayName(ChatColor.DARK_PURPLE + "Teleprot zu Farmwelt");
        meta3.setDisplayName(ChatColor.DARK_PURPLE + "Online Spieler");
        meta4.setDisplayName(ChatColor.DARK_PURPLE + "CityBuild 1");

        ArrayList<String> lore = new ArrayList<>();
        ArrayList<String> lore2 = new ArrayList<>();
        ArrayList<String> lore3 = new ArrayList<>();
        ArrayList<String> lore4 = new ArrayList<>();

        lore.add(ChatColor.YELLOW + "Hier siehst du alle verfügbaren commands");
        lore2.add(ChatColor.YELLOW + "Klicke um zu Farmwelt zu gelangen");
        lore3.add(ChatColor.YELLOW + "Klicke um deine spielzeit zu sehen");
        lore4.add(ChatColor.YELLOW + "Klicke um zum CityBuild zu gelangen");

        meta.setLore(lore);
        meta2.setLore(lore2);
        meta3.setLore(lore3);
        meta4.setLore(lore4);

        item.setItemMeta(meta);
        item2.setItemMeta(meta2);
        item3.setItemMeta(meta3);
        item4.setItemMeta(meta4);

        inventory.addItem(item, item2, item3, item4);

        p.openInventory(inventory);
    }
}
