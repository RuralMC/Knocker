package com.ruralmc.knocker.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class PlayerManager {

    public static ArrayList<Player> gamePlayers = new ArrayList<Player>();
    public static String blue = "Blue";
    public static String red = "Red";
    public static ArrayList<Player> blueTeam = new ArrayList<Player>();
    public static ArrayList<Player> redTeam = new ArrayList<Player>();

    public static void giveStick(Player p) {
        ItemStack stick = new ItemStack(Material.STICK, 1);
        ItemMeta stickMeta = stick.getItemMeta();
        stickMeta.addEnchant(Enchantment.KNOCKBACK, 100, true);
        stickMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Knocker");
        stickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("The great stick of knocking!!");
        stickMeta.setLore(lore);
        stick.setItemMeta(stickMeta);

        p.getInventory().clear();
        p.getInventory().setItem(0, stick);
    }

    public static void setEffects(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000000, 2));
    }
}
