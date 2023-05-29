package me.alvsch.alvschlib.listener;

import lombok.RequiredArgsConstructor;
import me.alvsch.alvschlib.classes.Clickable;
import me.alvsch.alvschlib.classes.Menu;
import me.alvsch.alvschlib.classes.MenuItem;
import org.bukkit.Material;
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
        Inventory inventory = event.getView().getTopInventory();
        int size = inventory.getSize();

        if(!(inventory.getHolder() instanceof Menu menu) || event.getClickedInventory().equals(inventory)) {
            return;
        }

        event.setCancelled(true);

        MenuItem item = menu.getItem(event.getSlot());
        if(item instanceof Clickable clickable) {
            clickable.onClick(event);
        }

    }
}
