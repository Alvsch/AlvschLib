package me.alvsch.alvschlib.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public CooldownManager() {

    }

    public void addCooldown(UUID uuid, long seconds) {
        cooldowns.put(uuid, System.currentTimeMillis() + seconds*1000);
    }

    public Long getCooldown(UUID uuid) {
        return cooldowns.get(uuid);
    }

    public boolean hasCooldown(UUID uuid) {
        return cooldowns.containsKey(uuid);
    }

    public static void setItemCooldown(Player player, Material type, int seconds) {
        player.setCooldown(type, seconds*20);
    }

}
