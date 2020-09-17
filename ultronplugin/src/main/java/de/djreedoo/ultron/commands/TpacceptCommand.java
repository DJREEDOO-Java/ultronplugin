package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpacceptCommand implements CommandExecutor {
    private main plugin;

    public TpacceptCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpaccept").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ultron.tpaccept")){
                if(args.length ==0){
                    if(TpaCommand.tpa.get(p) != null){
                        TpaCommand.tpa.get(p).teleport(p);

                        p.sendMessage("§6Teleport vorgang...");
                    }else
                        p.sendMessage("§6Du hast derzeit keine anfragen");
                }else
                    p.sendMessage("§6Bitte benutze: §c/tpa <spieler>");
            } else
                p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.tpaccept §6Fehlt");
        }
        return false;
    }
}
