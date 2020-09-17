package de.djreedoo.ultron.commands;

import de.djreedoo.ultron.main.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class SbToggleCommand implements CommandExecutor {
    private main plugin;

    public SbToggleCommand(main plugin) {
        this.plugin = plugin;
        plugin.getCommand("sbtoggle").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                main.getInstance().scoreboardManagerUtils.forEach(scoreboardManagerUtil -> {
                    if(scoreboardManagerUtil.getPlayer() == p) {
                        if(scoreboardManagerUtil.getDisplaySlot() == null) {
                            scoreboardManagerUtil.setDisplaySlot(DisplaySlot.SIDEBAR);
                            scoreboardManagerUtil.getObjective().setDisplaySlot(DisplaySlot.SIDEBAR);
                        } else {
                            scoreboardManagerUtil.setDisplaySlot(null);
                            scoreboardManagerUtil.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                        }
                    }
                });
            }else
                p.sendMessage("Error");
        }
        return false;
    }
}
