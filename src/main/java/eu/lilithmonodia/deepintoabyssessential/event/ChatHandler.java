package eu.lilithmonodia.deepintoabyssessential.event;

import eu.lilithmonodia.deepintoabyssessential.DeepIntoAbyssEssential;
import eu.lilithmonodia.deepintoabyssessential.core.ChatType;
import eu.lilithmonodia.deepintoabyssessential.core.ChatTypesEnum;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ChatHandler implements Listener {
    private static final String[] MESSAGE_FORMAT_PARAMS = {"{player}", "{message}", "{rpname}"};
    private static DeepIntoAbyssEssential plugin;

    public ChatHandler(DeepIntoAbyssEssential mainPlugin) {
        ChatHandler.plugin = mainPlugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @NotNull
    private static List<Player> getOnlineOperators() {
        return plugin.getServer().getOperators().stream()
                .filter(OfflinePlayer::isOnline)
                .map(OfflinePlayer::getPlayer)
                .toList();
    }

    @NotNull
    private static String replaceFormatParams(String format, Player player, String colorCodedMessage) {
        String replacedFormat = format;
        if (replacedFormat.contains(MESSAGE_FORMAT_PARAMS[0]))
            replacedFormat = replacedFormat.replace(MESSAGE_FORMAT_PARAMS[0], player.getName());
        if (replacedFormat.contains(MESSAGE_FORMAT_PARAMS[1]))
            replacedFormat = replacedFormat.replace(MESSAGE_FORMAT_PARAMS[1], colorCodedMessage);
        if (replacedFormat.contains(MESSAGE_FORMAT_PARAMS[2]))
            replacedFormat = replacedFormat.replace(MESSAGE_FORMAT_PARAMS[2],
                    plugin.getConfiguration().rpNames().get(player.getName()));

        return replacedFormat;
    }

    public static void sendMessageByPlayer(@NotNull Player player, @NotNull ChatType chatType, String message) {
        List<Player> ops = getOnlineOperators();
        List<Player> playersInReach = new ArrayList<>();
        String colorCodedMessage = ChatColor.of(chatType.color().asHexString()).toString() + message;
        String format = replaceFormatParams(chatType.format(), player, colorCodedMessage);

        if (chatType.distance() == 0) {
            playersInReach = chatType.privateChat() ? List.copyOf(ops) :
                    Stream.of(plugin.getServer().getOnlinePlayers().toArray(Player[]::new))
                            .toList();
            playersInReach.add(player);
        } else {
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                if (onlinePlayer.getLocation().distance(player.getLocation()) <= chatType.distance()) {
                    playersInReach.add(onlinePlayer);
                }
            }
        }

        playersInReach.forEach(playerInReach -> playerInReach.sendMessage(format));

        plugin.getLogger().info(String.format("%s sent a message: %s", player.getName(), message));
    }

    @EventHandler
    public void onPlayerChat(@NotNull AsyncChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.signedMessage().message();
        List<String> prefixes = Arrays.stream(ChatTypesEnum.values()).map(chatType -> chatType.getChatType().prefix()).sorted(Comparator.comparing(String::length).reversed()).toList();

        for (String prefix : prefixes) {
            if (message.startsWith(prefix)) {
                ChatType chatType = ChatTypesEnum.getChatTypeByPrefix(prefix);
                // Check if message is mJExclusive and player is not an operator
                if (chatType.mJExclusive() && !player.isOp()) {
                    player.sendMessage("You do not have permission to send a mJExclusive message.");
                    return;
                }
                sendMessageByPlayer(player, chatType, message.substring(prefix.length()));
                return;
            }
        }
        sendMessageByPlayer(player, ChatTypesEnum.DEFAULT.getChatType(), message);
    }
}