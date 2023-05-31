package me.alvsch.alvschlib.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TabCompleteUtils {

    public static List<String> filter(List<String> list, String filter) {
        return list.stream()
                .filter(entry -> entry.startsWith(filter))
                .toList();
    }

    public static List<String> onlinePlayers(String filter) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.startsWith(filter))
                .toList();
    }

}
