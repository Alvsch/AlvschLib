package me.alvsch.alvschlib.classes;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonFacadeTest {

    @Test
    void universalTest() {
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

}