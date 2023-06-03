package me.alvsch.alvschlib.integrations;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class LuckPermsIntegration {

    private final JavaPlugin plugin;
    private LuckPerms api;

    LuckPermsIntegration(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        this.api = LuckPermsProvider.get();
    }

}
