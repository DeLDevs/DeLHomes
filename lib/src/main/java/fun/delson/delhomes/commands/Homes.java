package fun.delson.delhomes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fun.delson.delhomes.config.PlayerConfig;
import fun.delson.delhomes.utils.Chat;
import fun.delson.delhomes.utils.PlayerConfigUtils;

public class Homes implements CommandExecutor {

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
            if (config.homes.length == 0) {
                player.sendMessage(Chat.color("&6You have no homes. Use &c/sethome &6 to set one."));
                return true;
            }
            String response = "&6Homes: &r";
            response += config.homes[0].name;
            for (int i = 1; i < config.homes.length; i++) {
                response += ", " + config.homes[i].name;
            }
            player.sendMessage(Chat.color(response));

        } else {
            sender.sendMessage(Chat.color("&6You must be a player to use this command."));
            return false;
        }

        return true;

    }

}

