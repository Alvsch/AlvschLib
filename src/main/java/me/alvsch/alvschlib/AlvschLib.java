package me.alvsch.alvschlib;

import org.bukkit.plugin.java.JavaPlugin;

public class AlvschLib extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.log(Logger.LogLevel.INFO, "Enabling AlvschLib");

    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "Disabling AlvschLib");

    }

}