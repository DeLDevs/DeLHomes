package fun.delson.delhomes;

import org.bukkit.plugin.java.JavaPlugin;

public class DeLHomes extends JavaPlugin {
    
    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

}

