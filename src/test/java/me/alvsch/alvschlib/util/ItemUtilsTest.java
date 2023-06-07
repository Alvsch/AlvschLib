package me.alvsch.alvschlib.util;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.alvsch.alvschlib.AlvschLib;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemUtilsTest {

    private static ServerMock server;

    @BeforeAll
    public static void setUp() {
        // Mock the server and plugin
        server = MockBukkit.mock();
        MockBukkit.load(AlvschLib.class);
    }

    @AfterAll
    public static void tearDown() {
        // Unload the plugin
        MockBukkit.unmock();
    }

    @Test
    void createItem() {
        ItemStack item = ItemUtils.createItem(Material.DIAMOND, 1, "Hello");

        assertEquals(Material.DIAMOND, item.getType());
        assertEquals(1, item.getAmount());
        assertEquals("Hello", item.getItemMeta().getDisplayName());
    }

    @Test
    void rename() {
        ItemStack item = ItemUtils.createItem(Material.BARRIER, 1, "Test1");
        assertEquals("Test1", item.getItemMeta().getDisplayName());
        ItemUtils.rename(item, "Test2");
        assertEquals("Test2", item.getItemMeta().getDisplayName());
    }

    @Test
    void testRenameThrow() {
        ItemStack mock = Mockito.mock(ItemStack.class);
        Mockito.when(mock.getItemMeta()).thenReturn(null);

        assertThrows(NullPointerException.class, () ->
                ItemUtils.rename(mock, "Test")
        );
        assertThrows(NullPointerException.class, () ->
                ItemUtils.lore(mock, "Test")
        );
    }

    @Test
    void lore() {
        ItemStack item = ItemUtils.createItem(Material.BARRIER, 1, "Test1");
        ItemUtils.lore(item, "Hello",
                "My",
                "Name",
                "Is"
        );
        assertEquals(item.getItemMeta().getLore(), List.of("Hello", "My", "Name", "Is"));
    }
}