package me.alvsch.alvschlib.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

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

    public static ItemStack createSkullItem(UUID uuid) {
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();

        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        skullItem.setItemMeta(skullMeta);

        return skullItem;
    }

    public static void rename(ItemStack item, String updatedName) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) {
            throw new NullPointerException("ItemStack's ItemMeta cannot be null");
        }
        meta.setDisplayName(updatedName);
        item.setItemMeta(meta);
    }

    public static void lore(ItemStack item, String... lore) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null) {
            throw new NullPointerException("ItemStack's ItemMeta cannot be null");
        }
        meta.setLore(List.of(lore));
        item.setItemMeta(meta);
    }

}
