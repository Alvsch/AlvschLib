package me.alvsch.alvschlib.classes;

import lombok.Getter;
import org.bukkit.Material;

public class PageMenu extends Menu {

    @Getter
    private int pages = 1;
    @Getter
    private int current_page = 1;

    public PageMenu(String title, int rows) {
        super(title, rows);

        addCloseButton();
        addPageButtons();
    }

    private void addCloseButton() {
        int size = this.getInventory().getSize();
        int slot = size - 5;
        MenuItem close = new MenuItem("&cClose", Material.BARRIER, 1);
        this.setItem(slot, close);
    }

    private void addPageButtons() {
        int size = this.getInventory().getSize();
        int left = size - 9;
        int right = size - 1;

        MenuItem pageLeft = new MenuItem("&fLeft", Material.ARROW, 1);
        MenuItem pageRight = new MenuItem("&fRight", Material.ARROW, 1);

        this.setItem(left, pageLeft);
        this.setItem(right, pageRight);
    }

}
