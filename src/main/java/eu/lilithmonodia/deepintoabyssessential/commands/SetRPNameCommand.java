package eu.lilithmonodia.deepintoabyssessential.commands;

import eu.lilithmonodia.deepintoabyssessential.DeepIntoAbyssEssential;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * SetRPNameCommand is a class that represents the "/setrpname" command in the DeepIntoAbyssEssential plugin.
 * It allows players to set their roleplay (RP) name in the configuration file.
 * The command executor must implement both the CommandExecutor and TabCompleter interfaces.
 */
public class SetRPNameCommand implements CommandExecutor, TabCompleter {
    private final DeepIntoAbyssEssential plugin;

    /**
     * SetRPNameCommand is a class that represents the "/setrpname" command in the DeepIntoAbyssEssential plugin.
     * It allows players to set their roleplay (RP) name in the configuration file.
     * The command executor must implement both the CommandExecutor and TabCompleter interfaces.
     */
    public SetRPNameCommand(DeepIntoAbyssEssential plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the "/setrpname" command.
     *
     * @param sender  The command sender.
     * @param command The command that was executed.
     * @param label   The label used for the command.
     * @param args    The arguments provided for the command.
     * @return true if the command was executed successfully, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage("You must provide a name.");
            return false;
        }

        plugin.getConfig().set(args[0], args[1]);
        plugin.saveConfig();
        plugin.reload();
        sender.sendMessage("Your RP name has been set to " + args[1]);
        return true;
    }

    /**
     * Retrieves a list of tab completions for the onTabComplete method.
     *
     * @param sender The command sender.
     * @param command The command that was executed.
     * @param alias The alias used for the command.
     * @param args The arguments provided for the command.
     * @return A list of tab completions.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                completions.add(player.getName());
            }
            return completions;
        }
        return List.of();
    }
}
