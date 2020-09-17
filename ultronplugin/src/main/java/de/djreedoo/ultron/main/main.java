package de.djreedoo.ultron.main;

import com.google.common.reflect.ClassPath;
import de.djreedoo.ultron.commands.*;
import de.djreedoo.ultron.listener.*;
import de.djreedoo.ultron.util.ScoreboardManagerUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin implements Listener {
    public static main instance;
    public List<ScoreboardManagerUtil> scoreboardManagerUtils;


    public static main getInstance() {
        return main.instance;
    }

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§2§lPlugin aktiviert");
        serverOrdner();
        loadConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new listener(), this);
        this.getServer().getPluginManager().registerEvents(new InvClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new ZentraleCommand(this), this);
        this.getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new SchildListener(), this);
        commands();
        instance = this;
        scoreboardManagerUtils = new ArrayList<>();

        new BukkitRunnable() {
            @Override
            public void run() {

                if (scoreboardManagerUtils.size() > 0) {
                    scoreboardManagerUtils.forEach(scoreboardManagerUtil -> {
                        if (scoreboardManagerUtil.getPlayer() == null || scoreboardManagerUtil.getPlayer().getScoreboard() == null) {
                            scoreboardManagerUtils.remove(scoreboardManagerUtil);
                            return;
                        } else {
                            scoreboardManagerUtil.getPlayer().getScoreboard();
                        }
                        String rang = PlaceholderAPI.setPlaceholders(scoreboardManagerUtil.getPlayer(), "%luckperms_prefix%");
                        String geld = PlaceholderAPI.setPlaceholders(scoreboardManagerUtil.getPlayer(), "%vault_eco_balance_fixed%");
                        scoreboardManagerUtil.updateEntry("rank", "§6§e", rang);
                        scoreboardManagerUtil.updateEntry("online", "§0§e", "§1§l" + Bukkit.getOnlinePlayers().size() + " §7/ §1§l" + Bukkit.getMaxPlayers());
                        scoreboardManagerUtil.updateEntry("money", "§2§l", "§6§l" + geld + "€");
                        scoreboardManagerUtil.updateEntry("world", "§0§l", "§2§l" + scoreboardManagerUtil.getPlayer().getWorld().getName());
                    });
                }

            }
        }.runTaskTimer(this, 0, 20 * 2);

        registerListeners();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§2§lPlugin deaktiviert");
    }

    public void commands() {
        new MsgCommand(this);
        new MsgReplyCommand(this);
        new TpaCommand(this);
        new TpacceptCommand(this);
        new RucksackCommand(this);
        new SbToggleCommand(this);
        new DiscordCommand(this);
        new EventCommand(this);
        new HealCommand(this);
        new WorkbenchCommand(this);
        new FlyCommand(this);
        new EmailCommand(this);
        new EmailsCommand(this);
        new TabCompleteListener(this);
        new ReloadCommand(this);
        new GamemodeCommand(this);
        new ZentraleCommand(this);
        new SpawnCommand(this);
        new SetSpawnCommand(this);
        new BugCommand(this);
        new BugReadCommand(this);
        new TpCommand(this);
    }

    private void registerListeners() {
        try {
            ClassPath classPath = ClassPath.from(getClassLoader());
            classPath.getTopLevelClassesRecursive("de.djreedoo.ultron.listener.listener").forEach(info -> {
                try {
                    Class<?> clazz = Class.forName(info.getName());
                    Object obj = clazz.newInstance();
                    if (obj instanceof Listener) Bukkit.getPluginManager().registerEvents((Listener) obj, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        getConfig().options().header("Developer DJREEDOO\nNachdem du alles schön eingestellt hast\nReloade dann doch einmal deinen Server\nDamit alles übernommen wird");
        getConfig().options().copyHeader(true);
        saveConfig();
    }

    public void serverOrdner() {
        File file = new File("plugins/ultronplugin");
        if (file.mkdirs()) {
            Bukkit.getConsoleSender().sendMessage("§2§lServer Ordner erstellt: §b" + file.getName());
        } else {
            Bukkit.getConsoleSender().sendMessage("§2§lServer Ordner Exestiert bereits. §lSuper");
        }
    }
}
