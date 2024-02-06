package eu.lilithmonodia.deepintoabyssessential.configuration;


import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The Configuration class represents the configuration of the plugin.
 * It contains a mapping of RP names.
 */
public record Configuration(Map<String, String> rpNames) {
    /**
     * Retrieves a Configuration object from a FileConfiguration.
     *
     * @param config The FileConfiguration object to retrieve the Configuration from.
     * @return A new Configuration object with RP names mapping.
     */
    @Contract("_ -> new")
    public static @NotNull Configuration fromConfig(@NotNull FileConfiguration config) {
        Map<String, String> rpNames = new HashMap<>();
        for (String key : config.getKeys(false)) {
            rpNames.put(key, config.getString(key));
        }
        return new Configuration(rpNames);
    }
}
