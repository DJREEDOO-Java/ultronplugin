package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private main plugin;

    public FlyCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }
    //Danke keybax
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ultron.fly")){
                if(args.length == 0){
                    if(p.getAllowFlight()){//vermute er chekt ob spieler fliegt
                        p.setAllowFlight(false);
                        p.sendMessage("§6Fliegen wurde deaktiviert");//kommt in message.yml
                        return true;
                    }
                    p.setAllowFlight(true);
                    p.sendMessage("§6Fliegen wurde aktiviert");//kommt in message.yml
                    return true;
                } else if(args.length == 1){
                    final Player t = Bukkit.getPlayer(args[0]);
                    if(p.hasPermission("ultron.fly.others")){
                        if(t != null){//chekt ob target offline ist oder nicht
                            if(t != p){
                                if(t.getAllowFlight()){
                                    t.setAllowFlight(false);
                                    t.sendMessage("§6fliegen wurde deaktiviert");
                                    p.sendMessage("§6fliegen wurde für §a"+t.getName()+" §6deaktiviert");
                                    return true;
                                }
                                t.setAllowFlight(true);
                                t.sendMessage("§6Fliegen wurde aktiviert");
                                p.sendMessage("§6Fliegen wurde für  §a"+t.getName()+" §6aktiviert");
                            }else{
                                p.sendMessage("§cERROR");
                            }
                        }else{
                            p.sendMessage("§6Dieser Spieler ist Offline");
                        }
                    }else{
                        p.sendMessage("&6Dazu hast du keine Rechte! Premission: &cultron.fly.others &6Fehlt");
                    }
                }else {
                    p.sendMessage("§6Bitte verwende /fly <SpielerName>");
                }
            }else{
                p.sendMessage("&6Dazu hast du keine Rechte! Premission: &cultron.fly &6Fehlt");
            }
        }
        return false;
    }
}
