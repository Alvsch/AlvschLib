package me.alvsch.alvschlib.classes.menu.buttons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class CommandButton extends MenuButton {

    private final String command;
    private boolean console = false;

    public CommandButton(String name, @NotNull Material type, String command) {
        super(name, type, 1);
        this.command = command;
    }

    public CommandButton(String name, @NotNull Material type, String command, boolean console) {
        this(name, type, command);
        this.console = console;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if(console) {
            // Execute as console
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        } else {
            // Execute as player
            Player player = (Player) event.getWhoClicked();
            player.performCommand(command);
        }
    }

}
