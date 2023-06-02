package me.alvsch.alvschlib.classes;

import me.alvsch.alvschlib.AlvschLib;
import me.alvsch.alvschlib.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class Configuration extends YamlConfiguration {

    private final FileConfiguration defaultConfig;

    public Configuration(File file, String defaultFile) throws IOException {
        InputStream stream = AlvschLib.getPlugin().getResource(defaultFile);
        if(stream == null) {
            throw new NullPointerException("Stream cannot be null!");
        }

        try(Reader reader = new InputStreamReader(stream)) {
            this.defaultConfig = YamlConfiguration.loadConfiguration(reader);
        }

        try {
            this.load(file);
        } catch (FileNotFoundException ignored) {
        } catch (IOException | InvalidConfigurationException ex) {
            Logger.log(Logger.LogLevel.ERROR, "Cannot load " + file);
        }
    }

    @Nullable
    @Override
    public Object get(@NotNull String path, @Nullable Object def) {
        restoreIfMissing(path);
        return super.get(path, def);
    }

    public void restoreIfMissing(String path) {
        if(!this.isSet(path)) {
            if(!this.defaultConfig.isSet(path)) {
                return;
            }
            Object value = this.defaultConfig.get(path);
            this.set(path, value);

        }
    }

}
