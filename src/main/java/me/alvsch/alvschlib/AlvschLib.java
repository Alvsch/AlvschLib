package me.alvsch.alvschlib;

import me.alvsch.alvschlib.integrations.IntegrationsManager;
import me.alvsch.alvschlib.listener.MenuListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.TestOnly;

import java.io.File;
import java.util.logging.Level;

public class AlvschLib extends JavaPlugin {

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

        getLogger().log(Level.INFO, "Enabling AlvschLib");

        new MenuListener().register(this);

        getLogger().log(Level.INFO, "Loading Third-Party plugin integrations...");
        integrations.start();

    }

    @Override
    public void onDisable() {
        if (unitTest) return;

        getLogger().log(Level.INFO, "Disabling AlvschLib");
    }

    public static JavaPlugin getInstance() {
        return plugin;
    }

}