package fun.delson.delhomes.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import fun.delson.delhomes.config.PlayerConfig;
import fun.delson.delhomes.utils.PlayerConfigUtils;

public class PlayerTeleport implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {

        Player player = event.getPlayer();
        PlayerConfig config = PlayerConfigUtils.getPlayerConfig(player);
        Location fromLoc = event.getFrom();
        TeleportCause[] allowedReasons = { TeleportCause.COMMAND, TeleportCause.PLUGIN };
        boolean allowed = false;
        for (TeleportCause t : allowedReasons) {
            if (event.getCause().equals(t)) {
                allowed = true;
            }
        }
        if (allowed) {
            config.setBack(fromLoc);
        }

    }

}

