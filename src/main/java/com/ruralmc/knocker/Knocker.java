package com.ruralmc.knocker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Knocker extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getLogger().log(Level.INFO, "Enabled");
    }

    @Override
    public void onDisable() {
        this.getServer().getLogger().log(Level.INFO, "Disabled");
    }
}
