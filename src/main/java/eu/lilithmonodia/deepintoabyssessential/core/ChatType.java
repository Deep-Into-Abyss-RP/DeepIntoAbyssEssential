package eu.lilithmonodia.deepintoabyssessential.core;

import net.kyori.adventure.text.format.NamedTextColor;

/**
 * ChatType is a record class representing different types of chat messages in a chat system.
 *
 * @param prefix   The prefix associated with the chat type.
 * @param distance The distance at which the chat message can be heard or read.
 * @param format   The format of the chat message.
 */
public record ChatType(String prefix, int distance, String format, NamedTextColor color, boolean privateChat) {
}
