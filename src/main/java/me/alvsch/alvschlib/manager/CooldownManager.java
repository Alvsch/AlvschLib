package me.alvsch.alvschlib.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public CooldownManager() {

    }

    public void addCooldown(UUID uuid, Long seconds) {
        cooldowns.put(uuid, System.currentTimeMillis() + seconds*1000);
    }

    public Long getCooldown(UUID uuid) {
        return cooldowns.get(uuid);
    }

    public boolean hasCooldown(UUID uuid) {
        return cooldowns.containsKey(uuid);
    }

}
