package com.ruralmc.knocker.manager;

import com.ruralmc.knocker.Knocker;
import com.ruralmc.knocker.util.Messages;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.*;

import java.util.logging.Level;

public class GameManager {

    public static Knocker plugin = null;

    //TODO inProgress
    public static boolean inProgress = false;

    public static int totalPlayers = 0;
    public static int totalRed = 0;
    public static int totalBlue = 0;

    public static ScoreboardManager sm = Bukkit.getScoreboardManager();
    public static Scoreboard sb = sm.getNewScoreboard();

    public static int scoreBlue = 0;
    public static int scoreRed = 0;

    public GameManager(Knocker plugin) {
        this.plugin = plugin;
    }

    public static void playerJoinServerEvent(Player p) {
        totalPlayers++;
        p.setScoreboard(sb);
        PlayerManager.gamePlayers.add(p);
    }

    public static void newPlayer(final Player p) {
        p.setGameMode(GameMode.ADVENTURE);
        p.setHealth(20);
        p.setExp(0);
        p.setFoodLevel(20);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getInventory().clear();
        p.teleport(new Location(Bukkit.getWorld("world"), 16.5, 63, -36.5, 180, 0));

        GameManager.playerJoinServerEvent(p);

        ItemStack t1 = new ItemStack(Material.WOOL, 1, (byte) 11);
        ItemStack t2 = new ItemStack(Material.WOOL, 1, (byte) 14);
        final Inventory picker = Bukkit.createInventory(p, 27, ChatColor.BOLD + "PICK A TEAM");
        picker.setItem(12, t1);
        picker.setItem(14, t2);
        plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            public void run() {
                p.openInventory(picker);
            }
        }, 5L);
    }

    public static void setPlayerTeam(Player p, int team) {
        if (team == 1) {
            totalBlue++;
            PlayerManager.blueTeam.add(p);
            p.teleport(ArenaManager.bs1);
            Bukkit.getServer().getLogger().log(Level.INFO, p.getDisplayName() + " added to Blue Team");
            PlayerManager.giveStick(p);
            PlayerManager.setEffects(p);
            if (totalRed >= 1 && totalBlue >= 1) {
                startGame();
            }
        } else if (team == 2) {
            totalRed++;
            PlayerManager.redTeam.add(p);
            p.teleport(ArenaManager.rs1);
            PlayerManager.giveStick(p);
            PlayerManager.setEffects(p);
            Bukkit.getServer().getLogger().log(Level.INFO, p.getDisplayName() + " added to Red Team");
            if (totalRed >= 1 && totalBlue >= 1) {
                startGame();
            }
        }

    }

    public static void startGame() {
        inProgress = true;
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            public void run() {
                Bukkit.getServer().broadcastMessage(Messages.GAME_STARTING);
            }
        },5L);

        Team rt = sb.registerNewTeam("Red");
        Team bt = sb.registerNewTeam("Blue");

        Objective obj = sb.registerNewObjective("score", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GREEN + "SCORE");
    }

    public static boolean scoreCheck(int score) {
        return score >= 16;
    }

    public static void redScore() {
        scoreRed++;
        plugin.getServer().broadcastMessage(Messages.RED_SCORE);
        plugin.getServer().broadcastMessage(Messages.SCORE(scoreRed, scoreBlue));

        if (scoreCheck(scoreRed)) {
            Bukkit.getServer().broadcastMessage(Messages.RED_WIN);
            resetGame();
        }
    }

    public static void blueScore() {
        scoreBlue++;
        plugin.getServer().broadcastMessage(Messages.BLUE_SCORE);
        plugin.getServer().broadcastMessage(Messages.SCORE(scoreRed, scoreBlue));

        if (scoreCheck(scoreBlue)) {
            Bukkit.getServer().broadcastMessage(Messages.BLUE_WIN);
            resetGame();
        }
    }

    public static void resetGame() {
        totalPlayers = 0;
        totalRed = 0;
        totalBlue = 0;
        scoreRed = 0;
        scoreBlue = 0;
        PlayerManager.gamePlayers.clear();
        PlayerManager.blueTeam.clear();
        PlayerManager.redTeam.clear();

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            newPlayer(p);
        }
    }
}
