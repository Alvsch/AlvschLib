package me.alvsch.alvschlib.util;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class TabCompleteUtils {

    public static List<String> filter(List<String> list, String filter) {
        return list.stream()
                .filter(entry -> entry.startsWith(filter))
                .toList();
    }

    public static List<String> onlinePlayers() {
        return TabCompleteUtils.onlinePlayers(Player::getName);
    }

    public static List<String> onlinePlayers(Function<Player, String> mapper) {
        return Bukkit.getOnlinePlayers().stream()
                .map(mapper)
                .toList();
    }

    public static TabCompleter createTabCompleter(List<String> completion, boolean filtering) {
        return new TabCompleter() {
            @Nullable
            @Override
            public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                if (args.length > completion.size()) {
                    return null;
                }

                if(!filtering) {
                    return completion;
                }

                String filter = args[args.length - 1];
                return completion.stream()
                        .filter(entry -> entry.startsWith(filter))
                        .toList();

            }
        };

    }

}
