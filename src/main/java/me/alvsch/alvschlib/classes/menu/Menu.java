package me.alvsch.alvschlib.classes.menu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Menu implements InventoryHolder {

    private final List<MenuItem> items;

    @Getter
    private final String title;
    @Getter
    private final int size;

    public Menu(String title, int rows) {
        this.title = title;
        this.size = rows*9;

        this.items = new ArrayList<>(size);
    }

    public void open(Player player) {
        player.openInventory(this.getInventory());
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public void setItem(int index, MenuItem item) {
        this.items.set(index, item);
    }

    public MenuItem getItem(int index) {
        return this.items.get(index);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, title);
        inventory.setContents(this.items.toArray(new MenuItem[0]));
        return inventory;
    }
}
