package fun.delson.delhomes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fun.delson.delhomes.config.ServerConfig;
import fun.delson.delhomes.utils.Chat;
import fun.delson.delhomes.utils.ServerConfigUtils;

public class Warps implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (sender instanceof Player) {

            ServerConfig config = ServerConfigUtils.serverConfig;
            if (config == null) {
                Bukkit.getLogger().info(Chat.color("&cServer config not found."));
                player.sendMessage(Chat.color("&cServer config not found."));
                return false;
            }
            if (config.warps.length == 0) {
                player.sendMessage(Chat.color("&6There are no warps. An admin can use &c/setwarp &6 to set one."));
                return true;
            }
            String response = "&6Warps: &r";
            response += config.warps[0].name;
            for (int i = 1; i < config.warps.length; i++) {
                response += ", " + config.warps[i].name;
            }
            player.sendMessage(Chat.color(response));

        } else {
            sender.sendMessage(Chat.color("&6You must be a player to use this command."));
            return false;
        }

        return true;

    }

}

