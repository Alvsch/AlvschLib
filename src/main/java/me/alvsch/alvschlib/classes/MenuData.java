package me.alvsch.alvschlib.classes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuData extends MenuItem {

    public MenuData(Menu menu, JavaPlugin plugin, Material type) {
        super(" ", type, 1);
        ItemMeta meta = this.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, "menu");

        // TODO: Save modifiability, has close button, is page menu.
        pdc.set(key, PersistentDataType.STRING, "menu");
    }

    public MenuData(Menu menu, JavaPlugin plugin) {
        this(menu, plugin, Material.BLACK_STAINED_GLASS_PANE);
    }
}
