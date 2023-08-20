package me.alvsch.alvschlib.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class FileUtils {

    public static List<File> listFiles(File dir) {
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();

        if(files == null) {
            return fileList;
        }

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                List<File> nestedFiles = FileUtils.listFiles(file);
                fileList.addAll(nestedFiles);
            }
        }

        return fileList;
    }

    public static void createFile(File file) throws IOException {
        if(file.exists()) return;

        file.getParentFile().mkdirs();
        file.createNewFile();
    }

}
