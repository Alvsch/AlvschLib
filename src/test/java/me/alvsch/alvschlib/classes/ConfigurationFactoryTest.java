package me.alvsch.alvschlib.classes;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.alvsch.alvschlib.AlvschLib;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationFactoryTest {

    private static ServerMock server;
    private static AlvschLib plugin;

    @BeforeAll
    public static void setUp() {
        // Mock the server and plugin
        server = MockBukkit.mock();
        plugin = MockBukkit.load(AlvschLib.class);
    }

    @AfterAll
    public static void tearDown() {
        // Unload the plugin
        MockBukkit.unmock();
    }

    @Test
    void allValuesExist() {

        ConfigurationFactory factory = new ConfigurationFactory(plugin);
        InputStream is = plugin.getResource("configurationfactory/config.yml");
        YamlConfiguration config = factory.createWithDefaults(is, "configurationfactory/default.yml");

        String name1 = config.getString("user1.name");
        String pass1 = config.getString("user1.password");
        String email1 = config.getString("user1.email");

        String name2 = config.getString("user2.name");
        String pass2 = config.getString("user2.password");

        String name3 = config.getString("user3.name");
        String pass3 = config.getString("user3.password");


        assertEquals("user1", name1);
        assertEquals("pass123", pass1);
        assertEquals("user1@hotmail.com", email1);

        assertEquals("user2", name2);
        assertEquals("password123!", pass2);

        assertEquals("user3", name3);
        assertEquals("pass!", pass3);

    }

}