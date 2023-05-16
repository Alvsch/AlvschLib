package me.alvsch.alvschlib.armor;

import lombok.Getter;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Handler;

public class ArmorEquipEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    private final ItemStack newPiece;
    @Getter
    private final ItemStack oldPiece;

    public ArmorEquipEvent(@NotNull Player who, ItemStack newPiece, ItemStack oldPiece) {
        super(who);
        this.newPiece = newPiece;
        this.oldPiece = oldPiece;
    }


    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
