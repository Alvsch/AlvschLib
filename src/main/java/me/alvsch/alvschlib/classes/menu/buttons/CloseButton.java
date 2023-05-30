package me.alvsch.alvschlib.classes.menu.buttons;

import me.alvsch.alvschlib.classes.menu.MenuButton;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class CloseButton extends MenuButton {

    public CloseButton(String title, @NotNull Material type) {
        super(title, type, 1);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().closeInventory();
    }
}
