package me.alvsch.alvschlib.listener;

import me.alvsch.alvschlib.classes.menu.Clickable;
import me.alvsch.alvschlib.classes.menu.Menu;
import me.alvsch.alvschlib.classes.menu.MenuItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MenuListener implements Listener {

    private final JavaPlugin plugin;

    public MenuListener(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getView().getTopInventory();

        if(Objects.equals(event.getClickedInventory(), inventory) ||
                !(inventory.getHolder() instanceof Menu menu)
        ) return;

        event.setCancelled(true);

        MenuItem item = menu.getItem(event.getSlot());
        if(item instanceof Clickable clickable) {
            clickable.onClick(event);
        }

    }
}