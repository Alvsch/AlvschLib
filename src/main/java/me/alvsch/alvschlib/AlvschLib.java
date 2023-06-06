package me.alvsch.alvschlib;

import lombok.Getter;
import me.alvsch.alvschlib.integrations.IntegrationsManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.TestOnly;

import java.io.File;

public class AlvschLib extends JavaPlugin {

    @Getter
    private static JavaPlugin plugin;
    private final IntegrationsManager integrations = new IntegrationsManager(this);
    private final boolean unitTest;

    public AlvschLib() {
        super();
        unitTest = false;
    }

    @TestOnly
    protected AlvschLib(
            JavaPluginLoader loader,
            PluginDescriptionFile description,
            File dataFolder,
            File file) {
        super(loader, description, dataFolder, file);
        unitTest = true;
    }

    @Override
    public void onEnable() {
        plugin = this;

        if(unitTest) return;

        Logger.log(Logger.LogLevel.INFO, "Enabling AlvschLib");

        Logger.log(Logger.LogLevel.INFO, "Loading Third-Party plugin integrations...");
        integrations.start();

    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "Disabling AlvschLib");
    }



}