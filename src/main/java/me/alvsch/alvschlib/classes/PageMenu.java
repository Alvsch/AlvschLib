package me.alvsch.alvschlib.classes;

import lombok.Getter;
import org.bukkit.Material;

public class PageMenu {

    @Getter
    private final Menu menu;

    public PageMenu(String title, int rows) {
        if(rows < 1 || rows > 5) {
            throw new IllegalArgumentException("Page Menu rows must be between 1 and 5");
        }

        this.menu = new Menu(title, rows+9);
        this.menu.setItem(1, null);

        addCloseButton();
        addPageButtons();
    }

    private void addCloseButton() {
        int size = this.menu.getInventory().getSize();
        int slot = size - 5;
        MenuItem close = new MenuItem("&cClose", Material.BARRIER, 1);
        this.menu.setItem(slot, close);
        //45 46 47 48 49 50 51 52 53
    }

    private void addPageButtons() {
        int size = this.menu.getInventory().getSize();
        int left = size - 9;
        int right = size - 1;

        MenuItem pageLeft = new MenuItem("&fLeft", Material.ARROW, 1);
        MenuItem pageRight = new MenuItem("&fRight", Material.ARROW, 1);

        this.menu.setItem(left, pageLeft);
        this.menu.setItem(right, pageRight);
    }

}
