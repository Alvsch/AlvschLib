package me.alvsch.alvschlib.classes;

import lombok.RequiredArgsConstructor;
import me.alvsch.alvschlib.AlvschLib;
import me.alvsch.alvschlib.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

@RequiredArgsConstructor
public class ConfigurationFactory {

    private final AlvschLib plugin;

    public YamlConfiguration createWithDefaults(File file, String defaultResourcePath) throws FileNotFoundException {
        return this.createWithDefaults(new FileInputStream(file), defaultResourcePath);
    }

    public YamlConfiguration createWithDefaults(InputStream stream, String defaultResourcePath) {
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(new InputStreamReader(stream));

            FileConfiguration defaultConfig = this.getFromResource(defaultResourcePath);
            config.options().copyDefaults(true);
            config.setDefaults(defaultConfig);
        } catch (FileNotFoundException ignored) {
        } catch (IOException | InvalidConfigurationException e) {
            Logger.log(Logger.LogLevel.ERROR, "Could not load " + stream);
        }

        return config;
    }

    private YamlConfiguration getFromResource(String path) throws IOException {
        InputStream stream = plugin.getResource(path);
        if(stream == null) {
            throw new NullPointerException("Stream cannot be null!");
        }

        try(Reader reader = new InputStreamReader(stream)) {
            return YamlConfiguration.loadConfiguration(reader);
        }
    }

}
