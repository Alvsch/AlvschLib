package me.alvsch.alvschlib.util;

import org.bukkit.ChatColor;

public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
