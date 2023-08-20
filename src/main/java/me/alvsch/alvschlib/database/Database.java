package me.alvsch.alvschlib.database;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RequiredArgsConstructor
public abstract class Database implements AutoCloseable {

    protected final JavaPlugin plugin;
    protected Connection connection;

    public abstract Connection getSQLConnection();

    public void initialize() {
        connection = getSQLConnection();
    }

    public boolean execute(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            return statement.execute();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Error when executing statement", e);
        }

        return false;
    }

    public Object queryValue(String query, String column) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            if (!result.next()) return null;

            return result.getObject(column);

        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Error when executing statement", e);
        }

        return null;
    }

    public Map<String, Object> queryRow(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            if (!result.next()) return null;

            ResultSetMetaData meta = result.getMetaData();
            int length = meta.getColumnCount();

            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= length; i++) {
                map.put(meta.getColumnName(i), result.getObject(i));
            }
            return map;
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Error when executing statement", e);
        }

        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Test", e);
        }
    }
}