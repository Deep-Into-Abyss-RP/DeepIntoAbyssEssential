package eu.lilithmonodia.deepintoabyssessential.event;

import eu.lilithmonodia.deepintoabyssessential.DeepIntoAbyssEssential;
import eu.lilithmonodia.deepintoabyssessential.core.ChatType;
import eu.lilithmonodia.deepintoabyssessential.core.ChatTypesEnum;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler implements Listener {
    private static DeepIntoAbyssEssential plugin;
    public ChatHandler() {
        ChatHandler.plugin.getServer().getPluginManager().registerEvents(this, ChatHandler.plugin);
    }

    public static void sendMessageByPlayer(@NotNull Player player, ChatType chatType, String message) {
        Player[] players = player.getServer().getOnlinePlayers().toArray(new Player[0]);
        List<Player> playerInDistance = new ArrayList<>();
        final int distance = chatType.getDistance();
        String format = chatType.getFormat();
        if (format.contains("{player}")) format = format.replace("{player}", player.getName());
        if (format.contains("{message}")) format = format.replace("{message}", message);
        if (format.contains("{rpname}")) format = format.replace("{rpname}", plugin.getConfiguration().rpNames().get(player.getName()));
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
    }

    public static void setPlugin(DeepIntoAbyssEssential plugin) {
        ChatHandler.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.signedMessage().message();
        ChatType chatType;

        char[] prefixes = "#-+!(:".toCharArray();
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
