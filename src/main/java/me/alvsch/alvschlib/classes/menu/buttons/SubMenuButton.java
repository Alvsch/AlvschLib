package me.alvsch.alvschlib.classes.menu.buttons;

import me.alvsch.alvschlib.classes.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class SubMenuButton extends MenuButton {

    private final Menu submenu;

    public SubMenuButton(String name, @NotNull Material type, Menu submenu) {
        super(name, type, 1);
        this.submenu = submenu;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player human = (Player) event.getWhoClicked();
        human.closeInventory();
        submenu.open(human);
    }
}
