package fun.delson.delhomes.config;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerConfig {

    public Player player;
    ArrayList<Home> homes;

    public PlayerConfig(Player player, ArrayList<Home> homes) {
        this.player = player;
        this.homes = homes;
    }

}
