package fun.delson.delhomes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerRespawnEvent.RespawnReason;

import fun.delson.delhomes.utils.Chat;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        if (event.getRespawnReason().equals(RespawnReason.DEATH)) {
            player.sendMessage(Chat.color("&6Type &c/back &6 to return to the location of your death."));
        }

    }

}

