package me.alvsch.alvschlib.classes.input;

import me.alvsch.alvschlib.util.Utils;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ChatInput {

    private final String message;
    private final InputCallback callback;

    public ChatInput(String message, InputCallback callback) {
        this.message = message;
        this.callback = callback;
    }

    public void begin(JavaPlugin plugin, Player player) {
        ConversationFactory conversationFactory = new ConversationFactory(plugin)
                .withModality(true)
                .withFirstPrompt(new InputPrompt())
                .withEscapeSequence("cancel")
                .addConversationAbandonedListener(conversationAbandonedEvent -> {
                    if (conversationAbandonedEvent.gracefulExit()) {
                        callback.onCallback(player, null);
                        player.sendMessage(Utils.color("&cInput cancelled."));
                    }
                });

        Conversation conversation = conversationFactory.buildConversation(player);
        conversation.begin();
    }

    private class InputPrompt extends StringPrompt {

        @Override
        public @NotNull String getPromptText(@NotNull ConversationContext context) {
            return message;
        }

        @Override
        public Prompt acceptInput(ConversationContext context, String input) {
            Player player = (Player) context.getForWhom();
            callback.onCallback(player, input);
            return Prompt.END_OF_CONVERSATION;
        }
    }
}
