package me.alvsch.alvschlib.classes;

import me.alvsch.alvschlib.AlvschLib;
import me.alvsch.alvschlib.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ConfigurationFactory {

    public YamlConfiguration createWithDefaults(File file, String resourcePath) {
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(file);

            FileConfiguration defaultConfig = this.getFromResource(resourcePath);
            config.options().copyDefaults(true);
            config.setDefaults(defaultConfig);
        } catch (FileNotFoundException ignored) {
        } catch (IOException | InvalidConfigurationException e) {
            Logger.log(Logger.LogLevel.ERROR, "Could not load " + file);
        }

        return config;
    }

    private YamlConfiguration getFromResource(String path) throws IOException {
        InputStream stream = AlvschLib.getPlugin().getResource(path);
        if(stream == null) {
            throw new NullPointerException("Stream cannot be null!");
        }

        try(Reader reader = new InputStreamReader(stream)) {
            return YamlConfiguration.loadConfiguration(reader);
        }
    }

}
