package me.alvsch.alvschlib.classes.menu;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.alvsch.alvschlib.AlvschLib;
import me.alvsch.alvschlib.classes.menu.buttons.CloseButton;
import me.alvsch.alvschlib.classes.menu.buttons.MenuButton;
import me.alvsch.alvschlib.listener.MenuListener;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private static ServerMock server;
    private static PlayerMock player;

    private static final MenuListener listener = new MenuListener();

    @BeforeAll
    public static void setUp() {
        // Mock the server and plugin
        server = MockBukkit.mock();
        MockBukkit.load(AlvschLib.class);
    }

    @BeforeEach
    public void beforeEach() {
        // Kick all players
        server.setPlayers(0);
        player = server.addPlayer("player");
    }

    @AfterAll
    public static void tearDown() {
        // Unload the plugin
        MockBukkit.unmock();
    }

    @Test
    void menuCloseButtonTest() {
        Menu menu = new Menu("Test Menu", 6);
        menu.addItem(new CloseButton("Hello", Material.BARRIER));
        menu.open(player);

        Inventory inventory = player.getOpenInventory().getTopInventory();
        assertInstanceOf(Menu.class, inventory.getHolder());

        InventoryClickEvent event = player.simulateInventoryClick(0);
        listener.onInventoryClick(event);

        assertEquals(InventoryType.CRAFTING, player.getOpenInventory().getType());

    }

}