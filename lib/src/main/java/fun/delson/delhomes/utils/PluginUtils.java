package fun.delson.delhomes.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginUtils {

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("DeLHomes");
    }

}
