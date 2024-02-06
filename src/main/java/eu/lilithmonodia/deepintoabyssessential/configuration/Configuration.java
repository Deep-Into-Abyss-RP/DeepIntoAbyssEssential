package eu.lilithmonodia.deepintoabyssessential.configuration;


import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public record Configuration(Map<String, String> rpNames) {
    @Contract("_ -> new")
    public static @NotNull Configuration fromConfig(@NotNull FileConfiguration config) {
        Map<String, String> rpNames = new HashMap<>();
        for (String key : config.getKeys(false)) {
            rpNames.put(key, config.getString(key));
        }
        return new Configuration(rpNames);
    }
}
