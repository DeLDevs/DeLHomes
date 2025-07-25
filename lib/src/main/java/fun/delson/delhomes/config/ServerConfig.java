package fun.delson.delhomes.config;

import org.bukkit.Location;

import fun.delson.delhomes.utils.ServerConfigUtils;

public class ServerConfig {

    public Home[] warps;

    public ServerConfig(Home[] warps) {
        this.warps = warps;
    }

    public void setWarp(String name, Location location) {
        for (Home i : warps) {
            if (i.name.equals(name)) {
                i.setLocation(location);
            }
        }
        ServerConfigUtils.writeConfig();
    }
    
    public void addWarp(String name, Location location) {
        Home[] newWarps = new Home[warps.length + 1];
        for (int i = 0; i < warps.length; i++) {
            newWarps[i] = warps[i];
        }
        newWarps[newWarps.length - 1] = new Home(name, location);
        warps = newWarps;
        ServerConfigUtils.writeConfig();
    }

    public void delWarp(String name) {
        Home[] newWarps = new Home[warps.length - 1];
        for (int h = 0, n = 0, found = 0; h < warps.length; h++) {
            if (found == 0) {
                if (warps[h].name.equals(name)) {
                    found = 1;
                    continue;
                }
            }
            newWarps[n] = warps[h];
            n++;
        }
        warps = newWarps;
        ServerConfigUtils.writeConfig();
    }

}


