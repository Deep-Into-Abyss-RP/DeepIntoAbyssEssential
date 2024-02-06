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

public class SetRPNameCommand implements CommandExecutor, TabCompleter {
    private final DeepIntoAbyssEssential plugin;

    public SetRPNameCommand(DeepIntoAbyssEssential plugin) {
        this.plugin = plugin;
    }

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
