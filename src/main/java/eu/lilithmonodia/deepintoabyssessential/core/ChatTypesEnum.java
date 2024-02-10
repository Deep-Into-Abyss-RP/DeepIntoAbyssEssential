package eu.lilithmonodia.deepintoabyssessential.core;

import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * ChatTypesEnum is an enumeration that represents different types of chat messages in a chat system.
 */
public enum ChatTypesEnum {
    HRP(new ChatType("(", 20, "<{rpname}> ({message})", NamedTextColor.GRAY, false, false)),
    DEFAULT(new ChatType("", 20, Constants.RPNAME_INDICATOR + Constants.GREEN + "Dit" + Constants.WHITE + "]> {message}", NamedTextColor.GREEN, false, false)),
    WHISPER(new ChatType("#", 3, Constants.RPNAME_INDICATOR + Constants.DARK_AQUA + "Chuchote" + Constants.WHITE + "]> {message}", NamedTextColor.DARK_AQUA, true, false)),
    SHOUT(new ChatType("!", 100, Constants.RPNAME_INDICATOR + Constants.RED + "Crie" + Constants.WHITE + "]> {message}", NamedTextColor.RED, false, false)),
    LOUD(new ChatType("+", 50, Constants.RPNAME_INDICATOR + Constants.YELLOW + "Dit fort" + Constants.WHITE + "]> {message}", NamedTextColor.YELLOW, false, false)),
    QUIET(new ChatType("-", 10, Constants.RPNAME_INDICATOR + Constants.DARK_GREEN + "Dit bas" + Constants.WHITE + "]> {message}", NamedTextColor.DARK_GREEN, false, false)),
    GLOBAL(new ChatType(":", 0, "<{rpname}> {message}", NamedTextColor.WHITE, false, false)),
    STAFF(new ChatType("$", 0, "<{rpname}> {message}", NamedTextColor.DARK_PURPLE, true, false)),
    WHISPER_ACTION(new ChatType("#*", 3, Constants.ACTION_MESSAGE, NamedTextColor.GREEN, false, false)),
    CLOSE_ACTION(new ChatType("-*", 5, Constants.ACTION_MESSAGE, NamedTextColor.DARK_GREEN, false, false)),
    NORMAL_ACTION(new ChatType("*", 20, Constants.ACTION_MESSAGE, NamedTextColor.GREEN, false, false)),
    FAR_ACTION(new ChatType("+*", 50, Constants.ACTION_MESSAGE, NamedTextColor.YELLOW, false, false)),
    SHOUT_ACTION(new ChatType("!*", 100, Constants.ACTION_MESSAGE, NamedTextColor.RED, false, false)),
    WHISPER_ENCADREMENT(new ChatType("#$$$", 3, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    CLOSE_ENCADREMENT(new ChatType("-$$$", 5, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    NORMAL_ENCADREMENT(new ChatType("$$$", 20, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    FAR_ENCADREMENT(new ChatType("+$$$", 50, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    SHOUT_ENCADREMENT(new ChatType("!$$$", 100, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    GLOBAL_ENCADREMENT(new ChatType(":$$$", 0, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true));

    private final ChatType chatType;

    /**
     * Creates a ChatTypesEnum object with the specified ChatType.
     *
     * @param chatType the ChatType associated with the ChatTypesEnum object
     */
    ChatTypesEnum(ChatType chatType) {
        this.chatType = chatType;
    }

    /**
     * Retrieves the chat type based on the given prefix.
     *
     * @param prefix The prefix associated with the chat type.
     *
     * @return The chat type associated with the given prefix, or null if no match is found.
     */
    public static @NotNull ChatType getChatTypeByPrefix(String prefix) {
        for (ChatTypesEnum chatType : ChatTypesEnum.values()) {
            if (chatType.getChatType().prefix().equals(prefix)) {
                return chatType.getChatType();
            }
        }
        return ChatTypesEnum.DEFAULT.getChatType();
    }

    /**
     * Retrieves the chat type associated with the method.
     * The method returns a ChatType object that represents different types of chat messages in a chat system.
     *
     * @return The chat type associated with the method.
     */
    public ChatType getChatType() {
        return chatType;
    }

    private static class Constants {
        public static final String DARK_AQUA = ChatColor.of(NamedTextColor.DARK_AQUA.asHexString()).toString();
        public static final String DARK_GREEN = ChatColor.of(NamedTextColor.DARK_GREEN.asHexString()).toString();
        public static final String GREEN = ChatColor.of(NamedTextColor.GREEN.asHexString()).toString();
        public static final String YELLOW = ChatColor.of(NamedTextColor.YELLOW.asHexString()).toString();
        public static final String RED = ChatColor.of(NamedTextColor.RED.asHexString()).toString();
        public static final String WHITE = ChatColor.of(NamedTextColor.WHITE.asHexString()).toString();

        public static final String ACTION_MESSAGE = "*{rpname} {message}*";
        public static final String ENCADREMENT_MESSAGE = ChatColor.of(NamedTextColor.AQUA.asHexString()).toString() + "** {message}";
        public static final String RPNAME_INDICATOR = "<{rpname} [";
    }
}
