package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MsgReplyCommand implements CommandExecutor {
    private main plugin;

    public MsgReplyCommand(main plugin){
        this.plugin = plugin;
        plugin.getCommand("reply").setExecutor(this);
    }

    public static HashMap<String, Player> reply = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            String prefix = main.getInstance().getConfig().getString("config.msg.msgprefix");
            Player p = (Player)sender;
            Player t = MsgCommand.reply.get(p.getName());
            if(p.hasPermission("ultron.msg.reply")){
                if(args.length > 0){
                    if(t.getName() == null){
                        p.sendMessage("§6Dieser Spieler ist §cnicht §6Online");
                        return true;
                    }
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < args.length; i++)
                        s.append(args[i]).append(" ");

                    String msg = s.toString().trim();

                    t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" &6"+ p.getName() + "§e •§6● §8» §2" + t.getName() + "§e •§6● §8» \n" + ChatColor.LIGHT_PURPLE + msg));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+"§6 Deine Nachricht " + ChatColor.LIGHT_PURPLE + s));
                } else
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" §6Bitte verwende §c/msg <Spieler> <Nachricht>"));
            } else
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+" §6Dazu hast du keine Rechte! Premission: §cultron.msg.reply §6Fehlt"));
        }
        return false;
    }
}
