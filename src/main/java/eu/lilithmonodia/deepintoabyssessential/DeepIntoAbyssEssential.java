package eu.lilithmonodia.deepintoabyssessential;

import eu.lilithmonodia.deepintoabyssessential.commands.SetRPNameCommand;
import eu.lilithmonodia.deepintoabyssessential.configuration.Configuration;
import eu.lilithmonodia.deepintoabyssessential.event.ChatHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * The DeepIntoAbyssEssential class is the main class of the DeepIntoAbyssEssential plugin.
 * It extends the JavaPlugin class and provides methods for enabling and disabling the plugin,
 * as well as reloading the configuration and registering commands.
 */
public final class DeepIntoAbyssEssential extends JavaPlugin {
    private Configuration configuration;

    /**
     * Plugin startup logic.
     * - Saves the default configuration.
     * - Initializes the configuration from the config file.
     * - Sets the plugin instance for the ChatHandler class.
     * - Creates a new instance of the ChatHandler class.
     * - Registers the "setrpname" command.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        configuration = Configuration.fromConfig(getConfig());
        new ChatHandler(this);

        // Register commands
        registerCommand("setrpname", new SetRPNameCommand(this));
    }

    /**
     * Reloads the configuration and updates the configuration object.
     * This method should be called whenever changes are made to the configuration file.
     */
    public void reload() {
        reloadConfig();
        configuration = Configuration.fromConfig(getConfig());
    }

    /**
     * Returns the configuration of the plugin.
     *
     * @return The configuration object containing the RP names mapping.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * This method is called when the plugin is being disabled.
     * It handles the shutdown logic of the plugin.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Registers a command with the provided name and command executor.
     * The command executor must implement both CommandExecutor and TabCompleter interfaces.
     *
     * @param name    The name of the command to be registered.
     * @param command The command executor instance.
     * @param <T>     The type of the command executor.
     */
    @SuppressWarnings("SameParameterValue")
    private <T extends CommandExecutor & TabCompleter> void registerCommand(String name, T command) {
        PluginCommand pluginCommand = getCommand(name);
        Objects.requireNonNull(pluginCommand).setExecutor(command);
        pluginCommand.setTabCompleter(command);
    }
}
