package fun.delson.delhomes.config;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerConfig {

    public String name;
    public UUID uuid;
    Home[] homes;

    public PlayerConfig(Player player, Home[] homes) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.homes = homes;
    }

}
