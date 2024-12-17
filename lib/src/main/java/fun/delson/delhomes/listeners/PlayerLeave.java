package fun.delson.delhomes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fun.delson.delhomes.utils.PlayerConfigUtils;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerConfigUtils.writeConfig(player);
        PlayerConfigUtils.removePlayerConfig(player);
    }

}

