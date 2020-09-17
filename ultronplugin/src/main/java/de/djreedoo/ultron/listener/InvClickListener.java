package de.djreedoo.ultron.listener;

import de.djreedoo.ultron.util.Email;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equalsIgnoreCase("§eMCMail")) {

            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                String mail = event.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1].replaceAll("§c", "");

                Email email = Email.getEmail(p.getUniqueId());

                if(email.removeInboxMessage(mail)) {
                    p.sendMessage("§6Die Nachricht wurde als gelesen makiert");
                    p.closeInventory();
                }

            }
        }
    }

}
