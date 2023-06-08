package me.alvsch.alvschlib.integrations;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public abstract class Integration {

    protected final JavaPlugin plugin;
    protected final Plugin integration;
    public abstract void register();

}
