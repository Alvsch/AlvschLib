package me.alvsch.alvschlib.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {

    public static ItemStack createItem(Material material, int amount, String title) {
        return createItem(material, amount, title, null);
    }

    public static ItemStack createItem(Material material, int amount, String title, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.color(title));
        meta.setLore(Utils.color(lore));

        item.setItemMeta(meta);
        return item;
    }

}
