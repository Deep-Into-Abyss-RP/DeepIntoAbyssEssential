package eu.lilithmonodia.deepintoabyssessential;

import eu.lilithmonodia.deepintoabyssessential.commands.SetRPNameCommand;
import eu.lilithmonodia.deepintoabyssessential.configuration.Configuration;
import eu.lilithmonodia.deepintoabyssessential.event.ChatHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DeepIntoAbyssEssential extends JavaPlugin {
    private Configuration configuration;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        configuration = Configuration.fromConfig(getConfig());
        ChatHandler.setPlugin(this);
        new ChatHandler();

        // Register commands
        registerCommand("setrpname", new SetRPNameCommand(this));
    }

    /**
     * Reloads the configuration for this plugin.
     */
    public void reload() {
        reloadConfig();
        configuration = Configuration.fromConfig(getConfig());
    }

    /**
     * Returns the current Configuration instance held by the plugin.
     *
     * @return Configuration instance containing world and coordinates data.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Register a command handler that is both a CommandExecutor and a TabCompleter.
     *
     * @param name    The name of the command to be registered
     * @param command The command handler, which is both a CommandExecutor and a TabCompleter.
     */
    private <T extends CommandExecutor & TabCompleter> void registerCommand(String name, T command) {
        PluginCommand pluginCommand = getCommand(name);
        Objects.requireNonNull(pluginCommand).setExecutor(command);
        pluginCommand.setTabCompleter(command);
    }
}
