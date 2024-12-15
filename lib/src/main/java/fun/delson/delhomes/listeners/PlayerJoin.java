package fun.delson.delhomes.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fun.delson.delhomes.utils.PlayerConfigUtils;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerConfigUtils.loadConfig(event.getPlayer());
    }

}
