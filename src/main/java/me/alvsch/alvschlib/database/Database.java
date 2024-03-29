package me.alvsch.alvschlib.database;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public boolean execute(String query, Object... args) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for(int i = 0; i < args.length; i++) {
                var object = args[i];
                statement.setObject(i+1, object);
            }

            return statement.execute();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Error when executing statement", e);
        }

        return false;
    }

    public Object queryValue(String query, String column, Object... args) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for(int i = 0; i < args.length; i++) {
                var object = args[i];
                statement.setObject(i+1, object);
            }

            ResultSet result = statement.executeQuery();
            if (!result.next()) return null;

            return result.getObject(column);

        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Error when executing statement", e);
        }

        return null;
    }

    public Map<String, Object> queryRow(String query, Object... args) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for(int i = 0; i < args.length; i++) {
                var object = args[i];
                statement.setObject(i+1, object);
            }

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

    public List<Map<String, Object>> queryMultiple(String query, Object... args) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for(int i = 0; i < args.length; i++) {
                var object = args[i];
                statement.setObject(i+1, object);
            }

            ResultSet result = statement.executeQuery();
            List<Map<String, Object>> list = new ArrayList<>();

            while (result.next()) {
                ResultSetMetaData meta = result.getMetaData();
                int length = meta.getColumnCount();

                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= length; i++) {
                    map.put(meta.getColumnName(i), result.getObject(i));
                }
                list.add(map);
            }
            return list;
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
            plugin.getLogger().log(Level.SEVERE, "Error when closing database", e);
        }
    }
}