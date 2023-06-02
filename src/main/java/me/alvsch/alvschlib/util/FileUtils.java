package me.alvsch.alvschlib.util;

import me.alvsch.alvschlib.Logger;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<File> getFiles(File dir) {
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();

        if(files == null) {
            return fileList;
        }

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                List<File> nestedFiles = FileUtils.getFiles(file);
                fileList.addAll(nestedFiles);
            }
        }

        return fileList;
    }

    public static void createFile(File file) {
        if(file.exists()) return;

        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.log(Logger.LogLevel.ERROR, "Cannot create " + file);
            ex.printStackTrace();
        }
    }

}
