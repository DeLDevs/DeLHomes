package fun.delson.delhomes.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import fun.delson.delhomes.config.Home;
import fun.delson.delhomes.config.PlayerConfig;
import fun.delson.delhomes.utils.Chat;
import fun.delson.delhomes.utils.PlayerConfigUtils;

public class HomeCmd implements TabExecutor {

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
            Home home = null;
            for (Home i : config.homes) {
                if (i.name.equals(name)) {
                    home = i;
                }
            }
            if (home == null) {
                player.sendMessage(Chat.color("&cHome &6" + name + "&c not found."));
                return false;
            }
            Location location = new Location(Bukkit.getWorld(home.world), home.x, home.y, home.z, home.yaw, home.pitch);
            player.teleport(location);
            player.sendMessage(Chat.color("&6Teleporting to &c" + home.name + "&6."));
        } else {
            sender.sendMessage(Chat.color("&6You must be a player to use this command."));
            return false;
        }

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (!(sender instanceof Player)) {
            return null;
        }
        Player player = (Player) sender;
        PlayerConfig config = PlayerConfigUtils.getPlayerConfig(player);

        switch (args.length) {
            case 1:
                for (Home h : config.homes) {
                    commands.add(h.name);
                }
                StringUtil.copyPartialMatches(args[0], commands, completions);
                break;
        }
        

        Collections.sort(completions);
        return completions;

    }

}

