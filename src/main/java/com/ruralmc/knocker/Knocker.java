package com.ruralmc.knocker;

import com.ruralmc.knocker.event.PlayerListener;
import com.ruralmc.knocker.manager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Knocker extends JavaPlugin {

    @Override
    public void onEnable() {

        new PlayerListener(this);
        new GameManager(this);

        this.getLogger().log(Level.INFO, "Enabled");
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Disabled");
    }
}
