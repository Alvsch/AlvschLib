package me.alvsch.alvschlib.integrations;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.logging.Level;

@Getter
public class IntegrationsManager {

    private final JavaPlugin plugin;

    /**
     * This boolean determines whether {@link #start()} was run.
     */
    private boolean isEnabled = false;

    // Soft dependencies
    private boolean isLuckPermsInstalled = false;


    public IntegrationsManager(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * This method initializes all third party integrations.
     */
    public final void start() {
        if (isEnabled) {
            // Prevent double-registration
            throw new UnsupportedOperationException("All integrations have already been loaded.");
        } else {
            isEnabled = true;
        }

        // Load any soft dependencies
        onServerLoad();
    }

    private void onServerLoad() {
        /*
        load("LuckPerms", integration -> {
            new LuckPermsIntegration(plugin).register();
            isLuckPermsInstalled = true;
        });
         */

    }

    /**
     * This method loads an integration with a {@link Plugin} of the specified name.
     * If that {@link Plugin} is installed and enabled, the provided callback will be run.
     *
     * @param pluginName
     *            The name of this {@link Plugin}
     * @param consumer
     *            The callback to run if that {@link Plugin} is installed and enabled
     */
    private void load(@NotNull String pluginName, @NotNull Consumer<Plugin> consumer) {
        Plugin integration = plugin.getServer().getPluginManager().getPlugin(pluginName);

        if (integration != null && integration.isEnabled()) {
            String version = integration.getDescription().getVersion();
            plugin.getLogger().log(Level.INFO, "Hooked into Plugin: " + pluginName + " v" + version);

            try {
                // Run our callback
                consumer.accept(integration);
            } catch (Exception | LinkageError x) {
                plugin.getLogger().log(Level.WARNING, "Failed to hook into " + pluginName + " v" + version);
            }
        }
    }
}