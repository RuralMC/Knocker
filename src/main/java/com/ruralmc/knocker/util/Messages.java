package com.ruralmc.knocker.util;

import org.bukkit.ChatColor;

public class Messages {

    public static final String WELCOME = ChatColor.AQUA + "" + ChatColor.BOLD + "Welcome to " + ChatColor.GREEN + "" + ChatColor.BOLD + "Knocker";
    public static final String JOINED_BLUE = ChatColor.BLUE + "" + ChatColor.BOLD + "BLUE " + ChatColor.GREEN + "" + ChatColor.BOLD + "team selected!";
    public static final String JOINED_RED = ChatColor.RED + "" + ChatColor.BOLD + "RED " + ChatColor.GREEN + "" + ChatColor.BOLD + "team selected!";
    public static final String GAME_STARTING = ChatColor.GREEN + "" + ChatColor.BOLD + "GAME STARTING!";
    public static final String RED_SCORE = ChatColor.RED + "" + ChatColor.BOLD + "RED TEAM SCORED!";
    public static final String BLUE_SCORE = ChatColor.BLUE + "" + ChatColor.BOLD + "BLUE TEAM SCORED!";
    public static final String BLUE_WIN = ChatColor.BLUE + "" + ChatColor.BOLD + "BLUE" + ChatColor.GOLD + "" + ChatColor.BOLD + " TEAM WINS!!";
    public static final String RED_WIN = ChatColor.RED + "" + ChatColor.BOLD + "RED" + ChatColor.GOLD + "" + ChatColor.BOLD + " TEAM WINS!!";

    public static String SCORE(int red, int blue) {
        return ChatColor.GREEN + "" + ChatColor.BOLD + "Score: " + ChatColor.RED + "" + ChatColor.BOLD + red + ChatColor.AQUA + "" + ChatColor.BOLD + " : " + ChatColor.BLUE + "" + ChatColor.BOLD + blue;
    }
}
