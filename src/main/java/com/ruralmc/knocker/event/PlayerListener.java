package com.ruralmc.knocker.event;

import com.ruralmc.knocker.Knocker;
import com.ruralmc.knocker.manager.ArenaManager;
import com.ruralmc.knocker.manager.GameManager;
import com.ruralmc.knocker.manager.PlayerManager;
import com.ruralmc.knocker.util.Messages;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerListener implements Listener {

    private final Knocker plugin;

    public PlayerListener(Knocker plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.sendMessage(Messages.WELCOME);

        GameManager.newPlayer(p);
    }

    @EventHandler
    public void onInvClock(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        PlayerInventory pi = p.getInventory();

        if (!(e.getInventory().getName().equalsIgnoreCase(ChatColor.BOLD + "PICK A TEAM"))) return;

        if (e.getCurrentItem().equals(new ItemStack(Material.WOOL, 1, (byte) 11))) {
            e.setCancelled(true);
            p.closeInventory();
            GameManager.setPlayerTeam(p, 1);
            p.sendMessage(Messages.JOINED_BLUE);
        } else if (e.getCurrentItem().equals(new ItemStack(Material.WOOL, 1, (byte) 14))) {
            e.setCancelled(true);
            p.closeInventory();
            GameManager.setPlayerTeam(p, 2);
            p.sendMessage(Messages.JOINED_RED);
        }
    }

    @EventHandler
    public void damageEvent(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            e.setDamage(0);
        }
    }

    @EventHandler
    public void foodChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        Player pl = e.getPlayer();
        Location loc = pl.getLocation();
        double lZ = loc.getZ();

        //TODO PRE GAME
        if (!GameManager.inProgress) {
            e.setCancelled(true);
        }

        if (PlayerManager.blueTeam.contains(pl)) {
            if (lZ >= 47 && lZ < 48) {
                pl.teleport(ArenaManager.bs1);
                GameManager.redScore();
            }
            if (lZ <= 18) {
                pl.teleport(pl.getLocation().add(0, 0, 2));
            }
        } else if (PlayerManager.redTeam.contains(pl)) {
            if (lZ >= 1 && lZ < 2) {
                pl.teleport(ArenaManager.rs1);
                GameManager.blueScore();
            }
            if (lZ >= 30) {
                pl.teleport(pl.getLocation().subtract(0, 0, 2));
            }
            //todo red member crosses red line
        }
    }

}
