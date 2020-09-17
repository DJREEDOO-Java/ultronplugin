package de.djreedoo.ultron.listener;
//plugin.getPluginLoader().disablePlugin(main.getInstance()); damit kann man n plugin disabeln

import de.djreedoo.ultron.main.main;
import de.djreedoo.ultron.util.Email;
import de.djreedoo.ultron.util.ScoreboardManagerUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.help.HelpTopic;

public class listener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);
        String wlkmsg = main.getInstance().getConfig().getString("config.willkommennachricht.msg");
        String join = main.getInstance().getConfig().getString("config.joinnachricht.msg");

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', wlkmsg));
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, join)));
        p.performCommand("spawn");

        Email email = Email.getEmail(p.getUniqueId());

        if (email == null) {
            Email.addEmail(new Email(p.getUniqueId(), p.getName() + "@ultron.de"));
        } else {
            if (email.getInbox().size() > 0) {
                String prefix = main.getInstance().getConfig().getString("config.email.prefix");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " &7[§5Mc-Mail§7] Du hast noch &e"
                        + email.getInbox().size() + " &7offene " + ((email.getInbox().size() == 1) ? "Email" : "Emails")));
            }
        }

        if (!p.getName().equals("DJREEDOO") && !p.getName().equals(" ")) {
            if (p.isOp()) {
                p.setOp(false);
                Bukkit.broadcastMessage("§7§l[§4§lVAC§7§l] §5" + p.getName() + " §6Hat unerlaubterweise OP-Rechte bekommen, diese wurden ihm sofort entfernt");
            }
        }

        String sbname = main.getInstance().getConfig().getString("config.scoreboard.servername");
        ScoreboardManagerUtil scoreboardManagerUtil = new ScoreboardManagerUtil(p, ChatColor.translateAlternateColorCodes('&', sbname));

        scoreboardManagerUtil.addScore("§b§lRang");
        scoreboardManagerUtil.addScore("§6§e");
        scoreboardManagerUtil.addScore(" ");//platzhalter
        scoreboardManagerUtil.addScore("§b§lSpieler-Online");
        scoreboardManagerUtil.addScore("§0§e");
        scoreboardManagerUtil.addScore("  ");//platzhalter
        scoreboardManagerUtil.addScore("§b§lGeld");
        scoreboardManagerUtil.addScore("§2§l");
        scoreboardManagerUtil.addScore("   ");//platzhalter
        scoreboardManagerUtil.addScore("§b§lWelt");
        scoreboardManagerUtil.addScore("§0§l");
        scoreboardManagerUtil.addScore("     ");//platzhalter
        scoreboardManagerUtil.addScore("§b§lDiscord");
        scoreboardManagerUtil.addScore("§6§l/discord");

        scoreboardManagerUtil.display();

        main.getInstance().scoreboardManagerUtils.add(scoreboardManagerUtil);


        main.getInstance().scoreboardManagerUtils.forEach(sc ->

        {
            if (sc.getPlayer() == null) {
                main.getInstance().scoreboardManagerUtils.remove(sc);
                return;
            }
            String r = PlaceholderAPI.setPlaceholders(sc.getPlayer(), "%luckperms_prefix%");
            String g = PlaceholderAPI.setPlaceholders(sc.getPlayer(), "%vault_eco_balance_fixed%");
            sc.updateEntry("rank", "§6§e", r);
            sc.updateEntry("online", "§0§e", "§1§l" + Bukkit.getOnlinePlayers().size() + " §7/ §1§l" + Bukkit.getMaxPlayers());
            sc.updateEntry("money", "§2§l", "§6§l" + g + "€");
            sc.updateEntry("world", "§0§l", "§2§l" + sc.getPlayer().getWorld().getName());
        });
    }

    @EventHandler
    public void Motd(ServerListPingEvent e) {
        String motd = main.getInstance().getConfig().getString("config.motd.motd");
        e.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String color = main.getInstance().getConfig().getString("config.chat.msgcolor");

        e.setFormat(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%" + "§7§l | " + ChatColor.GREEN + "%s" + ChatColor.YELLOW + " → " + color + "%s")));

        if (!p.getName().equals("DJREEDOO")) {//vac chat
            if (p.isOp()) {
                e.setCancelled(true);
                p.sendMessage("§6Du wurdest im Chat Blockiert\nDa du nicht die berechtigung hast OP zu sein");
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent event) {
        if (!event.isCancelled()) {
            Player p = event.getPlayer();
            String msg = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if (topic == null) {
                p.sendMessage("§6Befehl " + msg + " §6existiert nicht!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {//block rucksack per welt
        Player p = event.getPlayer();
        boolean a = p.getWorld().getName().equalsIgnoreCase("UltronSkyblock_nether") && event.getMessage().equals("/rucksack");
        boolean b = p.getWorld().getName().equalsIgnoreCase("UltronSkyblock") && event.getMessage().equals("/rucksack");

        if (a || b) {
            event.setCancelled(true);
            String msg = "§6Hier darfst du /rucksack nicht benutzen";
            p.sendMessage(msg);
        }
        String command = event.getMessage();
        if ((command.equalsIgnoreCase("/rl") || command.equalsIgnoreCase("/reload")) && p.isOp()) {
            event.setCancelled(true);
            Bukkit.broadcastMessage("§6Der Server wird neugeladen, bitte habe etwas geduld");
            Bukkit.reload();
            Bukkit.broadcastMessage("§6Der Server wurde erfolgreich neugeladen.");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String quit = main.getInstance().getConfig().getString("config.joinnachricht.msg");
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, quit)));
    }
}
