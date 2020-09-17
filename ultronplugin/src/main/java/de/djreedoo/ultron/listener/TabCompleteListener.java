package de.djreedoo.ultron.listener;

import com.google.common.collect.Lists;
import de.djreedoo.ultron.main.main;
import de.djreedoo.ultron.util.Email;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteListener implements TabCompleter {
    private main plugin;
    public TabCompleteListener(main plugin){
        this.plugin = plugin;
        plugin.getCommand("emailsend").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length == 1) {

            ArrayList<String> emails = new ArrayList<>();

            Email.getEmails().forEach(email -> {
                emails.add(email.getDomain());
            });

            return emails;

        }

        return Lists.newArrayList();
    }
}
