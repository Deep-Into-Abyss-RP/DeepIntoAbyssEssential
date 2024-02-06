package eu.lilithmonodia.deepintoabyssessential.core;

import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

/**
 * ChatTypesEnum is an enumeration that represents different types of chat messages in a chat system.
 */
public enum ChatTypesEnum {
    HRP(new ChatType("(", 20, "<{rpname}> ({message})", NamedTextColor.GRAY)),
    DEFAULT(new ChatType("", 20, "<{rpname} [Dit]> {message}", NamedTextColor.GREEN)),
    WHISPER(new ChatType("#", 3, "<{rpname} [Chuchote]> {message}", NamedTextColor.GREEN)),
    SHOUT(new ChatType("!", 100, "<{rpname} [Crie]> {message}", NamedTextColor.RED)),
    LOUD(new ChatType("+", 50, "<{rpname} [Dit fort]> {message}", NamedTextColor.YELLOW)),
    QUIET(new ChatType("-", 10, "<{rpname} [Dit bas]> {message}", NamedTextColor.DARK_GREEN)),
    GLOBAL(new ChatType(":", 0, "<{rpname}> {message}", NamedTextColor.WHITE)),
    ACTION(new ChatType("*", 20, "*{rpname} {message}*", NamedTextColor.GREEN));


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
     * @return The chat type associated with the given prefix, or null if no match is found.
     */
    public static @Nullable ChatType getChatTypeByPrefix(String prefix) {
        for (ChatTypesEnum chatType : ChatTypesEnum.values()) {
            if (chatType.getChatType().prefix().equals(prefix)) {
                return chatType.getChatType();
            }
        }
        return null;
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
}
