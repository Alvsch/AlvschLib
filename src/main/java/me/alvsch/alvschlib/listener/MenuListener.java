package me.alvsch.alvschlib.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class MenuListener implements Listener {

    private final JavaPlugin plugin;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory topInventory = event.getView().getTopInventory();
        ItemStack data = topInventory.getItem(topInventory.getSize() - 1);

        // Get data and check
        if(!true) {
            return;
        }
        // Handle, this is a menu
        event.setCancelled(true);

    }
}
