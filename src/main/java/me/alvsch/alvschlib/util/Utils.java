package me.alvsch.alvschlib.util;


import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern COLOR_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> list) {
        if (list == null ||  list.isEmpty()) return List.of();
        return list.stream().map(Utils::color).toList();
    }

    public static String hexColor(String message) {
        Matcher matcher = COLOR_PATTERN.matcher(message);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String hexCode = matcher.group(1);
            ChatColor chatColor = ChatColor.of("#" + hexCode);

            matcher.appendReplacement(sb, chatColor.toString());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }


}
