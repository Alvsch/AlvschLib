package me.alvsch.alvschlib.classes.menu;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class MenuButton extends MenuItem implements Clickable {

    public MenuButton(@NotNull Material type, int amount) {
        super(type, amount);
    }

    public MenuButton(String name, @NotNull Material type, int amount) {
        super(name, type, amount);
    }

    public MenuButton(String name, List<String> lore, @NotNull Material type, int amount) {
        super(name, lore, type, amount);
    }

}
