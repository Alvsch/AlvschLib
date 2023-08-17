package me.alvsch.alvschlib;

import lombok.Setter;
import lombok.extern.java.Log;
import me.alvsch.alvschlib.util.Utils;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {
    
    @Setter
    private static boolean testing;

    /**
     * Log a message to the bukkit console
     * @param level What type of log it should be
     * @param message The message along with the log
     */
    public static void log(LogLevel level, String message) {
        if(message == null) return;
        
        if(!testing) {
            switch (level) {
                case ERROR -> Bukkit.getConsoleSender().sendMessage(Utils.color("&8[&c&lERROR&8] &f" + message));
                case WARNING -> Bukkit.getConsoleSender().sendMessage(Utils.color("&8[&7WARNING&8] &f" + message));
                case INFO -> Bukkit.getConsoleSender().sendMessage(Utils.color("&8[&fINFO&8] &f" + message));
                case SUCCESS -> Bukkit.getConsoleSender().sendMessage(Utils.color("&8[&aSUCCESS&8] &f" + message));
                case OUTLINE -> Bukkit.getConsoleSender().sendMessage(Utils.color("&7" + message));
            }
        } else {
            switch (level) {
                case ERROR -> System.out.println("[ERROR] " + message);
                case WARNING -> System.out.println("[WARNING] " + message);
                case INFO -> System.out.println("[INFO] " + message);
                case SUCCESS -> System.out.println("[SUCCESS] " + message);
                case OUTLINE -> System.out.println(message);
            }
        }



    }

    public static void error(String message) {
        Logger.log(LogLevel.ERROR, message);
    }

    public static void warning(String message) {
        Logger.log(LogLevel.WARNING, message);
    }

    public static void info(String message) {
        Logger.log(LogLevel.INFO, message);
    }

    public static void success(String message) {
        Logger.log(LogLevel.SUCCESS, message);
    }

    public static void outline(String message) {
        Logger.log(LogLevel.OUTLINE, message);
    }


    /**
     * The different types of logs
     */
    public enum LogLevel {ERROR, WARNING, INFO, SUCCESS, OUTLINE}
}