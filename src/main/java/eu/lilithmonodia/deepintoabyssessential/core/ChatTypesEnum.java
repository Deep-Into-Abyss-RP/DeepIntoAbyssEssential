package eu.lilithmonodia.deepintoabyssessential.core;

import org.jetbrains.annotations.Nullable;

public enum ChatTypesEnum {
    HRP(new ChatType("(", 20, "<{rpname}> ({message})")),
    DEFAULT(new ChatType("", 20, "<{rpname} [Dit]> {message}")),
    WHISPER(new ChatType("#", 3, "<{rpname} [Chuchote]> {message}")),
    SHOUT(new ChatType("!", 100, "<{rpname} [Crie]> {message}")),
    LOUD(new ChatType("+", 50, "<{rpname} [Dit fort]> {message}")),
    QUIET(new ChatType("-", 10, "<{rpname} [Dit bas]> {message}")),
    GLOBAL(new ChatType(":", 0, "<{rpname}> {message}")),
    ACTION(new ChatType("*", 20, "*{rpname} {message}*"));


    private final ChatType chatType;

    ChatTypesEnum(ChatType chatType) {
        this.chatType = chatType;
    }

    public static @Nullable ChatType getChatTypeByPrefix(String prefix) {
        for (ChatTypesEnum chatType : ChatTypesEnum.values()) {
            if (chatType.getChatType().getPrefix().equals(prefix)) {
                return chatType.getChatType();
            }
        }
        return null;
    }

    public ChatType getChatType() {
        return chatType;
    }
}
