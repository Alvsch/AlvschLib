package me.alvsch.alvschlib.classes.menu;

import me.alvsch.alvschlib.util.Utils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MenuItem extends ItemStack {

    public MenuItem(@NotNull Material type, int amount) {
        super(type, amount);
    }

    public MenuItem(String name, @NotNull Material type, int amount) {
        this(type, amount);
        ItemMeta meta = this.getItemMeta();
        assert meta != null;

        meta.setDisplayName(Utils.color(name));
        this.setItemMeta(meta);
    }

    public MenuItem(String name, List<String> lore, @NotNull Material type, int amount) {
        this(type, amount);
        ItemMeta meta = this.getItemMeta();
        assert meta != null;

        meta.setDisplayName(name);
        meta.setLore(lore);
        this.setItemMeta(meta);

    }

}
