package fun.delson.delhomes;

import org.bukkit.plugin.java.JavaPlugin;

import fun.delson.delhomes.listeners.PlayerJoin;


public class DeLHomes extends JavaPlugin {
    
    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

}

