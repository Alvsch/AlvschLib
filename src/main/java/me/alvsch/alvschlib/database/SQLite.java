package me.alvsch.alvschlib.database;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class SQLite extends Database {

    private final String dbname;

    @Getter
    private final File dataFolder;

    public SQLite(String databaseName, File folder, JavaPlugin plugin) {
        super(plugin);
        dbname = databaseName;
        dataFolder = folder;

        this.load();
    }

    public Connection getSQLConnection() {
        File folder = new File(dataFolder, dbname + ".db");
        if (!folder.exists()) {
            try {
                folder.getParentFile().mkdirs();
                folder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: " + dbname + ".db");
                throw new RuntimeException(e);
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + folder);
            return connection;
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize");

        } catch (ClassNotFoundException ignored) {}

        return null;
    }

    private void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS test(id PRIMARY KEY, name TEXT NOT NULL)";
            s.executeUpdate(sql);

            s.close();
        } catch (SQLException e) {

        }
        initialize();
    }

}
