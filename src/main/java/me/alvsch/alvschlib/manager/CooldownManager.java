package me.alvsch.alvschlib.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager<T> {

    private final Map<T, Long> cooldowns = new HashMap<>();

    public CooldownManager() {

    }

    public void addCooldown(T k, long seconds) {
        cooldowns.put(k, System.currentTimeMillis() + seconds*1000);
    }

    public Long getCooldown(T k) {
        return cooldowns.get(k);
    }

    public boolean hasCooldown(T k) {
        return cooldowns.containsKey(k);
    }

    public boolean expired(T k) {
        Long millis = cooldowns.get(k);
        if (millis == null) return true;
        return millis < System.currentTimeMillis();
    }

    public static void setItemCooldown(Player player, Material type, int seconds) {
        player.setCooldown(type, seconds*20);
    }

}
