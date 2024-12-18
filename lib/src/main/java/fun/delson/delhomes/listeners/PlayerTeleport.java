package fun.delson.delhomes.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import fun.delson.delhomes.config.PlayerConfig;
import fun.delson.delhomes.utils.PlayerConfigUtils;

public class PlayerTeleport implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {

        Player player = event.getPlayer();
        PlayerConfig config = PlayerConfigUtils.getPlayerConfig(player);
        Location fromLoc = event.getFrom();
        config.setBack(fromLoc);

    }

}

