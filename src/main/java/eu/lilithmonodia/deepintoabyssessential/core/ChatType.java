package eu.lilithmonodia.deepintoabyssessential.core;

import net.kyori.adventure.text.format.NamedTextColor;

/**
 * ChatType is a record class representing different types of chat messages in a chat system.
 *
 * @param prefix   The prefix associated with the chat type.
 * @param distance The distance at which the chat message is audible.
 * @param format   The format used to display the chat message.
 * @param color    The color of the chat message.
 */
public record ChatType(String prefix, int distance, String format, NamedTextColor color, boolean privateChat, boolean mJExclusive) {}
