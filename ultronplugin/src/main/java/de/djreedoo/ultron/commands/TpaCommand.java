package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpaCommand implements CommandExecutor {
    private main plugin;

    public TpaCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpa").setExecutor(this);
    }

    public static HashMap<Player, Player> tpa = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            Player t = Bukkit.getPlayer(args[0]);
            if(p.hasPermission("ultron.tpa")){
                if(args.length == 1){
                    tpa.put(t,p);
                    t.sendMessage(ChatColor.GREEN+p.getName()+" §6möchte sich zu dir teleportieren.\n§6Nimm die anfrage mit §2/tpaccept §6oder §2/tpac an");
                    p.sendMessage("§6Du hast eine tpa anfrage an §2"+t.getName()+"§6 gestellt");
                }else
                    p.sendMessage("§6Bitte verwende §c/tpa <Spieler>");
            }else
                p.sendMessage("§6Dazu hast du keine Rechte! Premission: §cultron.tpa §6Fehlt");

        }
        return false;
    }
}
