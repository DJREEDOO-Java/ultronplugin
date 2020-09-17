package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MsgCommand implements CommandExecutor {
    private main plugin;

    public MsgCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("msg").setExecutor(this);
    }

    public static HashMap<String, Player> reply = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            String prefix = main.getInstance().getConfig().getString("config.msg.msgprefix");
            Player p = (Player)sender;
            Player  t = Bukkit.getPlayer(args[0]);
            if(p.hasPermission("ultron.msg")){
                if(args.length > 1){
                    StringBuilder s = new StringBuilder();
                    for (int i = 1; i < args.length; i++)
                        s.append(args[i]).append(" ");
                    reply.put(t.getName() ,p);

                    String msg = s.toString().trim();
                    t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" &6"+ p.getName() + "&e •&6● &8» &2" + t.getName() + "&e •&6● &8» \n" + ChatColor.LIGHT_PURPLE + msg));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+"&6 Deine Nachricht " + ChatColor.LIGHT_PURPLE + s));

                }
            }else
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" &6Dazu hast du keine Rechte! Premission: &cultron.msg &6Fehlt"));

        }
        return false;
    }
}
