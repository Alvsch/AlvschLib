package me.alvsch.alvschlib.classes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.alvsch.alvschlib.AlvschLib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final List<MenuItem> items;

    @Getter
    private final String title;

    public Menu(String title, int rows) {
        this.title = title;

        int size = rows*9;
        this.inventory = Bukkit.createInventory(this, size, title);
        this.items = new ArrayList<>(size);

    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void addItem(MenuItem item) {
        this.inventory.addItem(item);
        this.items.add(item);
    }

    public void setItem(int index, MenuItem item) {
        this.inventory.setItem(index, item);
        this.items.set(index, item);
    }

    public MenuItem getItem(int index) {
        return this.items.get(index);
    }


    @Override
    public Menu clone() {
        try {
            Menu clone = (Menu) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
