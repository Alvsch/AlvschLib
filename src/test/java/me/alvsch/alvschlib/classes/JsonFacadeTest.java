package me.alvsch.alvschlib.classes;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.alvsch.alvschlib.AlvschLib;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonFacadeTest {

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
    void jsonObjectTest() {
        JsonFacade facade = new JsonFacade(new JsonObject());
        facade.set("user.name", "Alvsch");
        facade.set("user.email", "Alvsch@mail.com");

        assertEquals("Alvsch", facade.get("user.name").getAsString());
        assertEquals("Alvsch@mail.com", facade.get("user.email").getAsString());

        assertNull(facade.get("user.name.hello"));
        assertNull(facade.get("username.name"));

        assertTrue(facade.has("user.name"));
        assertFalse(facade.has("user.password"));

    }

    @Test
    void fileTest() {
        Reader reader = new InputStreamReader(plugin.getResource("jsonfacade/data.json"));
        JsonFacade facade = new JsonFacade(reader);
        // TODO continue

        assertEquals("Alvsch", facade.get("user.name").getAsString());
        assertEquals("Alvsch@gmail.com", facade.get("user.email").getAsString());

        assertNull(facade.get("user.name.hello"));
        assertNull(facade.get("username.name"));

        assertTrue(facade.has("user.name"));
        assertFalse(facade.has("user.password"));
    }

    @Test
    void compareTest() {
        JsonFacade facade = new JsonFacade(new JsonObject());
        JsonObject jsonObject = facade.getJsonObject();

        JsonElement element = JsonParser.parseReader(new InputStreamReader(plugin.getResource("jsonfacade/compare.json")));
        JsonObject comparison = element.getAsJsonObject();

        assertNotEquals(comparison.toString(), jsonObject.toString());

        facade.set("hello.test.bruh", "1");
        facade.set("hello.test.bro", "2");

        facade.set("hello.bull", "yeah");

        assertEquals(comparison, jsonObject);
    }

}