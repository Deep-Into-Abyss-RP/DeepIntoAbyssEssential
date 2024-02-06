package eu.lilithmonodia.deepintoabyssessential.event;

import eu.lilithmonodia.deepintoabyssessential.DeepIntoAbyssEssential;
import eu.lilithmonodia.deepintoabyssessential.core.ChatType;
import eu.lilithmonodia.deepintoabyssessential.core.ChatTypesEnum;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The ChatHandler class handles chat-related events and message sending in the DeepIntoAbyssEssential plugin.
 * It implements the Listener interface and provides methods for sending chat messages by player.
 */
public class ChatHandler implements Listener {
    private static DeepIntoAbyssEssential plugin;

    /**
     * The ChatHandler class handles chat-related events and message sending in the DeepIntoAbyssEssential plugin.
     * It implements the Listener interface and provides methods for sending chat messages by player.
     */
    public ChatHandler() {
        ChatHandler.plugin.getServer().getPluginManager().registerEvents(this, ChatHandler.plugin);
    }

    /**
     * Sends a message by player using a specified chat type.
     *
     * @param player   The player sending the message.
     * @param chatType The chat type indicating how the message should be formatted and distributed.
     * @param message  The message to be sent.
     */
    public static void sendMessageByPlayer(@NotNull Player player, @NotNull ChatType chatType, String message) {
        Player[] players = player.getServer().getOnlinePlayers().toArray(new Player[0]);
        List<Player> playerInDistance = new ArrayList<>();
        final int distance = chatType.distance();
        String format = chatType.format();
        NamedTextColor color = chatType.color();
        if (format.contains("{player}")) format = format.replace("{player}", player.getName());
        if (format.contains("{message}")) format = format.replace("{message}",color + message);
        if (format.contains("{rpname}"))
            format = format.replace("{rpname}", plugin.getConfiguration().rpNames().get(player.getName()));
        if (distance == 0) {
            playerInDistance.addAll(List.of(players));
        } else {
            for (Player p : players) {
                if (p.getLocation().distance(player.getLocation()) <= distance) {
                    playerInDistance.add(p);
                }
            }
        }
        for (Player p : playerInDistance) {
            p.sendMessage(format);
        }

        plugin.getLogger().info(format);
    }

    /**
     * Sets the plugin instance for the ChatHandler class.
     *
     * @param plugin The DeepIntoAbyssEssential plugin instance.
     */
    public static void setPlugin(DeepIntoAbyssEssential plugin) {
        ChatHandler.plugin = plugin;
    }

    /**
     * Handles the player chat event and cancels it.
     * Parses the message and determines the chat type based on the message prefix.
     * Sends the formatted message to the appropriate players.
     *
     * @param event The AsyncChatEvent representing the player chat event.
     */
    @EventHandler
    public void onPlayerChat(@NotNull AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.signedMessage().message();
        ChatType chatType;

        char[] prefixes = "#-+!(:*".toCharArray();
        for (char prefix : prefixes) {
            if (message.charAt(0) == prefix) {
                chatType = ChatTypesEnum.getChatTypeByPrefix(String.valueOf(prefix));
                sendMessageByPlayer(player, chatType, message.substring(1));
                return;
            }
        }
        chatType = ChatTypesEnum.DEFAULT.getChatType();
        sendMessageByPlayer(player, chatType, message);
    }
}
