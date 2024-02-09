package eu.lilithmonodia.deepintoabyssessential.core;

import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

/**
 * ChatTypesEnum is an enumeration that represents different types of chat messages in a chat system.
 */
public enum ChatTypesEnum {
    HRP(new ChatType("(", 20, "<{rpname}> ({message})", NamedTextColor.GRAY, false, false)),
    DEFAULT(new ChatType("", 20, "<{rpname} [Dit]> {message}", NamedTextColor.GREEN, false, false)),
    WHISPER(new ChatType("#", 3, "<{rpname} [Chuchote]> {message}", NamedTextColor.GREEN, true, false)),
    SHOUT(new ChatType("!", 100, "<{rpname} [Crie]> {message}", NamedTextColor.RED, false, false)),
    LOUD(new ChatType("+", 50, "<{rpname} [Dit fort]> {message}", NamedTextColor.YELLOW, false, false)),
    QUIET(new ChatType("-", 10, "<{rpname} [Dit bas]> {message}", NamedTextColor.DARK_GREEN, false, false)),
    GLOBAL(new ChatType(":", 0, "<{rpname}> {message}", NamedTextColor.WHITE, false, false)),
    STAFF(new ChatType("$", 0, "<{rpname} [Staff]> {message}", NamedTextColor.DARK_PURPLE, true, false)),
    WHISPER_ACTION(new ChatType("#*", 3, Constants.ACTION_MESSAGE, NamedTextColor.GREEN, false, false)),
    CLOSE_ACTION(new ChatType("-*", 5, Constants.ACTION_MESSAGE, NamedTextColor.DARK_GREEN, false, false)),
    NORMAL_ACTION(new ChatType("*", 20, Constants.ACTION_MESSAGE, NamedTextColor.GREEN, false, false)),
    FAR_ACTION(new ChatType("+*", 50, Constants.ACTION_MESSAGE, NamedTextColor.YELLOW, false, false)),
    SHOUT_ACTION(new ChatType("!*", 100, Constants.ACTION_MESSAGE, NamedTextColor.RED, false, false)),
    WHISPER_ENCADREMENT(new ChatType("#$$$", 3, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    CLOSE_ENCADREMENT(new ChatType("-$$$", 5, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    NORMAL_ENCADREMENT(new ChatType("$$$", 20, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    FAR_ENCADREMENT(new ChatType("+$$$", 50, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true)),
    SHOUT_ENCADREMENT(new ChatType("!$$$", 100, Constants.ENCADREMENT_MESSAGE, NamedTextColor.AQUA, false, true));


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
        public static final String ACTION_MESSAGE = "*{rpname} {message}*";
        public static final String ENCADREMENT_MESSAGE = "** {message}";
    }
}
