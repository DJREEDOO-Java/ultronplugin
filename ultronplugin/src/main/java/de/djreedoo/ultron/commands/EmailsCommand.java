package de.djreedoo.ultron.commands;


import de.djreedoo.ultron.main.main;
import de.djreedoo.ultron.util.Email;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EmailsCommand implements CommandExecutor {
    private main plugin;

    public EmailsCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("emails").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            Email email = Email.getEmail(p.getUniqueId());
            if(email == null) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten");
                return true;
            }
            Inventory inventory = Bukkit.createInventory(null, 9*6, "§eMCMail");
            email.getInbox().forEach((key, val) -> {
                ItemStack itemStack = new ItemStack(Material.BOOK);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§7von §c"+val);

                ArrayList<String> lore = new ArrayList<>();
                lore.add("§8-----------");
                lore.add("§7Naricht:");
                String message = "§e";
                int len = 0;
                String[] spl = key.split(" ");

                for(int i = 0; i < spl.length; i++) {
                    len++;
                    if(len == 5) {
                        lore.add(message);
                        message = "§e";
                        len = 0;
                    }
                    message+=spl[i]+ " ";
                }

                lore.add(message);

                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            });

            p.openInventory(inventory);

        }
        return false;
    }
}
