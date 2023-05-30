package me.alvsch.alvschlib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class AlvschLib extends JavaPlugin {

    @Getter
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Logger.log(Logger.LogLevel.INFO, "Enabling AlvschLib");

    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "Disabling AlvschLib");
    }



}