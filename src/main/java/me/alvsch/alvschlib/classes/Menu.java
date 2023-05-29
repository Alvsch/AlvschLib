package me.alvsch.alvschlib.classes;

import lombok.Getter;
import me.alvsch.alvschlib.AlvschLib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Cloneable {

    @Getter
    private final Inventory inventory;
    private final List<MenuItem> items;

    public Menu(String title, int rows) {
        if (rows < 2) {
            throw new IllegalArgumentException("Size must be between 2 and 6");
        }

        int size = rows*9;
        this.inventory = Bukkit.createInventory(null, size, title);
        this.items = new ArrayList<>(size);

        //45 46 47 48 49 50 51 52 53
        this.setItem(size - 1, new MenuData(this, AlvschLib.getPlugin()));
        MenuItem placeholder = new MenuItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1);
        for(int i = size - 2; i > size - 9; i--) {
            this.setItem(i, placeholder);
        }
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
}
