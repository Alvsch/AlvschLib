package me.alvsch.alvschlib.classes.input;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.alvsch.alvschlib.AlvschLib;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatInputTest {

    private static ServerMock server;
    private static PlayerMock player;

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
        player.setOp(true);
    }

    @AfterAll
    public static void tearDown() {
        // Unload the plugin
        MockBukkit.unmock();
    }

    // @Test
    void testChatInput() {
        String message = "Give me message";
        ChatInput chatInput = new ChatInput(message, (player, input) ->
                player.sendMessage(input)
        );
        chatInput.begin(AlvschLib.getPlugin(), player);
        assertEquals(message, player.nextMessage());
        player.sendMessage("hello");
        assertEquals("hello", player.nextMessage());
    }

}