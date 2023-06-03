package me.alvsch.alvschlib;

import lombok.Getter;
import me.alvsch.alvschlib.integrations.IntegrationsManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class AlvschLib extends JavaPlugin {

    @Getter
    private static JavaPlugin plugin;
    private final IntegrationsManager integrations = new IntegrationsManager(this);

    @Override
    public void onEnable() {
        plugin = this;

        Logger.log(Logger.LogLevel.INFO, "Enabling AlvschLib");

        Logger.log(Logger.LogLevel.INFO, "Loading Third-Party plugin integrations...");
        integrations.start();

    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "Disabling AlvschLib");
    }



}