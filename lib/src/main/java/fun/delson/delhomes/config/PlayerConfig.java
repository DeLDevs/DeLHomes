package fun.delson.delhomes.config;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fun.delson.delhomes.utils.PlayerConfigUtils;

public class PlayerConfig {

    public String name;
    public UUID uuid;
    public Home[] homes;
    public Home back;

    public PlayerConfig(Player player, Home[] homes, Home back) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.homes = homes;
        this.back = back;
    }

    public void setHome(String name, Location location) {
        for (Home i : homes) {
            if (i.name.equals(name)) {
                i.setLocation(location);
            }
        }
        PlayerConfigUtils.writeConfig(Bukkit.getPlayer(uuid));
    }
    
    public void addHome(String name, Location location) {
        Home[] newHomes = new Home[homes.length + 1];
        for (int i = 0; i < homes.length; i++) {
            newHomes[i] = homes[i];
        }
        newHomes[newHomes.length - 1] = new Home(name, location);
        homes = newHomes;
        PlayerConfigUtils.writeConfig(Bukkit.getPlayer(uuid));
    }

    public void delHome(String name) {
        Home[] newHomes = new Home[homes.length - 1];
        for (int h = 0, n = 0, found = 0; h < homes.length; h++) {
            if (found == 0) {
                if (homes[h].name.equals(name)) {
                    found = 1;
                    continue;
                }
            }
            newHomes[n] = homes[h];
            n++;
        }
        homes = newHomes;
        PlayerConfigUtils.writeConfig(Bukkit.getPlayer(uuid));
    }

    public void setBack(Location loc) {
        back = new Home("back", loc);
        PlayerConfigUtils.writeConfig(Bukkit.getPlayer(uuid));
    }

}
