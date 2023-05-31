package me.alvsch.alvschlib.classes.menu.buttons;

import me.alvsch.alvschlib.classes.menu.PageMenu;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class PageButton extends MenuButton {

    private final boolean left;
    private final PageMenu menu;

    public PageButton(String title, @NotNull Material type, PageMenu menu, boolean left) {
        super(title, type, 1);
        this.left = left;
        this.menu = menu;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return !left;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        HumanEntity player = event.getWhoClicked();
        if(getLeft()) {
            player.closeInventory();
            player.openInventory(handleLeft());
        } else {
            player.closeInventory();
            player.openInventory(handleRight());
        }

    }

    private Inventory handleLeft() {
        if(menu.getCurrentPage() > 1) {
            int newPage = menu.getCurrentPage() - 1;
            menu.setCurrentPage(newPage);
        }

        return menu.getPage(menu.getCurrentPage());
    }

    private Inventory handleRight() {
        if(menu.getCurrentPage() < menu.getPages()) {
            int newPage = menu.getCurrentPage() + 1;
            menu.setCurrentPage(newPage);
        }

        return menu.getPage(menu.getCurrentPage());
    }

}
