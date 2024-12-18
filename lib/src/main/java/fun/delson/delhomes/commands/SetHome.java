package fun.delson.delhomes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fun.delson.delhomes.config.Home;
import fun.delson.delhomes.config.PlayerConfig;
import fun.delson.delhomes.utils.Chat;
import fun.delson.delhomes.utils.PlayerConfigUtils;
import fun.delson.delhomes.utils.PluginUtils;

public class SetHome implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        Player player = (Player) sender;

        if (sender instanceof Player) {
            PlayerConfig config = PlayerConfigUtils.getPlayerConfig(player);
            if (config == null) {
                Bukkit.getLogger().info(Chat.color("&cPlayer &6" + player.getName() + "&c not found."));
                player.sendMessage(Chat.color("&cPlayer &6" + player.getName() + "&c not found."));
                return false;
            }
            if (args.length != 1) {
                player.sendMessage(Chat.color("&cIncorrect usage. Requires exactly 1 argument."));
                return false;
            }
            String name = args[0];
            Home updating = null;
            for (Home i : config.homes) {
                if (i.name.equals(name)) {
                    updating = i;
                    break;
                }
            }
            if (updating == null) {
                if (config.homes.length >= PluginUtils.getConfig().getInt("max-homes")) {
                    player.sendMessage(Chat.color("&cToo many homes. Update an existing one or use &6/delhome&c to remove one."));
                    return false;
                } else {
                    config.addHome(name, player.getLocation());
                    player.sendMessage(Chat.color("&6Home &c" + name + "&6 set to current location."));
                }
            } else {
                config.setHome(name, player.getLocation());
                player.sendMessage(Chat.color("&6Home &c" + name + "&6 updated to current location."));
            }
        } else {
            sender.sendMessage(Chat.color("&6You must be a player to use this command."));
            return false;
        }

        return true;

    }

}

