package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private main plugin;

    public HealCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("heal").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ultron.heal")){
                if(args.length == 0){
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.sendMessage("§6Du wurdest geheilt");
                }else if(p.hasPermission("ultron.heal.other")){
                    if(args.length == 1){
                    Player t = Bukkit.getPlayer(args[0]);
                    if(t != null){
                        t.setHealth(20);
                        t.setFoodLevel(20);
                        t.sendMessage("§6Du wurdest geheilt");
                    }else
                        t.sendMessage("§6Dieser Spieler ist Offline");
                }else
                    p.sendMessage("§6Bitte verwende §c/heal <Spieler>");
            }else
                p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.heal.other §6Fehlt");
            }else
                p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.heal §6Fehlt");
        }
        return false;
    }
}
