package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import de.djreedoo.ultron.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class EmailCommand implements CommandExecutor {
    private main plugin;

    public EmailCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("emailsend").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if(args.length >= 2) {
            Email email = Email.getEmail(args[0]);
            if(email == null) {
                p.sendMessage("§4Diese Email ist nicht vorhanden.");
                return true;
            }
            Email myEmail = Email.getEmail(p.getUniqueId());
            String message = "";
            for(int i = 1; i < args.length; i++) message+=args[i]+" ";
            message = message.substring(0, message.length()-1);
            email.getInbox().put(message, myEmail.getDomain());
            p.sendMessage("§aDeine Email wurde erfolgreich versendet.");

            Player t = Bukkit.getPlayer(email.getDomain().split("@")[0]);

            if(t != null) {
                t.sendMessage("§6Du hast eine Email erhalten sehe sie dir mit §2/emails §6an");
            }

        } else {
            p.sendMessage("§6Bitte benutze §c/emailsend <email> <message>");
        }

        return false;
    }
}
