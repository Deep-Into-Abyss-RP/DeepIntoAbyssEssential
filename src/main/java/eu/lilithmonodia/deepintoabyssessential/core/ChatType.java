package eu.lilithmonodia.deepintoabyssessential.core;

public class ChatType {
    private final String prefix;
    private final int distance;
    private final String format;

    public ChatType(String prefix, int distance, String format) {
        this.prefix = prefix;
        this.distance = distance;
        this.format = format;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getDistance() {
        return distance;
    }

    public String getFormat() {
        return format;
    }
}
