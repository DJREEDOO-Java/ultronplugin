package de.djreedoo.ultron.util;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class ScoreboardManagerUtil {
    private Scoreboard scoreboard;
    private Objective objective;
    private Player player;

    private String title;
    private List<String> scoreboardPoints;
    private DisplaySlot displaySlot;

    public ScoreboardManagerUtil(Player player, String title) {
        this.player = player;

        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective(player.getName(),player.getName());
        this.scoreboardPoints = Lists.newArrayList();
        this.title = title;
        this.displaySlot = null;
    }


    public void display() {

        this.objective.setDisplayName(this.title);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.displaySlot = DisplaySlot.SIDEBAR;

        int size = this.scoreboardPoints.size();

        for(String str : this.scoreboardPoints) {
            this.objective.getScore(str).setScore(size);
            size--;
        }

        this.player.setScoreboard(this.scoreboard);

    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }


    public DisplaySlot getDisplaySlot() {
        return displaySlot;
    }

    public void setDisplaySlot(DisplaySlot displaySlot) {
        this.displaySlot = displaySlot;
    }

    public Objective getObjective() {
        return objective;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getScoreboardPoints() {
        return scoreboardPoints;
    }




    public void addScore(String text) {
        this.scoreboardPoints.add(text);
    }


    public void updateEntry(String team, String entry, String prefix) {

        Team t = (this.scoreboard.getTeam(team) != null) ? this.scoreboard.getTeam(team) : this.scoreboard.registerNewTeam(team);

        t.addEntry(entry);
        t.setPrefix(prefix);

    }
}
